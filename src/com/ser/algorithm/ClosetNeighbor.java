package com.ser.algorithm;

import com.ser.graph.BooleanEdges;
import com.ser.graph.Edges;
import com.ser.graph.Graph;

import java.util.ArrayList;
import java.util.List;


public enum ClosetNeighbor {
    ;

    public static Edges solve(Graph graph) {
        final Edges neighbors = new BooleanEdges(graph.size());
        final Edges neighborsPreviouslySeen = new BooleanEdges(graph.size());
        final Edges nodesAvailable = new BooleanEdges(graph.size()).inverted();
        final Edges solution = new BooleanEdges(graph.size());

        return solve(graph, List.of(new EdgeSet(neighbors, neighborsPreviouslySeen, nodesAvailable, solution)));
    }

    private static Edges solve(final Graph graph, final List<EdgeSet> sets) {
        final List nextSets = new ArrayList();
        Edges solution = new BooleanEdges(graph.size());

        for(final EdgeSet set : sets) {
            if(set.getAvailableNodes().size() == 0 && set.getSolution().size() > solution.size()) {
                solution = set.getSolution();
                continue;
            }
            final Edges neighbors = set.getNeighbors();
            final Edges neighborsPreviouslySeen = set.getPreviousNeighbors();
            final Edges nodesAvailable = set.getAvailableNodes();

            final Edges edges = neighbors.size() > 0 ? neighbors : neighborsPreviouslySeen.size() > 0 ? neighborsPreviouslySeen : nodesAvailable;

            final int[] minMaxIndexesWithEdgeCount = findMinAndMaxIndexWithTotalEdgeCount(graph, edges, nodesAvailable);

            if(minMaxIndexesWithEdgeCount == null) {
                continue;
            }

            nextSets.addAll(calculateNextSets(graph, minMaxIndexesWithEdgeCount[0], set));

            return solve(graph, nextSets);
        }

        return solution;
    }

    private static int[] findMinAndMaxIndexWithTotalEdgeCount(final Graph graph, final Edges edges, final Edges nodesAvailable) {
        if(edges.size() == 0) {
            return null;
        }
        Integer maxEdgeValue = null;
        Integer maxEdgeIndex = null;
        Integer minEdgeValue = null;
        Integer minEdgeIndex = null;
        int totalEdgeCount = 0;
        for (int i = 0; i < graph.size(); i++) {
            if (edges.get(i)) {
                final int edgeCount = graph.get(i).getEdges().and(nodesAvailable).size();
                totalEdgeCount += edgeCount;
                if (maxEdgeIndex == null || edgeCount > maxEdgeValue) {
                    maxEdgeIndex = i;
                    maxEdgeValue = edgeCount;
                }
                if (minEdgeIndex == null || edgeCount < minEdgeValue) {
                    minEdgeIndex = i;
                    minEdgeValue = edgeCount;
                }
            }
        }
        return maxEdgeIndex == null || minEdgeIndex == null ? null : new int[]{minEdgeIndex, maxEdgeIndex, totalEdgeCount};
    }
    private static List<EdgeSet> calculateNextSets(final Graph graph, final int index, final EdgeSet set) {
        final List sets = new ArrayList();
        sets.add(calculateNextSet(graph, index, set));

        final Edges neighbors = graph.get(index).getEdges().and(set.getAvailableNodes()).and(set.getAvailableNodes());

        for(int i = 0; i < graph.size(); i++) {
            if(!neighbors.get(i)){
                continue;
            }
            sets.add(calculateNextSet(graph, i, set));
        }

        return sets;
    }

    private static EdgeSet calculateNextSet(final Graph graph, final int index, final EdgeSet set) {
        final Edges neighbors = graph.get(index).getEdges();
        final Edges nextNeighbors = new BooleanEdges(graph.size());
        for(int i = 0; i < graph.size(); i++) {
            if(neighbors.get(i)) {
                nextNeighbors.or(graph.get(i).getEdges().and(set.getAvailableNodes()));
            }
        }

        final Edges availableNodes = graph.get(index).getInvertedEdges().and(set.getAvailableNodes());
        availableNodes.set(index, false);

        final Edges neighborsPreviouslySeen = (set.getPreviousNeighbors().and(availableNodes)).or(set.getNeighbors().and(set.getAvailableNodes()));

        final Edges solution = new BooleanEdges(set.getSolution());
        solution.set(index, true);

        return new EdgeSet(nextNeighbors, neighborsPreviouslySeen, availableNodes, solution);
    }
}

class EdgeSet {
    private final Edges neighbors;
    private final Edges previousNeighbors;
    private final Edges availableNodes;
    private final Edges solution;

    public EdgeSet(final Edges neighbors, final Edges previousNeighbors, final Edges availableNodes, final Edges solution) {
        this.neighbors = neighbors;
        this.previousNeighbors = previousNeighbors;
        this.availableNodes = availableNodes;
        this.solution = solution;
    }

    public Edges getNeighbors() {
        return neighbors;
    }

    public Edges getPreviousNeighbors() {
        return previousNeighbors;
    }

    public Edges getAvailableNodes() {
        return availableNodes;
    }

    public Edges getSolution() {
        return solution;
    }

    @Override
    public String toString() {
        return solution.toString();
    }
}
