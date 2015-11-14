package com.ser.algorithm;

import com.ser.graph.BooleanEdges;
import com.ser.graph.Edges;
import com.ser.graph.Graph;
import com.ser.graph.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Bradley
 * on 6/27/2015.
 */
public enum FlowGraph {
    ;
    public static Edges solve(Graph graph){
        return solve(graph, findInitial(graph));
    }

    private static Edges solve(Graph graph, List<Node> initialNodes){
        Edges solution = new BooleanEdges(graph.size());
        while(initialNodes.size() > 0){
            Node currNode = initialNodes.remove(0);
            Edges currSolution = solve(graph, currNode);
            if(currSolution.size() > solution.size()){
                solution = currSolution;
            }
        }
        return solution;
    }

    private static Edges solve(Graph graph, Node currNode){
        return solve(graph, currNode, new BooleanEdges(graph.size()));
    }

    private static Edges solve(Graph graph, Node currNode, Edges solution){
        solution.set(currNode.getVertex(), true);
        Edges currEdges = currNode.getInvertedEdges();
        currEdges.set(currNode.getVertex(), false);
        return solve(graph, currEdges, solution);
    }

    private static Edges solve(Graph graph, Edges currEdges, Edges solution){
        List<Edges> solutions = new ArrayList<Edges>();
        for(int i = 0; i < currEdges.length(); i++){
            if(currEdges.get(i) && !solution.get(i)){
                Edges possibleSolution = new BooleanEdges(solution);
                possibleSolution.set(i, true);
                if(graph.checkSolution(possibleSolution)){
                    solutions.add(solve(graph, currEdges.and(graph.get(i).getInvertedEdges()), possibleSolution));
                }
            }
        }

        if(solutions.size() == 0){
            return solution;
        }
        return pickSolution(graph, solutions);
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

    private static Edges pickSolution(Graph graph, List<Edges> solutions){
        Edges solution = null;
        Map<String, Boolean> seenSolutions = new HashMap<String, Boolean>();
        for(Edges possibleSolution : solutions){
            if(seenSolutions.containsKey(possibleSolution.toString())){
                continue;
            }
            seenSolutions.put(possibleSolution.toString(), true);
            if(graph.checkSolution(possibleSolution)){
                if(solution == null || possibleSolution.size() > solution.size()){
                    solution = possibleSolution;
                }
            }
        }
        return solution;
    }

}