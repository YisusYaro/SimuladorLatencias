/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redes;

/**
 *
 * @author JesusAlejandro
 */
public class Redes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // TODO code application logic here
        
        Archivo miArchivo = new Archivo();
        miArchivo.leer();
        Grafo miGrafo = new Grafo(miArchivo.getNumeroNodos(),miArchivo.getNumeroEnlaces(), miArchivo.getEnlaces(), miArchivo.getNodoOrigen(), miArchivo.getNodoDestino());
        CalculadoraLatencia miCalculadoraLatencia = new CalculadoraLatencia(miArchivo.getNumeroNodos(), miArchivo.getNumeroEnlaces(), miArchivo.getNodos(), miArchivo.getEnlaces(), miArchivo.getTamanioTranferencia(), miArchivo.getNodoOrigen(), miArchivo.getNodoDestino(), miGrafo.getCaminos());
        miCalculadoraLatencia.calcularLatencias();
        miCalculadoraLatencia.mostrarTiempoTotalTransferenciaMinimo();
    }
    
}
