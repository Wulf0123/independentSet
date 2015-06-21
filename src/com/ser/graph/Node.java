package com.ser.graph;

/**
 * Created by Bradley
 * on 6/21/2015.
 */
public class Node {
    Edges edge;
    Edges iEdge;

    public Node(Edges edge){
        this.edge = edge;
    }

    public Edges getInvertedEdges(){
        if(iEdge == null){
            edge.inverted();
        }
        return iEdge;
    }
}
