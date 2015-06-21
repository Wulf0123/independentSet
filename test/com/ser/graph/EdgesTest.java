package com.ser.graph;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Bradley
 * on 6/21/2015.
 */
public class EdgesTest {
    @Test
    public void testEdgesCreation(){
        Edges edge = new Edges(0);
        Assert.assertEquals(0, edge.size());
    }

    @Test
    public void testEdgesBadCreation(){
        Edges edge = new Edges(-1);
        Assert.assertEquals(0, edge.size());
    }
}