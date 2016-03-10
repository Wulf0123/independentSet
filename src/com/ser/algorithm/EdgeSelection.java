package com.ser.algorithm;

import com.ser.graph.BooleanEdges;
import com.ser.graph.Edges;
import com.ser.graph.Graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brad
 * on 3/9/2016.
 */
public enum EdgeSelection {
    ;

    public static Edges solve(Graph graph){
        Edges sol = new BooleanEdges(graph.size());
        List<Edges> solutionSet = getInitialSolutionSet(graph);
        for(int i = 0; i < graph.size(); i++){
            for(int j = i + 1; j < graph.size(); j++){
                List<Edges> nextSolutionSet = new ArrayList<Edges>();
                for(Edges solution : solutionSet){
                    if(graph.get(i).getEdge(j)){
                        Edges left = new BooleanEdges(solution);
                        left.set(i, false);
                        solution.set(j, false);
                        if(graph.checkSolution(left)){
                            if(left.size() > sol.size()){
                                sol = left;
                            }
                        }else if(left.size() > sol.size()){
                            nextSolutionSet.add(left);
                        }
                        if(graph.checkSolution(solution)){
                            if(solution.size() > sol.size()){
                                sol = solution;
                            }
                        }else if(solution.size() > sol.size()){
                            nextSolutionSet.add(solution);
                        }
                    } else{
                        nextSolutionSet.add(solution);
                    }
                }
                solutionSet = nextSolutionSet;
            }
        }
        return sol;// getSolution(solutionSet);
    }

    private static List<Edges> getInitialSolutionSet(Graph graph) {
        List<Edges> solutionSet = new ArrayList<Edges>();
        solutionSet.add(graph.get(0).getInvertedEdges());
        Edges initialEdges = graph.get(0).getEdges();
        for(int i = 0; i < graph.size(); i++){
            if(initialEdges.get(i)){
                solutionSet.add(graph.get(i).getInvertedEdges());
            }
        }
        return solutionSet;
    }

    private static Edges getSolution(List<Edges> solutionSet) {
        Edges solution = solutionSet.get(0);
        for(Edges possibleSolution : solutionSet){
            if(possibleSolution.size() > solution.size()){
                solution = possibleSolution;
            }
        }
        return solution;
    }
}
