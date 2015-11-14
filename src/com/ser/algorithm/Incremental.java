package com.ser.algorithm;

import com.ser.graph.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Bradley
 * on 10/11/2015.
 */
public enum Incremental {
    ;

    public static Edges solve(Graph graph) {
        List<Node> initialNodes = findInitialNodes(graph);
        return solve(graph, initialNodes);
    }

    private static Edges solve(Graph graph, List<Node> initialNodes) {
        Edges solution = new BooleanEdges(graph.size());
        sortNodes(initialNodes);
        for(Node node : initialNodes){
            Edges nodeSolution = solve(graph, node, solution);
            if(nodeSolution.size() > solution.size()){
                solution = nodeSolution;
            }
        }
        return solution;
    }

    private static Edges solve(Graph graph, Node node, Edges solution){
        for(int solutionSize = solution.size() + 1; solutionSize < graph.size(); solutionSize++){
            NumericEdges realizedEdges = findEdgeSize(graph, node.getInvertedEdges(), solutionSize);
            Edges currSolution = solve(graph, realizedEdges, solutionSize);
            if(currSolution.size() == 0){
                return solution;
            }
            else if(currSolution.size() > solution.size()){
                solution = currSolution;
            }
        }
        return solution;
    }

    private static Edges solve(Graph graph, Edges edges, int solutionSize){
        if(checkSolution(graph, edges, solutionSize)){
            return new BooleanEdges(edges);
        }
        return findSubset(graph, edges, solutionSize);
    }

    private static Edges findSubset(Graph graph, Edges edges, int solutionSize){
        if(edges.size() > 0 && solutionSize > 0) {
            for (int i = 0; i < edges.length(); i++) {
                if (edges.get(i)) {
                    Edges solution = new BooleanEdges(edges);
                    solution.set(i, false);
                    solution = solution.and(graph.get(i).getInvertedEdges());
                    solution = findSubset(graph, solution, solutionSize - 1);
                    solution.set(i, true);
                    if(solution.size() == solutionSize){
                        return solution;
                    }
                }
            }
        }

        return new BooleanEdges(graph.size());
    }

    private static boolean checkSolution(Graph graph, Edges edges, int solutionSize) {
        if(edges.size() == solutionSize) {
            for (int i = 0; i < edges.length(); i++) {
                if (edges.get(i)) {
                    edges = edges.and(graph.get(i).getInvertedEdges());
                }
            }
            if (edges.size() == solutionSize) {
                return true;
            }
        }
        return false;
    }

    private static NumericEdges findEdgeSize(Graph graph, Edges invertedEdges, int solutionSize){
        boolean zeroed = false;
        NumericEdges realizedEdges = findEdgeSize(graph, invertedEdges);
        if(realizedEdges.size() > 0) {
            for (int j = 0; j < realizedEdges.length(); j++) {
                if (realizedEdges.get(j) && realizedEdges.getInteger(j) < solutionSize) {
                    realizedEdges.zero(j);
                    zeroed = true;
                }
            }
            if (zeroed) {
                realizedEdges = findEdgeSize(graph, realizedEdges, solutionSize);
            }
        }
        return realizedEdges;
    }

    private static NumericEdges findEdgeSize(Graph graph, Edges invertedEdges) {
        NumericEdges totalEdges = new NumericEdges(graph.size());
        for(int i = 0; i < invertedEdges.length(); i++){
            if(invertedEdges.get(i)){
                NumericEdges andEdges = new NumericEdges(invertedEdges.and(graph.get(i).getInvertedEdges()));
                if(totalEdges.size() == 0){
                    totalEdges = andEdges;
                } else {
                    totalEdges = (NumericEdges) totalEdges.add(andEdges);
                }
            }
        }
        return totalEdges;
    }

    private static List<Node> findInitialNodes(Graph graph){
        List<Node> nodes = new ArrayList<Node>();
        //Replace this with any node and its neighbors
        for(Node node : graph.getNodes()){
            nodes.add(node);
        }
        return nodes;
    }

    private static void sortNodes(List<Node> nodes){
        nodes.sort(new NodeComparator());
    }

    private static class NodeComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            return o2.getInvertedEdges().size() - o1.getInvertedEdges().size();
        }

        @Override
        public boolean equals(Object obj) {
            return false;
        }
    }
}