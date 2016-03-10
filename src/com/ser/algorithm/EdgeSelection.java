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
        List<Edges> solutionSet = new ArrayList<Edges>();
        solutionSet.add(new BooleanEdges(graph.size()).inverted());
        for(int i = 0; i < graph.size(); i++){
            for(int j = i + 1; j < graph.size(); j++){
                List<Edges> nextSolutionSet = new ArrayList<Edges>();
                for(Edges solution : solutionSet){
                    if(graph.get(i).getEdge(j)){
                        Edges left = new BooleanEdges(solution);
                        Edges right = new BooleanEdges(solution);
                        left.set(i, false);
                        right.set(j, false);
                        if(graph.checkSolution(left)){
                            if(left.size() > sol.size()){
                                sol = left;
                            }
                        }else {
                            nextSolutionSet.add(left);
                        }
                        if(graph.checkSolution(right)){
                            if(right.size() > sol.size()){
                                sol = right;
                            }
                        }else {
                            nextSolutionSet.add(right);
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
