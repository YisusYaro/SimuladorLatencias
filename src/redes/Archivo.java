/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redes;

import java.io.*;
import java.util.StringTokenizer;
import java.util.ArrayList;
/**
 *
 * @author JesusAlejandro
 */
public class Archivo {
    
    private int numeroNodos;
    private int numeroEnlaces;
    private ArrayList<float[]> nodos = new ArrayList<>();
    private ArrayList<int[]> enlaces = new ArrayList<>();
    private float tamanioTranferencia; 
    private int nodoOrigen;
    private int nodoDestino;
    private String separador;

    
    Archivo(){
        separador=" ";
    }
    
    public int getNumeroNodos() {
        return numeroNodos;
    }

    public int getNumeroEnlaces() {
        return numeroEnlaces;
    }

    public ArrayList<float[]> getNodos() {
        return nodos;
    }

    public ArrayList<int[]> getEnlaces() {
        return enlaces;
    }

    public float getTamanioTranferencia() {
        return tamanioTranferencia;
    }

    public int getNodoOrigen() {
        return nodoOrigen;
    }

    public int getNodoDestino() {
        return nodoDestino;
    }
    public void leer(){
        
        
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            int contador=0;
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File ("red.txt");
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            while((linea=br.readLine())!=null){
                if(contador==0){ 
                    StringTokenizer tokens = new StringTokenizer(linea,separador);
                    this.numeroNodos = Integer.parseInt( tokens.nextToken() );
                    this.numeroEnlaces = Integer.parseInt( tokens.nextToken() );
                }

                if(contador>0 && contador<=this.numeroNodos){ 
                    float[] arregloNodos = new float[2];
                    StringTokenizer tokens = new StringTokenizer(linea,separador);
                    arregloNodos[0] = Float.parseFloat( tokens.nextToken() );
                    arregloNodos[1] = Float.parseFloat( tokens.nextToken() );
                    nodos.add(arregloNodos);

                }

                if(contador>this.numeroNodos && contador<=(this.numeroEnlaces+this.numeroNodos) ){
                    int[] arregloEnlaces = new int[6];
                    StringTokenizer tokens = new StringTokenizer(linea,separador);
                    arregloEnlaces[0] = Integer.parseInt( tokens.nextToken() );
                    arregloEnlaces[1] = Integer.parseInt( tokens.nextToken() );
                    arregloEnlaces[2] = Integer.parseInt( tokens.nextToken() );
                    arregloEnlaces[3] = Integer.parseInt( tokens.nextToken() );
                    arregloEnlaces[4] = Integer.parseInt( tokens.nextToken() );
                    arregloEnlaces[5] = Integer.parseInt( tokens.nextToken() );
                    enlaces.add(arregloEnlaces);
                }

                if(contador>(this.numeroEnlaces+this.numeroNodos) && contador<=(this.numeroEnlaces+this.numeroNodos+1) ){
                    this.tamanioTranferencia = Float.parseFloat( linea );
                }
                if(contador==(this.numeroEnlaces+this.numeroNodos+2) ){
                    StringTokenizer tokens = new StringTokenizer(linea,separador);
                    this.nodoOrigen = Integer.parseInt( tokens.nextToken());
                    this.nodoDestino = Integer.parseInt(tokens.nextToken());
                }
                contador++;
            }



            /*System.out.println("nNodos"+this.numeroNodos);
            System.out.println("nEnlaces"+this.numeroEnlaces);
            for(int x=0; x<this.nodos.size(); x++){
                System.out.println("nodo "+this.nodos.get(x)[0]);
                System.out.println("tiempo de cola "+this.nodos.get(x)[1]);
            }
            for(int x=0; x<this.enlaces.size(); x++){
                System.out.println("origen "+this.enlaces.get(x)[0]);
                System.out.println("destino "+this.enlaces.get(x)[1]);
                System.out.println("velocidad maxima "+this.enlaces.get(x)[2]);
                System.out.println("distancia "+this.enlaces.get(x)[3]);
                System.out.println("DC "+this.enlaces.get(x)[4]);
                System.out.println("DU "+this.enlaces.get(x)[5]);
            }
            System.out.println("Tamaño de transferencia "+this.tamanioTranferencia);
            System.out.println("Nodo origen "+this.nodoOrigen);
            System.out.println("Nodo destino "+this.nodoDestino);*/
                        
        }
        catch(Exception e){
           e.printStackTrace();
        }finally{
           // En el finally cerramos el fichero, para asegurarnos
           // que se cierra tanto si todo va bien como si salta 
           // una excepcion.
           try{
              if( null != fr ){
                 fr.close();
              }
           }catch (Exception e2){
              e2.printStackTrace();
           }
        }
    } 
}
