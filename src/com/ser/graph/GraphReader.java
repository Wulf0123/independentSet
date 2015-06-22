package com.ser.graph;

import com.ser.com.ser.util.FileUtility;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

/**
 * Created by Bradley
 * on 6/21/2015.
 */
public enum GraphReader {
    ;

    public static Graph readGraph(String fileName) throws IOException {
        List<String> lines = Files.readAllLines(FileUtility.getPathForResource(fileName));
        Node[] nodes = null;
        Edges solution = null;
        int i = 0;
        int size = 0;
        for(String line : lines){
            if(nodes == null){
                size = parseSize(line);
                solution = parseSolution(line);
                nodes = new Node[size];
            } else{
                nodes[i] = parseNode(size, i, line);
                i++;
            }
        }
        return new Graph(nodes, solution);
    }

    private static Node parseNode(int size, int vertex, String line){
        if(line != null && line.length() > 0){
            String[] tokens = line.split("\\s+");
            if(tokens.length > 1){
                Edges edge = new Edges(size);
                for(int i = 0; i < tokens.length; i++) {
                    if(tokens[i].equals("1")) {
                        edge.set(i, true);
                    }
                }
                return new Node(vertex, edge);
            }
        }
        return null;
    }

    private static int parseSize(String line){
        if(line != null && line.length() > 0){
            String[] tokens = line.split("\\s+");
            try{
                return Integer.parseInt(tokens[0]);
            } catch (Exception e){
                //TODO log this
            }
        }
        return 0;
    }

    private static Edges parseSolution(String line){
        if(line != null && line.length() > 0){
            String[] tokens = line.split("\\s+");
            if(tokens.length > 1){
                try{
                    int size = Integer.parseInt(tokens[0]);
                    int solutionSize = Integer.parseInt(tokens[1]);
                    if(tokens.length == solutionSize + 2){
                        Edges solution = new Edges(size);
                        for(int i = 2; i < tokens.length; i++) {
                            int j = Integer.parseInt(tokens[i]);
                            solution.set(j, true);
                        }
                        return solution;
                    }
                } catch(Exception e){
                    //TODO log this
                }
            }
        }
        return null;
    }
}
