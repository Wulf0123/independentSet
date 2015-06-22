package com.ser.graph;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Bradley
 * on 6/21/2015.
 */
public class NodeTest {
    @Test
    public void testNodeCreation(){
        Edges edge = new Edges(1);
        Node node = new Node(0, edge);
        Assert.assertEquals(0, node.getVertex());
        Assert.assertEquals(edge.get(0), !node.getInvertedEdges().get(0));
    }
}
