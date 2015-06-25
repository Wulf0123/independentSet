package com.ser.graph;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by Bradley
 * on 6/21/2015.
 */
public class GraphReaderTest {
    private static String graphFile = "graph001.txt";

    @Test
    public void testGraphReader() throws IOException {
        Graph graph = GraphReader.readGraph(graphFile, new BooleanEdges.BooleanReader());
        Assert.assertNotNull(graph);
        Assert.assertEquals(4, graph.size());
    }
}
