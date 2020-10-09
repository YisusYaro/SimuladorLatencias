/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redes;

import java.util.ArrayList;
import java.math.*;
/**
 *
 * @author JesusAlejandro
 */
public class CalculadoraLatencia {
    private int numeroNodos;
    private int numeroEnlaces;
    private ArrayList<float[]> nodos;
    private ArrayList<int[]> enlaces;
    private float tamanioTranferencia; 
    private int nodoOrigen;
    private int nodoDestino;
    private ArrayList< int[] > caminos;
    private ArrayList< BigDecimal > latencias;
    private int caminoGanador;

    CalculadoraLatencia(int numeroNodos, int numeroEnlaces, ArrayList<float[]> nodos, ArrayList<int[]> enlaces, float tamanioTranferencia, int nodoOrigen, int nodoDestino, ArrayList< int[] > caminos){
        this.numeroNodos=numeroNodos;
        this.numeroEnlaces=numeroEnlaces;
        this.nodos=nodos;
        this.enlaces=enlaces;
        this.tamanioTranferencia=tamanioTranferencia; 
        this.nodoOrigen=nodoOrigen;
        this.nodoDestino=nodoDestino;
        this.caminos=caminos;
        latencias = new ArrayList<>();
    }
    
    int numeroPaquetes(int caminoGanador){
        //si existe decimales va al tope
        
            for(int i=0; i<enlaces.size(); i++)
                if(enlaces.get(i)[0]==caminos.get(caminoGanador)[0] && enlaces.get(i)[1]==caminos.get(caminoGanador)[1] || enlaces.get(i)[0]==caminos.get(caminoGanador)[1] && enlaces.get(i)[1]==caminos.get(caminoGanador)[0]){
                    
                    return (int)Math.ceil( Conversor.gigabytesABytes(tamanioTranferencia) / enlaces.get(i)[5] );
                }
        return 0;
    }
    
    public BigDecimal calcularTiempoTasaTransferencia(int[] camino){
        
        boolean c = true;
        BigDecimal latenciaTotal = new BigDecimal(0);
        BigDecimal latencia;
        ArrayList<Integer> paquete = new ArrayList<>();
        
        for(int x=0; x<camino.length-1; x++){
            for(int i=0; i<enlaces.size(); i++){
                
                if(enlaces.get(i)[0]==camino[x] && enlaces.get(i)[1]==camino[x+1] || enlaces.get(i)[0]==camino[x+1] && enlaces.get(i)[1]==camino[x]){

                    latencia=BigDecimal.ZERO;
                    latencia=latencia.add(calcularTiempoPropagacion(enlaces.get(i)[3]) );
                    latencia=latencia.add(calcularTiempoTransmision( enlaces.get(i)[4], enlaces.get(i)[5] ,enlaces.get(i)[2] ));
                    if(camino[x+1]!=nodoDestino){
                            latencia=latencia.add(obtenerTiempoCola(camino[x+1]));
                    }
                    if(c==true){
                        c=false;
                        paquete.add(enlaces.get(i)[5]);
                    }

                    for(int j=0; j<paquete.size(); j++){
                        if(paquete.get(j)>enlaces.get(i)[5]){

                            ArrayList<Integer> paquetesNuevos = divisorPaquetes(paquete.get(j), enlaces.get(i)[5] );
                            paquete.remove(paquete.indexOf(paquete.get(j)));
                            paquete.addAll(paquetesNuevos);
                            j=-1;
                        }
                        
                    }

                    BigDecimal multiplicador = new BigDecimal(paquete.size());
                    latencia=latencia.multiply(multiplicador);
                    latenciaTotal=latenciaTotal.add(latencia);    
                }
                
            }
            
        }
        
        return latenciaTotal;
    }
    
    public ArrayList<Integer> divisorPaquetes(int paqueteAnterior, int paqueteActual){
        ArrayList<Integer> paquetes = new ArrayList<>();
        
        paquetes.add(paqueteActual);
        
        do{
            paqueteAnterior=paqueteAnterior-paqueteActual;
            paquetes.add(paqueteAnterior);
        }while(paqueteAnterior>paqueteActual);
        
        return paquetes;
    }
    
    BigDecimal calcularTiempoPropagacion(int metros){
        BigDecimal m= new BigDecimal(metros);
        BigDecimal c= new BigDecimal(299792458);
        m=m.divide(c,MathContext.DECIMAL128);
        
        return m;
    }
    
    BigDecimal calcularTiempoTransmision(int DC, int DU, int tasaTransferencia){
        
        BigDecimal d = Conversor.bytesAMegabits(DC + DU);
        BigDecimal t = new BigDecimal(tasaTransferencia);
        d = d.divide(t, MathContext.DECIMAL128);
        return d;
    }
    
    BigDecimal obtenerTiempoCola(int nodo){
        
        BigDecimal value = new BigDecimal(nodos.get(nodo)[1], new MathContext(5, RoundingMode.HALF_EVEN));
        return value;
    }
    
    public void calcularLatencias(){
        for(int x=0; x<caminos.size(); x++){
            System.out.println("Camino: "+x);
            for(int f=0; f<caminos.get(x).length; f++){
                System.out.print(caminos.get(x)[f]);
            }
            System.out.println("");
            BigDecimal latenciaActual = calcularTiempoTasaTransferencia(caminos.get(x));
            latencias.add(latenciaActual);
            System.out.println("Latencia: " +latenciaActual);
            System.out.println("");
        }
    }
    
    public void mostrarTiempoTotalTransferenciaMinimo(){
        BigDecimal minima = latencias.get(0);
        
        for(int i=0; i<latencias.size(); i++)
            if (minima.compareTo(latencias.get(i)) == 1){
                minima=latencias.get(i);
                caminoGanador=i;
            }
        BigDecimal numeroPaquetes = new BigDecimal( numeroPaquetes(caminoGanador) );
        
        System.out.println("Minima: "+minima);
        System.out.println("Proveniente del camino: "+caminoGanador);
        System.out.println("Numero paquetes: "+numeroPaquetes);
        
        BigDecimal tiempoEnSegundos = numeroPaquetes;
        tiempoEnSegundos = tiempoEnSegundos.multiply(minima);
        
        System.out.println("Segundos: "+tiempoEnSegundos);
        Conversor.mostrarHorasMinutosSegundos(tiempoEnSegundos);
    }

    
}
