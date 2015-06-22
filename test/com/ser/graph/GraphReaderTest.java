package com.ser.graph;

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
        GraphReader.readGraph(graphFile);
    }
}
