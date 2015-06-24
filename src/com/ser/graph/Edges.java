package com.ser.graph;

/**
 * Created by Bradley
 * on 6/21/2015.
 */
public class Edges {
    private boolean[] edges;
    private int size = -1;

    public Edges(int size){
        if(size >= 0) {
            edges = new boolean[size];
        }
    }

    public Edges(Edges edge){
        this(edge.length());
        for(int i = 0; i < edge.length(); i++){
            edges[i] = edge.get(i);
        }
    }

    public void set(int i, boolean value){
        if(edges != null && i >= 0 && i < edges.length){
            edges[i] = value;
            size = -1;
        }
    }

    public boolean get(int i){
        if(edges == null || i < 0 || i >= edges.length) throw new ArrayIndexOutOfBoundsException();
        return edges[i];
    }

    public int size(){
        if(size == -1) {
            size = 0;
            for (boolean edge : edges) {
                if (edge) {
                    size++;
                }
            }
        }
        return size;
    }

    public int length(){
        return edges == null ? 0 : edges.length;
    }

    public Edges and(Edges edge){
        Edges result = new Edges(length());
        for(int i = 0; i < length(); i++){
            result.set(i, this.get(i) && edge.get(i));
        }
        return result;
    }

    public Edges or(Edges edge){
        Edges result = new Edges(length());
        for(int i = 0; i < length(); i++){
            result.set(i, this.get(i) || edge.get(i));
        }
        return result;
    }

    public Edges inverted(){
        Edges edges = new Edges(this.edges.length);
        for(int i = 0; i < edges.length(); i++){
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
            for (boolean edge1 : edges) {
                String edge;
                if (edge1) {
                    edge = "1";
                } else {
                    edge = "0";
                }
                string = String.format("%s%s ", string, edge);
            }
        }
        return string.trim();
    }
}