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
        Assert.assertEquals(0, edge.length());
    }

    @Test
    public void testEdgesBadCreation(){
        Edges edge = new Edges(-1);
        Assert.assertEquals(0, edge.length());
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
        Assert.assertEquals(edge.length(), inverted.length());
        Assert.assertFalse(edge.get(0));
        Assert.assertTrue(inverted.get(0));
    }

    @Test
    public void testCopyConstructor(){
        Edges edge = new Edges(1);
        edge.set(0, true);
        Edges edge2 = new Edges(edge);
        Assert.assertEquals(edge, edge2);
    }

    @Test
    public void testEquals(){
        Edges edge = new Edges(1);
        Edges edge2 = new Edges(1);
        Assert.assertEquals(edge, edge2);
    }

    @Test
    public void testNotEquals(){
        Edges edge = new Edges(1);
        Edges edge2 = new Edges(1);
        edge2.set(0, true);
        Assert.assertNotEquals(edge, edge2);
    }

    @Test
    public void testToString(){
        Edges edge = new Edges(0);
        Assert.assertEquals("", edge.toString());
    }

    @Test
    public void testToString2(){
        Edges edge = new Edges(2);
        edge.set(1, true);
        Assert.assertEquals("0 1", edge.toString());
    }

    @Test
    public void testSizeOne(){
        Edges edge = new Edges(10);
        Assert.assertEquals(0, edge.size());
    }

    @Test
    public void testSizeTwo(){
        Edges edge = new Edges(10);
        edge.set(2, true);
        edge.set(5, true);
        Assert.assertEquals(2, edge.size());
    }
}