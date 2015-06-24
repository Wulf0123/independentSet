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

    @Test
    public void testGraph004() throws IOException {
        Graph graph = GraphReader.readGraph("graph004.txt");
        Edges solution = Flow.solve(graph);
        Assert.assertEquals(graph.getSolution().size(), solution.size());
        Assert.assertTrue(graph.checkSolution(solution));
        System.out.println(String.format("Graph-004: %s", solution));
    }

    @Test
    public void testGraph005() throws IOException {
        Graph graph = GraphReader.readGraph("graph005.txt");
        Edges solution = Flow.solve(graph);
        Assert.assertEquals(graph.getSolution().size(), solution.size());
        Assert.assertTrue(graph.checkSolution(solution));
        System.out.println(String.format("Graph-005: %s", solution));
    }

    @Test
    public void testGraph006() throws IOException {
        Graph graph = GraphReader.readGraph("graph006.txt");
        Edges solution = Flow.solve(graph);
        Assert.assertEquals(graph.getSolution().size(), solution.size());
        Assert.assertTrue(graph.checkSolution(solution));
        System.out.println(String.format("Graph-006: %s", solution));
    }

    @Test
    public void testGraph007() throws IOException {
        Graph graph = GraphReader.readGraph("graph007.txt");
        Edges solution = Flow.solve(graph);
        Assert.assertEquals(graph.getSolution().size(), solution.size());
        Assert.assertTrue(graph.checkSolution(solution));
        System.out.println(String.format("Graph-007: %s", solution));
    }

    @Test
    public void testGraph008() throws IOException {
        Graph graph = GraphReader.readGraph("graph008.txt");
        Edges solution = Flow.solve(graph);
        Assert.assertEquals(graph.getSolution().size(), solution.size());
        Assert.assertTrue(graph.checkSolution(solution));
        System.out.println(String.format("Graph-008: %s", solution));
    }

    @Test
    public void testGraph009() throws IOException {
        Graph graph = GraphReader.readGraph("graph009.txt");
        Edges solution = Flow.solve(graph);
        Assert.assertEquals(graph.getSolution().size(), solution.size());
        Assert.assertTrue(graph.checkSolution(solution));
        System.out.println(String.format("Graph-009: %s", solution));
    }

    @Test
    public void testGraph010() throws IOException {
        Graph graph = GraphReader.readGraph("graph010.txt");
        Edges solution = Flow.solve(graph);
        Assert.assertEquals(graph.getSolution().size(), solution.size());
        Assert.assertTrue(graph.checkSolution(solution));
        System.out.println(String.format("Graph-010: %s", solution));
    }

    @Test
    public void testGraph011() throws IOException {
        Graph graph = GraphReader.readGraph("graph011.txt");
        Edges solution = Flow.solve(graph);
        Assert.assertEquals(graph.getSolution().size(), solution.size());
        Assert.assertTrue(graph.checkSolution(solution));
        System.out.println(String.format("Graph-011: %s", solution));
    }

    @Test
    public void testGraph012() throws IOException {
        Graph graph = GraphReader.readGraph("graph012.txt");
        Edges solution = Flow.solve(graph);
        Assert.assertEquals(graph.getSolution().size(), solution.size());
        Assert.assertTrue(graph.checkSolution(solution));
        System.out.println(String.format("Graph-012: %s", solution));
    }

    @Test
    public void testGraph013() throws IOException {
        Graph graph = GraphReader.readGraph("graph013.txt");
        Edges solution = Flow.solve(graph);
        Assert.assertEquals(graph.getSolution().size(), solution.size());
        Assert.assertTrue(graph.checkSolution(solution));
        System.out.println(String.format("Graph-013: %s", solution));
    }

    @Test
    public void testGraph014() throws IOException {
        Graph graph = GraphReader.readGraph("graph014.txt");
        Edges solution = Flow.solve(graph);
        Assert.assertEquals(graph.getSolution().size(), solution.size());
        Assert.assertTrue(graph.checkSolution(solution));
        System.out.println(String.format("Graph-014: %s", solution));
    }

    @Test
    public void testGraph015() throws IOException {
        Graph graph = GraphReader.readGraph("graph015.txt");
        Edges solution = Flow.solve(graph);
        Assert.assertEquals(graph.getSolution().size(), solution.size());
        Assert.assertTrue(graph.checkSolution(solution));
        System.out.println(String.format("Graph-015: %s", solution));
    }

    @Test
    public void testGraph016() throws IOException {
        Graph graph = GraphReader.readGraph("graph016.txt");
        Edges solution = Flow.solve(graph);
        Assert.assertEquals(graph.getSolution().size(), solution.size());
        Assert.assertTrue(graph.checkSolution(solution));
        System.out.println(String.format("Graph-016: %s", solution));
    }

    @Test
    public void testGraph017() throws IOException {
        Graph graph = GraphReader.readGraph("graph017.txt");
        Edges solution = Flow.solve(graph);
        Assert.assertEquals(graph.getSolution().size(), solution.size());
        Assert.assertTrue(graph.checkSolution(solution));
        System.out.println(String.format("Graph-017: %s", solution));
    }

    @Test
    public void testGraph018() throws IOException {
        Graph graph = GraphReader.readGraph("graph018.txt");
        Edges solution = Flow.solve(graph);
        Assert.assertEquals(graph.getSolution().size(), solution.size());
        Assert.assertTrue(graph.checkSolution(solution));
        System.out.println(String.format("Graph-018: %s", solution));
    }

    @Test
    public void testGraph019() throws IOException {
        Graph graph = GraphReader.readGraph("graph019.txt");
        Edges solution = Flow.solve(graph);
        Assert.assertEquals(graph.getSolution().size(), solution.size());
        Assert.assertTrue(graph.checkSolution(solution));
        System.out.println(String.format("Graph-019: %s", solution));
    }

    @Test
    public void testGraph020() throws IOException {
        Graph graph = GraphReader.readGraph("graph020.txt");
        Edges solution = Flow.solve(graph);
        Assert.assertEquals(graph.getSolution().size(), solution.size());
        Assert.assertTrue(graph.checkSolution(solution));
        System.out.println(String.format("Graph-020: %s", solution));
    }
}