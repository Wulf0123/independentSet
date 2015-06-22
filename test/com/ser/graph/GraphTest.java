package com.ser.graph;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by Bradley
 * on 6/21/2015.
 */
public class GraphTest {
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
}
