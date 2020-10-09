/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redes;

import java.util.*;
import java.util.ArrayList;

/**
 *
 * @author JesusAlejandro
 */

public class Grafo {
    private int numeroNodos;
    private int numeroEnlaces;
    private ArrayList<int[]> enlaces;
    private int nodoOrigen;
    private int nodoDestino;
   
    
    private ArrayList<Integer> listaAdyacencia[];
    private ArrayList<Integer> camino;
    private boolean marked[];
    private ArrayList< int[] > caminos;
    
    Grafo(int numeroNodos, int numeroEnlaces, ArrayList<int[]> enlaces, int nodoOrigen, int nodoDestino){
        this.numeroNodos = numeroNodos;
        this.numeroEnlaces = numeroEnlaces;
        this.enlaces = enlaces;
        this.nodoOrigen = nodoOrigen;
        this.nodoDestino = nodoDestino;
       
        
        this.listaAdyacencia = new ArrayList[this.numeroNodos];
        this.marked = new boolean[this.numeroNodos];
        this.camino = new ArrayList<>();
        this.caminos = new ArrayList<>();
        
        
        crearListaAdyacencia();
        dfs(this.nodoOrigen,this.nodoDestino);
       
    }

    public ArrayList<int[]> getCaminos() {
        return caminos;
    }
    
    
    private void crearListaAdyacencia(){

        inicializarListaAdyacencia();
        
        for(int x=0; x<this.enlaces.size(); x++){

                this.listaAdyacencia[ this.enlaces.get(x)[0] ].add( this.enlaces.get(x)[1] );
                this.listaAdyacencia[ this.enlaces.get(x)[1] ].add( this.enlaces.get(x)[0] );
        }
        
    }
    
    private void inicializarListaAdyacencia(){
        for(int x=0; x<this.numeroNodos; x++)
                listaAdyacencia[ x ] = new ArrayList<Integer>();
    }
    
    private void dfs (int s,int f){
        marked[s]=true;
        camino.add(s);
        int i, next;
        if(s==f){
            guardarCamino();
        } 
        else{
            for(i=0; i<listaAdyacencia[s].size(); i++){
                next = listaAdyacencia[s].get(i);
                if(!marked[next]){
                    dfs(next,f);
                }
            }
        }
        camino.remove(camino.indexOf(s));
        marked[s]=false;
    }

    private void guardarCamino(){
        int [] caminoNuevo = new int[camino.size()];
        for(int i=0; i<camino.size(); i++){
            caminoNuevo[i]=camino.get(i);
        }
        
        caminos.add(caminoNuevo);
    }
    
        private void imprimirCaminos(){
        for(int x=0; x<this.caminos.size(); x++){
            System.out.println("camino: "+x);
            for (int i=0; i<this.caminos.get(x).length; i++){
                System.out.println(this.caminos.get(x)[i]);
            }
            System.out.println("");
        }
    }
    
}