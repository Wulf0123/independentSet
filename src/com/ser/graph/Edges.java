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
        if(i >= 0 && i < edges.length){
            edges[i] = value;
        }
    }

    public Boolean get(int i){
        return i >= 0 && i < edges.length ? edges[i] : null;
    }

    public int size(){
        return edges == null ? 0 : edges.length;
    }
}