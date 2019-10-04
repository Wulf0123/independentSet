package com.ser.algorithm;

import com.ser.graph.BooleanEdges;
import com.ser.graph.Edges;
import com.ser.graph.Graph;
import com.ser.graph.GraphReader;
import com.ser.time.StopWatch;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by Bradley 
 * on 9/25/2015.
 */
public class ClosestNeighborTest {
    Edges.Reader edgesReader = new BooleanEdges.BooleanReader();
    private static StopWatch stopWatch = new StopWatch();

    @BeforeClass
    public static void setup(){
        stopWatch.start();
    }

    @AfterClass
    public static void teardown(){
        System.out.println(String.format("Tests took: %s s", (double)stopWatch.elapsedTime()/1000));
    }

    @Test
    public void testGraph001() throws IOException {
        Graph graph = GraphReader.readGraph("graph001.txt", edgesReader);
        Edges solution = ClosetNeighbor.solve(graph);
        Assert.assertEquals(graph.getSolution().size(), solution.size());
        Assert.assertTrue(graph.checkSolution(solution));
        System.out.println(String.format("Graph-001: %s", solution));
    }

    //@Ignore
    @Test
    public void testGraph002() throws IOException {
        Graph graph = GraphReader.readGraph("graph002.txt", edgesReader);
        Edges solution = ClosetNeighbor.solve(graph);
        Assert.assertEquals(graph.getSolution().size(), solution.size());
        Assert.assertTrue(graph.checkSolution(solution));
        System.out.println(String.format("Graph-002: %s", solution));
    }

    //@Ignore
    @Test
    public void testGraph003() throws IOException {
        Graph graph = GraphReader.readGraph("graph003.txt", edgesReader);
        Edges solution = ClosetNeighbor.solve(graph);
        Assert.assertEquals(graph.getSolution().size(), solution.size());
        Assert.assertTrue(graph.checkSolution(solution));
        System.out.println(String.format("Graph-003: %s", solution));
    }

    //@Ignore
    @Test
    public void testGraph004() throws IOException {
        Graph graph = GraphReader.readGraph("graph004.txt", edgesReader);
        Edges solution = ClosetNeighbor.solve(graph);
        Assert.assertEquals(graph.getSolution().size(), solution.size());
        Assert.assertTrue(graph.checkSolution(solution));
        System.out.println(String.format("Graph-004: %s", solution));
    }

    //@Ignore
    @Test
    public void testGraph005() throws IOException {
        Graph graph = GraphReader.readGraph("graph005.txt", edgesReader);
        Edges solution = ClosetNeighbor.solve(graph);
        Assert.assertEquals(graph.getSolution().size(), solution.size());
        Assert.assertTrue(graph.checkSolution(solution));
        System.out.println(String.format("Graph-005: %s", solution));
    }

    //@Ignore
    @Test
    public void testGraph006() throws IOException {
        Graph graph = GraphReader.readGraph("graph006.txt", edgesReader);
        Edges solution = ClosetNeighbor.solve(graph);
        Assert.assertEquals(graph.getSolution().size(), solution.size());
        Assert.assertTrue(graph.checkSolution(solution));
        System.out.println(String.format("Graph-006: %s", solution));
    }

    //@Ignore
    @Test
    public void testGraph007() throws IOException {
        Graph graph = GraphReader.readGraph("graph007.txt", edgesReader);
        Edges solution = ClosetNeighbor.solve(graph);
        Assert.assertEquals(graph.getSolution().size(), solution.size());
        Assert.assertTrue(graph.checkSolution(solution));
        System.out.println(String.format("Graph-007: %s", solution));
    }

    //@Ignore
    @Test
    public void testGraph008() throws IOException {
        Graph graph = GraphReader.readGraph("graph008.txt", edgesReader);
        Edges solution = ClosetNeighbor.solve(graph);
        Assert.assertEquals(graph.getSolution().size(), solution.size());
        Assert.assertTrue(graph.checkSolution(solution));
        System.out.println(String.format("Graph-008: %s", solution));
    }

    //@Ignore
    @Test
    public void testGraph009() throws IOException {
        Graph graph = GraphReader.readGraph("graph009.txt", edgesReader);
        Edges solution = ClosetNeighbor.solve(graph);
        Assert.assertEquals(graph.getSolution().size(), solution.size());
        Assert.assertTrue(graph.checkSolution(solution));
        System.out.println(String.format("Graph-009: %s", solution));
    }

    //@Ignore
    @Test
    public void testGraph010() throws IOException {
        Graph graph = GraphReader.readGraph("graph010.txt", edgesReader);
        Edges solution = ClosetNeighbor.solve(graph);
        Assert.assertEquals(graph.getSolution().size(), solution.size());
        Assert.assertTrue(graph.checkSolution(solution));
        System.out.println(String.format("Graph-010: %s", solution));
    }

