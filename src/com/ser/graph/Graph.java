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

    public Node get(int index){
        return nodes == null ? null : nodes[index];
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
                Node node = nodes[i];
                for(int j = 0; j < solution.length(); j++){
                    if(solution.get(j) && node.getEdge(j)){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public int size(){
        return nodes == null ? 0 : nodes.length;
    }
}
