package com.ser.algorithm;

import com.ser.graph.BooleanEdges;
import com.ser.graph.Edges;
import com.ser.graph.Graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bmr01 on 4/10/2016.
 */
public enum Triangle {
    ;


    public static Edges solve(Graph graph) {
        Edges cleansedEdges = new BooleanEdges(graph.size());
        List<Edges> initialEdges = new ArrayList<Edges>();
        for(int i = 0; i < graph.size(); i++){
            if(graph.get(i).getInvertedEdges().size() == graph.size()){
                cleansedEdges.set(i, true);
            } else if(initialEdges.size() == 0){
                initialEdges.add(graph.get(i).getInvertedEdges());
                for(int j = 0; j < graph.size(); j++){
                    if(i != j && graph.get(i).getEdge(j)){
                        initialEdges.add(graph.get(j).getInvertedEdges());
                    }
                }
            }
        }

        Edges solution = new BooleanEdges(graph.size());
        for(Edges edges : initialEdges){
            Edges currSolution = solve(graph, edges);
            if(currSolution.size() > solution.size()){
                solution = currSolution;
                System.out.println(solution.size() + " " + solution);
            }
        }
        return solution.or(cleansedEdges);
    }

    private static Edges solve(Graph graph, Edges edges){
        Edges solution = new BooleanEdges(graph.size());
        Edges[] things= cleanse(graph, edges);
        Edges cleansedEdges = things[0];
        edges = things[1];
        Edges neighbors = new BooleanEdges(graph.size());
        for(int i = 0; i < graph.size(); i++){
            if(edges.get(i)){
                neighbors = graph.get(i).getEdges().and(edges);
                neighbors.set(i, true);
                break;
            }
        }
        for(int i = 0; i < graph.size(); i++){
            if(neighbors.get(i)){
                Edges currSolution = finalSolve(graph, edges.and(graph.get(i).getInvertedEdges()));
                if(currSolution.size() > solution.size()){
                    solution = currSolution;
                }
            }
        }
        return solution.or(cleansedEdges);
    }

    private static Edges finalSolve(Graph graph, Edges edges){
        Edges[] things = cleanse(graph, edges);
        Edges cleansedEdges = things[0];
        edges = things[1];
        Edges solution = new BooleanEdges(graph.size());
        int independentSetSize = edges.size();
        int numOfEdges = calculateNumOfEdges(graph, edges);
        while(independentSetSize > 0){
            solution = new BooleanEdges(graph.size());
            if(numOfEdges >= calculateNecessaryNumOfEdges(independentSetSize)){
                Edges validEdges = calculateValidEdges(graph, edges, independentSetSize - 1);
                if(validEdges.size() >= independentSetSize){
                    validEdges = calculateValidEdges(graph, validEdges, independentSetSize - 1);
                    if(validEdges.size() >= independentSetSize){
                        int maxIndex = 0;
                        while(validEdges.size() > 0 && maxIndex >= 0){
                            maxIndex = calculateMaxIndex(graph, validEdges);
                            solution.set(maxIndex, true);
                            validEdges = validEdges.and(graph.get(maxIndex).getInvertedEdges());
                            validEdges.set(maxIndex, false);
                        }
                        if(solution.size() >= independentSetSize && graph.checkSolution(solution)){
                            return solution.or(cleansedEdges);
                        }
                    }
                }
            }
            independentSetSize--;
        }
        return solution.or(cleansedEdges);
    }

    private static int calculateMaxIndex(Graph graph, Edges validEdges) {
        int maxIndex = -1;
        for(int i = 0; i < graph.size(); i++){
            if(validEdges.get(i)){
                if(maxIndex < 0){
                    maxIndex = i;
                } else if(graph.get(maxIndex).getInvertedEdges().and(validEdges).size() < graph.get(i).getInvertedEdges().and(validEdges).size()){
                    maxIndex = i;
                }
            }
        }
        return maxIndex;
    }

    private static Edges calculateValidEdges(Graph graph, Edges edges, int validSize) {
        Edges validEdges = new BooleanEdges(graph.size());
        for(int i = 0; i < graph.size(); i++){
            if(edges.get(i) && graph.get(i).getInvertedEdges().and(edges).size() >= validSize){
                validEdges.set(i, true);
            }
        }
        return validEdges;
    }

    private static int calculateNecessaryNumOfEdges(int n){
        return (((n * n ) - n) / 2);
    }

    private static int calculateNumOfEdges(Graph graph, Edges edges) {
        int numOfEdges = 0;
        for(int i = 0; i < graph.size(); i++){
            numOfEdges += (graph.get(i).getEdges().and(edges)).size();
        }
        return numOfEdges;
    }

    private static Edges[] cleanse(Graph graph, Edges edges){
        Edges newEdges = new BooleanEdges(edges);
        Edges cleansedEdges = new BooleanEdges(graph.size());
        for(int i = 0; i < graph.size(); i++){
            if(edges.get(i) && graph.get(i).getInvertedEdges().and(edges).size() == edges.size()){
                cleansedEdges.set(i, true);
                newEdges.set(i, false);
            }
        }
        Edges[] returnEdges = new Edges[2];
        returnEdges[0] = cleansedEdges;
        returnEdges[1] = newEdges;
        return returnEdges;
    }
}