    //@Ignore
    @Test
    public void testGraph011() throws IOException {
        Graph graph = GraphReader.readGraph("graph011.txt", edgesReader);
        Edges solution = ClosetNeighbor.solve(graph);
        Assert.assertEquals(graph.getSolution().size(), solution.size());
        Assert.assertTrue(graph.checkSolution(solution));
        System.out.println(String.format("Graph-011: %s", solution));
    }

    //@Ignore
    @Test
    public void testGraph012() throws IOException {
        Graph graph = GraphReader.readGraph("graph012.txt", edgesReader);
        Edges solution = ClosetNeighbor.solve(graph);
        Assert.assertEquals(graph.getSolution().size(), solution.size());
        Assert.assertTrue(graph.checkSolution(solution));
        System.out.println(String.format("Graph-012: %s", solution));
    }

    //@Ignore
    @Test
    public void testGraph013() throws IOException {
        Graph graph = GraphReader.readGraph("graph013.txt", edgesReader);
        Edges solution = ClosetNeighbor.solve(graph);
        Assert.assertEquals(graph.getSolution().size(), solution.size());
        Assert.assertTrue(graph.checkSolution(solution));
        System.out.println(String.format("Graph-013: %s", solution));
    }

    //@Ignore
    @Test
    public void testGraph014() throws IOException {
        Graph graph = GraphReader.readGraph("graph014.txt", edgesReader);
        Edges solution = ClosetNeighbor.solve(graph);
        Assert.assertEquals(graph.getSolution().size(), solution.size());
        Assert.assertTrue(graph.checkSolution(solution));
        System.out.println(String.format("Graph-014: %s", solution));
    }

    //@Ignore
    @Test
    public void testGraph015() throws IOException {
        Graph graph = GraphReader.readGraph("graph015.txt", edgesReader);
        Edges solution = ClosetNeighbor.solve(graph);
        Assert.assertEquals(graph.getSolution().size(), solution.size());
        Assert.assertTrue(graph.checkSolution(solution));
        System.out.println(String.format("Graph-015: %s", solution));
    }

    //@Ignore
    @Test
    public void testGraph016() throws IOException {
        Graph graph = GraphReader.readGraph("graph016.txt", edgesReader);
        Edges solution = ClosetNeighbor.solve(graph);
        Assert.assertEquals(graph.getSolution().size(), solution.size());
        Assert.assertTrue(graph.checkSolution(solution));
        System.out.println(String.format("Graph-016: %s", solution));
    }

    //@Ignore
    @Test
    public void testGraph017() throws IOException {
        Graph graph = GraphReader.readGraph("graph017.txt", edgesReader);
        Edges solution = ClosetNeighbor.solve(graph);
        Assert.assertEquals(graph.getSolution().size(), solution.size());
        Assert.assertTrue(graph.checkSolution(solution));
        System.out.println(String.format("Graph-017: %s", solution));
    }

    //@Ignore
    @Test
    public void testGraph018() throws IOException {
        Graph graph = GraphReader.readGraph("graph018.txt", edgesReader);
        Edges solution = ClosetNeighbor.solve(graph);
//        Assert.assertEquals(graph.getSolution().size(), solution.size());
        //Assert.assertTrue(graph.checkSolution(solution));
        System.out.println(String.format("Graph-018: %s", solution));
        Assert.assertEquals(graph.getSolution().size(), solution.size());
    }

    //@Ignore
    @Test
    public void testGraph019() throws IOException {
        Graph graph = GraphReader.readGraph("graph019.txt", edgesReader);
        Edges solution = ClosetNeighbor.solve(graph);
        Assert.assertEquals(graph.getSolution().size(), solution.size());
        Assert.assertTrue(graph.checkSolution(solution));
        System.out.println(String.format("Graph-019: %s", solution));
    }

    //@Ignore
    @Test
    public void testGraph020() throws IOException {
        Graph graph = GraphReader.readGraph("graph020.txt", edgesReader);
        Edges solution = ClosetNeighbor.solve(graph);
        Assert.assertEquals(graph.getSolution().size(), solution.size());
        Assert.assertTrue(graph.checkSolution(solution));
        System.out.println(String.format("Graph-020: %s", solution));
    }

    @Test
    public void testGraph021() throws IOException {
        Graph graph = GraphReader.readGraph("graph021.txt", edgesReader);
        Edges solution = ClosetNeighbor.solve(graph);
        Assert.assertEquals(graph.getSolution().size(), solution.size());
        Assert.assertTrue(graph.checkSolution(solution));
        System.out.println(String.format("Graph-021: %s", solution));
    }

    @Test
    public void testGraph022() throws IOException {
        Graph graph = GraphReader.readGraph("graph022.txt", edgesReader);
        Edges solution = ClosetNeighbor.solve(graph);
        Assert.assertEquals(graph.getSolution().size(), solution.size());
        Assert.assertTrue(graph.checkSolution(solution));
        System.out.println(String.format("Graph-022: %s", solution));
    }
}
