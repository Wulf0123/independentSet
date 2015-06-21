package com.ser.graph;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Created by Bradley
 * on 6/21/2015.
 */
public class EdgesTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();

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

    @Test
    public void testBadGet(){
        Edges edge = new Edges(0);
        exception.expect(ArrayIndexOutOfBoundsException.class);
        edge.get(0);
    }

    @Test
    public void testNegGet(){
        Edges edge = new Edges(1);
        exception.expect(ArrayIndexOutOfBoundsException.class);
        edge.get(-1);
    }

    @Test
    public void testSetEmpty(){
        Edges edge = new Edges(-1);
        edge.set(0, true);
        exception.expect(ArrayIndexOutOfBoundsException.class);
        edge.get(0);
    }

    @Test
    public void testSet(){
        Edges edge = new Edges(1);
        boolean value = edge.get(0);
        edge.set(0, true);
        Assert.assertFalse(value);
        Assert.assertTrue(edge.get(0));
    }

    @Test
    public void testInvert(){
        Edges edge = new Edges(1);
        Edges inverted = edge.inverted();
        Assert.assertEquals(edge.size(), inverted.size());
        Assert.assertFalse(edge.get(0));
        Assert.assertTrue(inverted.get(0));
    }
}