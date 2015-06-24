package com.ser.algorithm;

import com.ser.algorithm.com.ser.algorithm.helper.SolutionPair;
import com.ser.graph.Edges;
import com.ser.graph.Graph;
import com.ser.graph.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Bradley
 * on 6/21/2015.
 */
public enum Flow {
    ;
    public static Edges solve(Graph graph){
        List<SolutionPair> initialPairs = findInitialNodes(graph);
        return solve(graph, initialPairs, new Edges(graph.size()), new HashMap<String, Edges>());
    }

    private static List<SolutionPair> findInitialNodes(Graph graph){
        Node initialNode = findInitialNode(graph);
        return findInitialPairs(initialNode, graph);
    }

    private static Node findInitialNode(Graph graph){
        //TODO change to pick Node with smallest edge
        return graph.get(0);
    }

    private static List<SolutionPair> findInitialPairs(Node node, Graph graph){
        List<SolutionPair> initialPairs = new ArrayList<SolutionPair>();
        Edges edges = node.getEdges();
        for(int i = 0; i < edges.length(); i++){
            if(edges.get(i)){
                Node currNode = graph.get(i);
                Edges currEdge = currNode.getInvertedEdges();
                Edges solution = new Edges(graph.size());
                currEdge.set(i, false);
                solution.set(i, true);
                initialPairs.add(new SolutionPair(currEdge, solution));
            }
        }
        return initialPairs;
    }

    private static Edges solve(Graph graph, List<SolutionPair> edges, Edges solution, final Map<String, Edges> knownSolutions){
        while(edges.size() > 0){
            SolutionPair edge = edges.remove(0);
            if(knownSolutions.containsKey(edge.toString())){
                Edges currSolution = edge.getSolution().or(knownSolutions.get(edge.toString()));
                if(currSolution.size() > solution.size()){
                    solution = currSolution;
                }
            } else{
                if(edge.getEdges().size() == 0){
                    if(edge.getSolution().size() > solution.size()){
                        solution = edge.getSolution();
                    }
                } else{
                    List<SolutionPair> newPairs = new ArrayList<SolutionPair>();
                    Edges currEdges = edge.getEdges();
                    for(int i = 0; i < currEdges.length(); i++) {
                        if (currEdges.get(i)) {
                            Edges currEdge = graph.get(i).getInvertedEdges().and(currEdges);
                            Edges currSolution = edge.getSolution();
                            currSolution.set(i, true);
                            newPairs.add(new SolutionPair(currEdge, currSolution));
                        }
                    }
                    Edges maxSolution = solve(graph, newPairs, solution, knownSolutions);
                    maxSolution = maxSolution.or(edge.getSolution());
                    if(maxSolution.size() > solution.size()){
                        solution = maxSolution;
                    }
                }
            }
        }
        return solution;
    }
}