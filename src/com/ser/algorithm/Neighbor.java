package com.ser.algorithm;

import com.ser.collections.EdgesHeap;
import com.ser.graph.BooleanEdges;
import com.ser.graph.Edges;
import com.ser.graph.Graph;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Bradley
 * on 11/8/2015.
 */
public enum Neighbor {
    ;

    public static Edges solve(Graph graph){
        return solve(graph, (new BooleanEdges(graph.size()).inverted()), 0, new HashMap<String, Edges>());
    }

    private static Edges solve(final Graph graph, final Edges edges, final int solutionSize, final Map<String, Edges> solutions){
        Edges solution = null;
        if(edges.size() > solutionSize) {
            String key = edges.toString();
            if (solutions.containsKey(key)) {
                solution = solutions.get(key);
            } else {
                solution = completeAnd(graph, edges);
                EdgesHeap edgeHeap = selectNextEdge(graph, edges.and(solution.inverted()));
                solution = solution.or(solve(graph, edgeHeap, solutionSize-solution.size(), solutions));
                solutions.put(key, solution);
                if(solution.size() > 26){
                    System.out.println(solution);
                }
            }
        }
        return solution == null ? new BooleanEdges(graph.size()) : solution;
    }

    private static Edges solve(final Graph graph, final EdgesHeap heap, final int solutionSize, final Map<String, Edges> solutions){
        Edges solution = new BooleanEdges(graph.size());
        Edges edge;
        while((edge = heap.pop()) != null) {
            Edges currSolution = solve(graph, edge, solutions.size() > solutionSize ? solution.size() : solutionSize, solutions);
            if(currSolution.size() > solution.size()){
                solution = currSolution;
            }
        }
        return solution;
    }

    private static EdgesHeap selectNextEdge(final Graph graph, final Edges edges){
        EdgesHeap heap = new EdgesHeap();
        int selectedNode = -1;
        for(int i = 0; i < edges.length(); i++){
            if(edges.get(i)){
                if(selectedNode < 0){
                    selectedNode = i;
                } else if(selectEdge(graph, edges, i, selectedNode)){
                    selectedNode = i;
                }
            }
        }
        if(selectedNode >= 0){
            addToHeap(graph, heap, selectedNode, edges);
        }
        return heap;
    }

    private static boolean selectEdge(final Graph graph, final Edges edges, final int currIndex, final int selectedIndex){
        int currSize = graph.get(currIndex).getEdges().and(edges).size();
        int selectedSize = graph.get(selectedIndex).getEdges().and(edges).size();
        if(currSize < selectedSize){
            return true;
        } else if(currSize == selectedSize){
            if(graph.get(currIndex).getInvertedEdges().and(edges).size() > graph.get(selectedIndex).getInvertedEdges().and(edges).size()) {
                return true;
            }
        }
        return false;
    }

    private static void addToHeap(final Graph graph, final EdgesHeap heap, final int node, final Edges edges){
        heap.add(graph.get(node).getInvertedEdges().and(edges));
        Edges neighbors = edges.and(graph.get(node).getEdges());
        for(int i = 0; i < graph.size(); i++){
            if(neighbors.get(i)){
                heap.add(graph.get(i).getInvertedEdges().and(edges));
            }
        }
    }

    private static Edges completeAnd(Graph graph, Edges edge){
        Edges completeAnd = new BooleanEdges(edge);
        for(int i = 0; i < edge.length(); i++){
            if(edge.get(i)){
                completeAnd = completeAnd.and(graph.get(i).getInvertedEdges());
            }
        }
        return completeAnd;
    }
}
