package com.ser.algorithm.helper;

import com.ser.algorithm.com.ser.algorithm.helper.SolutionPair;
import com.ser.graph.Edges;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Bradley
 * on 6/23/2015.
 */
public class SolutionPairTest {
    private static Edges edges;
    private static Edges solution;

    @BeforeClass
    public static void setup(){
        edges = new Edges(3);
        edges.set(1, true);
        edges.set(2, true);
        solution = edges.inverted();
    }

    @Test
    public void testCreation(){
        SolutionPair pair = new SolutionPair(edges, solution);
        Assert.assertEquals(edges, pair.getEdges());
        Assert.assertEquals(solution, pair.getSolution());
    }

    @Test
    public void testSort(){
        final SolutionPair pair1 = new SolutionPair(edges, solution);
        final SolutionPair pair2 = new SolutionPair(solution, edges);
        List<SolutionPair> pairList = new ArrayList<SolutionPair>(){{add(pair1); add(pair2);}};
        Collections.sort(pairList, new SolutionPair.SolutionPairComparator());
        Assert.assertEquals(pair1.getEdges(), pairList.get(1).getEdges());
        Assert.assertEquals(pair2.getEdges(), pairList.get(0).getEdges());
    }
}
