package com.ser.algorithm;

import com.ser.algorithm.com.ser.algorithm.helper.SolutionPair;
import com.ser.graph.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bradley
 * on 6/25/2015.
 */
public enum ZeroFlow {
    ;
    public static Edges solve(Graph graph){
        return solve(graph, findInitial(graph));
    }

    private static List<Edges> findInitial(Graph graph){
        Node initialNode = findInitialNode(graph);
        List<Edges> initialPairs = new ArrayList<Edges>();
        Edges edges = initialNode.getEdges();
        for(int i = 0; i < edges.length(); i++){
            if(edges.get(i) || i == initialNode.getVertex()){
                Node currNode = graph.get(i);
                Edges currEdge = currNode.getInvertedEdges();
                initialPairs.add(currEdge);
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

    private static Edges solve(Graph graph, List<Edges> initialEdges){
        Edges solution = new BooleanEdges(graph.size());
        while(initialEdges.size() > 0){
            Edges currEdges = initialEdges.remove(0);
            Edges currSolution = solve(graph, currEdges);
            if(currSolution.size() > solution.size()){
                solution = currSolution;
            }
        }
        return solution;
    }

    private static Edges solve(Graph graph, Edges currEdges){
        NumericEdges flow = new NumericEdges(graph.size());
        for(int i = 0; i < currEdges.length(); i++){
            if(currEdges.get(i)){
                Node currNode = graph.get(i);
                Edges nodeEdges = currNode.getEdges().and(currEdges);
                for(int j = 0; j < currEdges.length(); j++){
                    if(currEdges.get(j) && nodeEdges.get(j)){
                        flow.increment(j);
                    }
                    if(!currEdges.get(j)){
                        flow.set(j);
                        flow.negate(j);
                    }
                }
            }
        }
        if(flow.size() == 0){
            return currEdges;
        } else{
            return solve(graph, currEdges, flow);
        }
    }

    private static Edges solve(Graph graph, Edges currEdges, NumericEdges flow){
        int largestValue = flow.getInteger(0);
        for(int i = 1; i < flow.length(); i++){
            int currentValue = flow.getInteger(i);
            if(currentValue >= 0 && currentValue < largestValue || largestValue == -1){
                largestValue = currentValue;
            }
        }

        Edges solution = new BooleanEdges(graph.size());
        for(int i = 0; i < flow.length(); i++){
            if(flow.getInteger(i) == largestValue){
                Edges nextEdges = new BooleanEdges(currEdges);
                nextEdges.set(i, false);
                nextEdges = nextEdges.and(graph.get(i).getInvertedEdges());
//                Edges nextSolution = solve(graph, nextEdges);
//                if(nextSolution.size() > solution.size()){
//                    solution = nextSolution;
//                }
                solution = solve(graph, nextEdges);
                solution.set(i, true);
                return solution;
            }
        }
        return solution;
    }
}