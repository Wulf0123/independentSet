package com.ser.algorithm;

import com.ser.graph.BooleanEdges;
import com.ser.graph.Edges;
import com.ser.graph.Graph;
import com.ser.graph.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bmr01
 * on 3/23/2016.
 */
public enum Triplet {
    ;

    public static Edges solve(Graph graph) {
        List<Edges> initialEdges = getInitialSolutionSet(graph);
        Edges solution = new BooleanEdges(graph.size());
        for(Edges edges : initialEdges){
            Edges currSolution = solve(graph, edges);
            if(currSolution.size() > solution.size()){
                solution = currSolution;
                System.out.println(solution.size() + " " + solution);
            }
        }
        return solution;
    }

    public static Edges solve(Graph graph, Edges edges){
        List<Edges> triplets = findTriplets(graph, edges);
        return solve(graph, edges, triplets);
    }

    public static Edges solve(Graph graph, Edges edges, List<Edges> triplets){
        Edges solution = new BooleanEdges(graph.size());
        int maxSeen = 0;
        Edges maxSeenTriplet;
        List<Edges> toDelete = new ArrayList<Edges>();
        do {
            maxSeenTriplet = null;
            for (Edges triplet : triplets) {
                if(solution.and(triplet).size() == triplet.size() || edges.and(triplet).size() != triplet.size()){
                    toDelete.add(triplet);
                    continue;
                }
                int seen = 0;
                for (int i = 0; i < edges.length(); i++) {
                    if (edges.get(i)) {
                        Edges currEdges = graph.get(i).getInvertedEdges();
                        Edges anded = currEdges.and(triplet);
                        if (anded.size() == triplet.size()) {
                            seen++;
                        }
                    }
                }
                if (seen > maxSeen) {
                    maxSeen = seen;
                    maxSeenTriplet = triplet;
                } else if(seen == 0){
                    toDelete.add(triplet);
                }
            }
            if(maxSeenTriplet != null) {
                triplets.remove(maxSeenTriplet);
                solution = solution.or(maxSeenTriplet);
                for(int i = 0; i < solution.length(); i++){
                    if(solution.get(i)){
                        edges = edges.and(graph.get(i).getInvertedEdges());
                    }
                }
                for(Edges delete : toDelete){
                    triplets.remove(delete);
                }
                toDelete.clear();
                maxSeen = 0;
            }
        }while(maxSeenTriplet != null);
        return solution;
    }

    private static List<Edges> findTriplets(Graph graph, Edges edges) {
        List<Edges> triplets = new ArrayList<Edges>();
        for(int i = 0; i < edges.length(); i++){
            if(edges.get(i)){
                Edges triplet = new BooleanEdges(graph.size());
                triplet.set(i, true);
                for(int j = i + 1; j < edges.length(); j++){
                    if(edges.get(j)){
                        Edges secondTriplet = new BooleanEdges(triplet);
                        secondTriplet.set(j, true);
                        Edges anded = edges.and(graph.get(j).getInvertedEdges()).and(graph.get(i).getInvertedEdges());
                        for(int k = j + 1; k < edges.length(); k++){
                            if(anded.get(k)){
                                Edges nextTriplet = new BooleanEdges(secondTriplet);
                                nextTriplet.set(k, true);
                                triplets.add(nextTriplet);
                            }
                        }
                    }
                }
            }
        }
        return triplets;
    }

    private static List<Edges> getInitialSolutionSet(Graph graph) {
        List<Edges> solutionSet = new ArrayList<Edges>();
        Node initialNode = findLargestIndex(graph);
        solutionSet.add(initialNode.getInvertedEdges());
        Edges initialEdges = initialNode.getEdges();
        for(int i = 0; i < graph.size(); i++){
            if(initialEdges.get(i)){
                solutionSet.add(graph.get(i).getInvertedEdges());
            }
        }
        return solutionSet;
    }

    private static Node findLargestIndex(Graph graph) {
        Node node = graph.get(0);
        for(int i = 1; i < graph.size(); i++){
            if(graph.get(i).getEdges().size() < node.getEdges().size()){
                node = graph.get(i);
            }
        }
        return node;
    }
}
