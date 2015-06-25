package com.ser.graph;

/**
 * Created by Bradley 
 * on 6/24/2015.
 */
public interface Edges {
    boolean get(int index);

    Edges inverted();

    int length();

    void set(int i, boolean b);

    int size();

    Edges or(Edges solve);

    Edges and(Edges invertedEdges);

    public static interface Reader {
        Edges parse(String[] tokens, int start, int size);
    }
}
