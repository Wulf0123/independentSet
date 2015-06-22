package com.ser.graph;

/**
 * Created by Bradley
 * on 6/21/2015.
 */
public class Graph {
    private Node[] nodes;
    private Edges solution;

    public Graph(Node[] nodes){
        this.nodes = nodes;
    }

    public Graph(Node[] nodes, Edges solution){
        this.nodes = nodes;
        this.solution = solution;
    }

    public Node[] getNodes(){
        return nodes;
    }

    public Edges getSolution(){
        return solution;
    }

    public boolean checkSolution(Edges solution){
        for(int i = 0; i < solution.length(); i++){
            if(solution.get(i)){
                
            }
        }
        return false;
    }

    public int size(){
        return nodes == null ? 0 : nodes.length;
    }
}
