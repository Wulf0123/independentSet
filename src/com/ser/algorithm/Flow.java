package com.ser.algorithm;

import com.ser.graph.Edges;
import com.ser.graph.Graph;
import com.ser.graph.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bradley
 * on 6/21/2015.
 */
public enum Flow {
    ;
    public static Edges solve(Graph graph){
        Node start = findFirstNode(graph);
        Edges solution = new Edges(graph.size());
        return solve(graph, start, solution);
    }

    private static Edges solve(Graph graph, Node node, Edges solution){
        solution.set(node.getVertex(), true);
        List<Edges> children = new ArrayList<Edges>();
        Edges edges = new Edges(node.getInvertedEdges());
        edges.set(node.getVertex(), false);
        if(edges.size() > solution.size()) {
            for (int i = 0; i < graph.size(); i++) {
                if (edges.get(i)) {
                    Edges childrenEdges = graph.get(i).getInvertedEdges();
                    children.add(edges.and(childrenEdges));
                    edges.set(i, false);
                }
            }
        }
        if(children.size() > 0){
            solution = solve(graph, solution, children);
        }
        return solution;
    }

    private static Edges solve(Graph graph, Edges solution, List<Edges> edges){
        while(edges.size() > 0){
            Edges edge = edges.remove(0);
            for(int i = 0; i < graph.size(); i++){
                if(edge.get(i)){
                    Node node = graph.get(i);
                    List<Edges> children = new ArrayList<Edges>();
                    Edges nodeEdges = edge.and(node.getInvertedEdges());
                    if(nodeEdges.size() > solution.size()) {
                        for (int j = 0; j < graph.size(); j++) {
                            if (nodeEdges.get(j)) {
                                solution.set(j, true);
                                nodeEdges.set(j, false);
                                children.add(nodeEdges.and(nodeEdges));
                            }
                        }
                    }
                    edges.addAll(children);
                }
            }
        }

        return solution;
    }

    private static Node findFirstNode(Graph graph){
        return graph.get(0); //TODO find the node with the smallest edges
    }
}