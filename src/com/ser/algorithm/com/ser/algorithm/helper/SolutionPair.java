package com.ser.algorithm.com.ser.algorithm.helper;

import com.ser.graph.BooleanEdges;
import com.ser.graph.Edges;

import java.util.Comparator;


/**
 * Created by Bradley
 * on 6/23/2015.
 */
public class SolutionPair {
    private final Edges edges;
    private final Edges solution;

    public SolutionPair(Edges edges, Edges solution){
        this.edges = edges;
        this.solution = solution;
    }

    public Edges getEdges(){
        return edges;
    }

    public Edges getSolution(){
        return solution;
    }

    public int size(){
        return edges.size() + solution.size();
    }

    @Override
    public String toString(){
        return edges.toString();
    }

    public static class SolutionPairComparator implements Comparator<SolutionPair> {
        @Override
        public int compare(SolutionPair pair1, SolutionPair pair2) {
            int pair1size = pair1.getEdges().size();
            int pair2size = pair2.getEdges().size();
            return pair1size > pair2size ? 1 : pair1size < pair2size ? -1 : 0;
        }
    }
}
