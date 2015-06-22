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

    public Edges(Edges edge){
        this(edge.size());
        for(int i = 0; i < edge.size(); i++){
            edges[i] = edge.get(i);
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

    @Override
    public boolean equals(Object o){
        if(o instanceof Edges && this.edges != null){
            Edges edge = (Edges) o;
            for(int i = 0; i < this.edges.length; i++){
                if(this.edges[i] != edge.get(i)){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public String toString(){
        String string = "";
        if(edges != null) {
            for (int i = 0; i < edges.length; i++) {
                String edge;
                if(edges[i]){
                    edge = "1";
                } else{
                    edge = "0";
                }
                string = String.format("%s%s ", string, edge);
            }
        }
        return string.trim();
    }
}