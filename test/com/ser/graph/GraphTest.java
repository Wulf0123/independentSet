package com.ser.graph;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by Bradley
 * on 6/21/2015.
 */
public class GraphTest {
    private static String graphFile = "graph001.txt";
    private static Node[] nodes;

    @BeforeClass
    public static void setup(){
        nodes = new Node[2];
        Edges edge = new Edges(2);
        nodes[0] = new Node(0, edge);
        nodes[1] = new Node(1, edge);
    }

    @Test
    public void testGraphCreation(){
        Graph graph = new Graph(nodes);
        Node[] graphNodes = graph.getNodes();
        Assert.assertNotNull(graphNodes);
        for(int i = 0; i < nodes.length; i++){
            Assert.assertEquals(nodes[i], graphNodes[i]);
        }
    }

    @Test
    public void testCheckBadSolution() throws IOException {
        Graph graph = GraphReader.readGraph(graphFile);
        Edges solution = new Edges(graph.size());
        Assert.assertFalse(graph.checkSolution(solution.inverted()));
    }

    @Test
    public void testCheckGoodSolution() throws IOException {
        Graph graph = GraphReader.readGraph(graphFile);
        Assert.assertTrue(graph.checkSolution(graph.getSolution()));
    }
}
