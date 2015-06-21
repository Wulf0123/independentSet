package com.ser.graph;

/**
 * Created by Bradley
 * on 6/21/2015.
 */
public class Edges {
    boolean[] edges;

    public Edges(int size){
        if(size >= 0) {
            edges = new boolean[size];
        }
    }

    public void set(int i, boolean value){
        if(edges != null && i >= 0 && i < edges.length){
            edges[i] = value;
        }
    }

    public boolean get(int i){
        if(edges == null || i < 0 || i >= edges.length) throw new ArrayIndexOutOfBoundsException();
        return edges[i];
    }

    public int size(){
        return edges == null ? 0 : edges.length;
    }

    public Edges inverted(){
        Edges edges = new Edges(this.edges.length);
        for(int i = 0; i < edges.size(); i++){
            edges.set(i, !this.edges[i]);
        }
        return edges;
    }
}