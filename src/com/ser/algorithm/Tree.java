package com.ser.algorithm;

import com.ser.graph.BooleanEdges;
import com.ser.graph.Edges;
import com.ser.graph.Graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bradley
 * on 11/8/2015.
 */
public enum Tree {
    ;

    public static Edges solve(Graph graph){
        final List<_NodeTree> trees = buildTreesFromIndex(0, new BooleanEdges(graph.size()).inverted(), graph);
        return solve(graph, trees);
    }

    private static Edges solve(final Graph graph, final List<_NodeTree> trees) {
        Edges solution = new BooleanEdges(graph.size());
        for(final _NodeTree tree : trees) {
            solution = solution.or(solve(graph, tree));
        }
        return solution;
    }

    private static Edges solve(final Graph graph, final _NodeTree tree) {
       final Edges root = tree.pop();
       final Edges treeNodes = tree.all();
       if(treeNodes == null) {
           return new BooleanEdges((graph.size()));
       }
       final Edges nodes = new BooleanEdges(treeNodes);
        final Edges firstNeighbors = tree.pop();
        final Edges indNodes = tree.all();
        final Edges independentNeighbors = tree.pop();
        int firstNeighborMaxTreeHeight = 0;
        List<_NodeTree> firstNeighborTrees = new ArrayList<_NodeTree>();
        int indNeighborMaxTreeHeight = 0;
        List<_NodeTree> independentNeighborTrees = new ArrayList<_NodeTree>();
        for(int i = 0 ; i < graph.size(); i++) {
            if(firstNeighbors != null && firstNeighbors.get(i)) {
                final List<_NodeTree> possibleFirstNeighborTrees = buildTreesFromIndex(i, nodes, graph);
                final int possibleHeight = calculateMaxPossibleSolution(possibleFirstNeighborTrees);
                if(possibleHeight > firstNeighborMaxTreeHeight) {
                    firstNeighborTrees = possibleFirstNeighborTrees;
                    firstNeighborMaxTreeHeight = possibleHeight;
                }
            }
            if(independentNeighbors != null && independentNeighbors.get(i)) {
                final List<_NodeTree> possibleIndNeighborTrees = buildTreesFromIndex(i, indNodes, graph);
                final int possibleHeight = calculateMaxPossibleSolution(possibleIndNeighborTrees);
                if(possibleHeight > indNeighborMaxTreeHeight) {
                    independentNeighborTrees = possibleIndNeighborTrees;
                    indNeighborMaxTreeHeight = possibleHeight;
                }
            }
        }
        if(firstNeighborMaxTreeHeight > indNeighborMaxTreeHeight) {
            return solve(graph, firstNeighborTrees);
        } else {
            return root.or(solve(graph, independentNeighborTrees));
        }
    }

    private static int calculateMaxPossibleSolution(final List<_NodeTree> trees) {
        int max = 0;
        for(final _NodeTree tree : trees) {
            max += tree.halfSize();
        }
        return max;
    }

    private static int findNodeWithMostEdges(final Graph graph) {
        int max = graph.get(0).getEdges().size();
        int index = 0;
        for(int i = 1; i < graph.size(); i++) {
            final int count = graph.get(i).getEdges().size();
            if(count > max) {
                max = count;
                index = i;
            }
        }
        return index;
    }

    private static List<_NodeTree> buildTreesFromIndex(final int index, final Edges nodeSet, final Graph graph) {
        final List<_NodeTree> trees = new ArrayList<_NodeTree>();
        Edges remainingNodes = new BooleanEdges(nodeSet);
        while(remainingNodes.size() > 0) {
            if(trees.size() == 0) {
                final _NodeTree root = buildTreeFromIndex(index, remainingNodes, graph);
                remainingNodes = remainingNodes.and(root.all().inverted());
                trees.add(root);
            } else {
                for ( int i = 0 ; i < graph.size(); i++) {
                    if(remainingNodes.get(i)) {
                        final _NodeTree root = buildTreeFromIndex(i, remainingNodes, graph);
                        remainingNodes = remainingNodes.and(root.all().inverted());
                        trees.add(root);
                    }
                }
            }
        }
        return trees;
    }

    private static _NodeTree buildTreeFromIndex(final int index, final Edges nodeSet, final Graph graph) {
        final _NodeTree tree = new _NodeTree();
        final Edges root = new BooleanEdges(graph.size());
        root.set(index, true);
        root.size();
        tree.push(root);

        Edges remainingNodes = new BooleanEdges(nodeSet);
        remainingNodes.set(index, false);
        Edges neighbors = findRemainingNeighbors(root, remainingNodes, graph);
        while(neighbors.size() > 0) {
            tree.push(neighbors);
            remainingNodes = remainingNodes.and(neighbors.inverted());
            neighbors = findRemainingNeighbors(neighbors, nodeSet.and(remainingNodes), graph);
        }
        if(remainingNodes.size() > 0) {

        }
        return tree;
    }

    private static Edges findRemainingNeighbors(final Edges roots, final Edges nodes, final Graph graph) {
        Edges neighbors = new BooleanEdges(graph.size());

        for(int i = 0; i < graph.size(); i++) {
            if(roots.get(i)) {
                neighbors = neighbors.or(graph.get(i).getEdges());
            }
        }
        return neighbors.and(nodes);
    }
}

class _NodeTree {
    private final List<Edges> branches = new ArrayList<>();

    public int size() {
        return branches.size();
    }

    public int halfSize() {
        return (int)Math.ceil((double)size() / 2);
    }

    public Edges get(final int i) {
        if(i < branches.size()) {
            return branches.get(i);
        }
        return null;
    }

    public Edges pop() {
        return branches.size() > 0 ? branches.remove(0) : null;
    }

    public void push(final Edges edges) {
        branches.add(edges);
    }

    public Edges all() {
        if(branches.size() == 0) {
            return null;
        }
        Edges seen = branches.get(0);
        for(int i = 1; i < branches.size(); i++) {
            seen = seen.or(branches.get(i));
        }
        return seen;
    }
//    private final int index;
//    private final Edges neighbors;
//    private final Edges previousNeighbors;
//    private final Edges availableNodes;
//    private final Edges solution;
//
//    public _NodeTree(final int index, final Edges neighbors, final Edges previousNeighbors, final Edges availableNodes, final Edges solution) {
//        this.index = index;
//        this.neighbors = neighbors;
//        this.previousNeighbors = previousNeighbors;
//        this.availableNodes = availableNodes;
//        this.solution = solution;
//    }

//    public Edges getNeighbors() {
//        return neighbors;
//    }
//
//    public Edges getPreviousNeighbors() {
//        return previousNeighbors;
//    }
//
//    public Edges getAvailableNodes() {
//        return availableNodes;
//    }
//
//    public Edges getSolution() {
//        return solution;
//    }

//    @Override
//    public String toString() {
//        return String.format("%d - %s", index, solution.toString());
//    }
}
