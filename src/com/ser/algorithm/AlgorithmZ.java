package com.ser.algorithm;

import com.ser.graph.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bradley
 * on 9/25/2015.
 */
public enum AlgorithmZ {
    ;

    public static Edges solve(Graph graph){
        return solve(graph, findInitial(graph));
    }

    private static Edges solve(Graph graph, List<Node> initial) {
        NumericEdges solution = new NumericEdges(graph.size());
        for(Node node : initial) {
            NumericEdges solutionEdges = solve(graph, node.getInvertedEdges());
            if(solutionEdges.size() > solution.size()){
                solution = solutionEdges;
            }
        }
        return new BooleanEdges(solution);
    }

    private static NumericEdges solve(Graph graph, Edges edges){
        NumericEdges numEdges = calculateEdges(graph, edges);
        if(done(numEdges)){
            return numEdges;
        }
        int max = findMax(numEdges);
        numEdges = zeroSmallestEdgesOfMax(graph, numEdges, max);
        return solve(graph, new BooleanEdges(numEdges));
    }

    private static boolean done(NumericEdges numEdges) {
        int last = 0;
        for(int i = 0; i < numEdges.length(); i++){
            if(numEdges.get(i)){
                if(last == 0){
                    last = numEdges.getInteger(i);
                } else if(last != numEdges.getInteger(i)){
                    return false;
                }
            }
        }
        return true;
    }

    private static NumericEdges zeroSmallestEdgesOfMax(Graph graph, NumericEdges edges, int max) {
        for(int i = 0; i < edges.length(); i++){
            if(edges.getInteger(i) == max){
                edges = zeroSmallestEdgesOf(graph, edges, i);
            }
        }
        return edges;
    }

    private static NumericEdges zeroSmallestEdgesOf(Graph graph, NumericEdges edges, int index) {
        Edges maxEdges = graph.get(index).getInvertedEdges();
        maxEdges = maxEdges.and(edges);
        int smallest = Integer.MAX_VALUE;
        for(int i = 0; i < maxEdges.length(); i++){
            if(maxEdges.get(i)){
                if(edges.getInteger(i) < smallest){
                    smallest = edges.getInteger(i);
                }
            }
        }
        for(int i = 0; i < edges.length(); i++){
            if(edges.getInteger(i) == smallest){
                edges.zero(i);
            }
        }

        return edges;
    }

    private static int findMax(NumericEdges numEdges) {
        int max = 0;
        for(int i = 0; i < numEdges.length(); i++){
            if(numEdges.getInteger(i) > max){
                max = numEdges.getInteger(i);
            }
        }
        return max;
    }

    private static NumericEdges calculateEdges(Graph graph, Edges edges) {
        NumericEdges numericEdges = new NumericEdges(edges);
        for(int i = 0; i < numericEdges.length(); i++){
            if(numericEdges.get(i)){
                numericEdges = (NumericEdges) numericEdges.add(graph.get(i).getInvertedEdges().and(edges));
            }
        }
        return numericEdges;
    }

    private static List<Node> findInitial(Graph graph){
        Node initialNode = findInitialNode(graph);
        List<Node> initialPairs = new ArrayList<Node>();
        Edges edges = initialNode.getEdges();
        for(int i = 0; i < edges.length(); i++){
            if(edges.get(i) || i == initialNode.getVertex()){
                Node currNode = graph.get(i);
                initialPairs.add(currNode);
            }
        }
        return initialPairs;
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
}
