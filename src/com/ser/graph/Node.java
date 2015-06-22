package com.ser.graph;

/**
 * Created by Bradley
 * on 6/21/2015.
 */
public class Node {
    int vertex;
    Edges edge;
    Edges iEdge;

    public Node(int vertex, Edges edge){
        this.vertex = vertex;
        this.edge = edge;
    }

    public int getVertex(){
        return vertex;
    }

    public boolean getEdge(int index){
        if(edge != null){
            return edge.get(index);
        }
        return false;
    }

    public Edges getInvertedEdges(){
        if(iEdge == null){
            iEdge = edge.inverted();
        }
        return iEdge;
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof Node){
            Node node = (Node) o;
            if(this.edge != null && node.edge != null) {
                return this.vertex == node.vertex && this.edge.equals(node.edge);
            }
        }
        return false;
    }
}
