package com.ser.algorithm;

import com.ser.algorithm.com.ser.algorithm.helper.SolutionPair;
import com.ser.graph.*;
import com.ser.graph.Edges;

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
       return solve(graph, initialPairs);
    }

    private static List<SolutionPair> findInitialNodes(Graph graph){
        Node initialNode = findInitialNode(graph);
        return findInitialPairs(initialNode, graph);
    }

    private static Node findInitialNode(Graph graph){
        Node initialNode = graph.get(0);
        for(int i = 1; i < graph.size(); i++){
            Node currNode = graph.get(i);
            if(initialNode.getEdges().size() > currNode.getEdges().size()){
                initialNode = currNode;
            }
        }
        return initialNode;
    }

    private static List<SolutionPair> findInitialPairs(Node node, Graph graph){
        List<SolutionPair> initialPairs = new ArrayList<SolutionPair>();
        Edges edges = node.getEdges();
        for(int i = 0; i < edges.length(); i++){
            if(edges.get(i) || i == node.getVertex()){
                Node currNode = graph.get(i);
                Edges currEdge = currNode.getInvertedEdges();
                Edges solution = new BooleanEdges(graph.size());
                currEdge.set(i, false);
                solution.set(i, true);
                initialPairs.add(new SolutionPair(currEdge, solution));
            }
        }
        return initialPairs;
    }

    private static Edges solve(Graph graph, List<SolutionPair> solutions){
        Edges solution = new BooleanEdges(graph.size());
        while(solutions.size() > 0) {
            SolutionPair current = solutions.remove(0);
            Edges currentSolution = current.getSolution().or(solve(graph, current.getEdges(), new HashMap<String, Edges>()));
            if(currentSolution.size() > solution.size()){
                solution = currentSolution;
            }
        }
        return solution;
    }

    private static Edges solve(Graph graph, Edges currentEdges, Map<String, Edges> knownSolutions){
        Edges solution = new BooleanEdges(graph.size());
        if(currentEdges.size() > 0){
            for(int i = 0; i < currentEdges.length(); i++){
                if(currentEdges.get(i)){
                    Edges thisSolution;
                    Edges nextEdges = graph.get(i).getInvertedEdges();
                    nextEdges = nextEdges.and(currentEdges);
                    nextEdges.set(i, false);
                    if(knownSolutions.containsKey(nextEdges.toString())){
                        thisSolution = new BooleanEdges(knownSolutions.get(nextEdges.toString()));
                    } else{
                        thisSolution = solve(graph, nextEdges, knownSolutions);
                        knownSolutions.put(nextEdges.toString(), new BooleanEdges(thisSolution));
                    }
                    thisSolution.set(i, true);
                    if(thisSolution.size() > solution.size()){
                        solution = thisSolution;
                    }
                }
            }
        }
        return solution;
    }
}