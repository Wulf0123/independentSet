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
       // return solve(graph, initialPairs, new Edges(graph.size()), new HashMap<String, Edges>());
        return solve(graph, initialPairs);
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

    private static Edges solve(Graph graph, List<SolutionPair> solutions){
        Edges solution = new Edges(graph.size());
        while(solutions.size() > 0) {
            SolutionPair current = solutions.remove(0);
            Edges currentSolution = solve(graph, current);
            if(currentSolution.size() > solution.size()){
                solution = currentSolution;
            }
        }
        return solution;
    }

    private static Edges solve(Graph graph, SolutionPair currentNode){
        Edges solution = currentNode.getSolution();

        Edges thing = currentNode.getEdges();
        for(int i = 0; i < thing.length(); i++){
            if(thing.get(i)){
                Edges thisSolution = new Edges(currentNode.getSolution());
                thisSolution.set(i, true);
                Edges thisThing = currentNode.getEdges().and(graph.get(i).getInvertedEdges());
                thisThing.set(i, false);
                SolutionPair nextPair = new SolutionPair(thisThing, thisSolution);
                thisSolution = solve(graph, nextPair);
                if(thisSolution.size() > solution.size()){
                    solution = thisSolution;
                }
            }
        }

        return solution;
    }
}