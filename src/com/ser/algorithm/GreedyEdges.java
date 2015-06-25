package com.ser.algorithm;

import com.ser.graph.Edges;
import com.ser.graph.Graph;

/**
 * Created by Bradley
 * on 6/24/2015.
 */
public enum GreedyEdges {
    ;

    public static Edges solve(Graph graph){

        return null;
    }
//    Edges allEdges = new Edges(_Graph.size());
//    for(int i = 0; i < _Graph.size(); i++){
//        allEdges.set(i);
//    }
//    while(allEdges.size() > 0) {
//        boolean found = false;
//        int min = 0;
//        int minVector = 0;
//        for (int i = 1; i < _Graph.size(); i++) {
//            if(allEdges.get(i) == 0){
//                continue;
//            }
//            Vector curr = _Graph.get(i);
//            if(found == false){
//                found = true;
//                min = curr.size();
//                minVector = i;
//            }
//
//            if (curr.size() < min) {
//                min = curr.size();
//                minVector = i;
//            } else if (curr.size() == min) {
//                if (secondEdgesOf(curr) > secondEdgesOf(_Graph.get(minVector))) {
//                    min = curr.size();
//                    minVector = i;
//                }
//            }
//        }
//        _Solution.set(minVector);
//        allEdges.zero(minVector);
//        Edges currEdges = _Graph.get(minVector).getEdges();
//        for (int j = 0; j < _Graph.size(); j++) {
//            if (currEdges.get(j) != 0) {
//                allEdges.zero(j);
//            }
//        }
//    }
}
