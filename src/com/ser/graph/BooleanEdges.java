package com.ser.graph;

/**
 * Created by Bradley
 * on 6/21/2015.
 */
public class BooleanEdges implements Edges {
    private boolean[] edges;
    private int size = -1;

    public BooleanEdges(int size){
        if(size >= 0) {
            edges = new boolean[size];
        }
    }

    public BooleanEdges(Edges edge){
        this(edge.length());
        for(int i = 0; i < edge.length(); i++){
            edges[i] = edge.get(i);
        }
    }

    @Override
    public void set(int i, boolean value){
        if(edges != null && i >= 0 && i < edges.length){
            edges[i] = value;
            size = -1;
        }
    }

    @Override
    public boolean get(int i){
        if(edges == null || i < 0 || i >= edges.length) throw new ArrayIndexOutOfBoundsException();
        return edges[i];
    }

    @Override
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

    @Override
    public int length(){
        return edges == null ? 0 : edges.length;
    }

    @Override
    public Edges and(Edges edge){
        BooleanEdges result = new BooleanEdges(length());
        for(int i = 0; i < length(); i++){
            result.set(i, this.get(i) && edge.get(i));
        }
        return result;
    }

    @Override
    public Edges or(Edges edge){
        BooleanEdges result = new BooleanEdges(length());
        for(int i = 0; i < length(); i++){
            result.set(i, this.get(i) || edge.get(i));
        }
        return result;
    }

    @Override
    public Edges inverted(){
        BooleanEdges edges = new BooleanEdges(this.edges.length);
        for(int i = 0; i < edges.length(); i++){
            edges.set(i, !this.edges[i]);
        }
        return edges;
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof Edges && this.edges != null){
            BooleanEdges edge = (BooleanEdges) o;
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

    public static class BooleanReader implements Reader {

        @Override
        public Edges parse(String[] tokens, int start, int size) {
            Edges solution = new BooleanEdges(size);
            for(int i = start; i < tokens.length; i++) {
                if(start != 0) {
                    int j = Integer.parseInt(tokens[i]);
                    solution.set(j, true);
                } else{
                    if(tokens[i].equals("1")){
                        solution.set(i, true);
                    }
                }
            }
            return solution;
        }
    }
}