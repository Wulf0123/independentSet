package com.ser.algorithm;

import com.ser.com.ser.exception.AlgorithmNotFoundException;
import com.ser.graph.Edges;
import com.ser.graph.Graph;

/**
 * Created by Bradley
 * on 6/21/2015.
 */
public enum Algorithm {
    ;
    public static Edges solve(Graph graph, String algorithmName) throws AlgorithmNotFoundException {
        if(algorithmName.equals("flow")){
            Flow.solve(graph);
        }
        throw new AlgorithmNotFoundException();
    }
}
