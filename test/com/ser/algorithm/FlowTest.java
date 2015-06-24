package com.ser.algorithm;

import com.ser.graph.Edges;
import com.ser.graph.Graph;
import com.ser.graph.GraphReader;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by Bradley
 * on 6/21/2015.
 */
public class FlowTest {

    @Test
    public void testGraph001() throws IOException {
        Graph graph = GraphReader.readGraph("graph001.txt");
        Edges solution = Flow.solve(graph);
        Assert.assertEquals(graph.getSolution().size(), solution.size());
        Assert.assertTrue(graph.checkSolution(solution));
        System.out.println(String.format("Graph-001: %s", solution));
    }

    @Test
    public void testGraph002() throws IOException {
        Graph graph = GraphReader.readGraph("graph002.txt");
        Edges solution = Flow.solve(graph);
        Assert.assertEquals(graph.getSolution().size(), solution.size());
        Assert.assertTrue(graph.checkSolution(solution));
        System.out.println(String.format("Graph-002: %s", solution));
    }

    @Test
    public void testGraph003() throws IOException {
        Graph graph = GraphReader.readGraph("graph003.txt");
        Edges solution = Flow.solve(graph);
        Assert.assertEquals(graph.getSolution().size(), solution.size());
        Assert.assertTrue(graph.checkSolution(solution));
        System.out.println(String.format("Graph-003: %s", solution));
    }
}
