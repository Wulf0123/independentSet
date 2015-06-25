package com.ser.graph;

/**
 * Created by Bradley
 * on 6/24/2015.
 */
public class NumericEdges implements Edges {
    private int size;
    private int[] edges;

    public NumericEdges(int size) {
        size = 0;
        edges = new int[size];
    }

    public NumericEdges(int size, String data) {
        initialize(size, data);
    }

    public NumericEdges(Edges toCopy) {
        size = 0;
        int length = toCopy.length();
        this.size = toCopy.size();
        this.edges = new int[length];
        for (int i = 0; i < length; i++) {
            if(toCopy instanceof NumericEdges){
                this.edges[i] = ((NumericEdges) toCopy).getInteger(i);
            }
            else if(toCopy.get(i)){
                this.edges[i] = 1;
            }
        }
    }

    protected void initialize(int size, String data) {
        edges = new int[size];
        String[] tokens = data.split("[ ]+");
        if (tokens.length == size) {
            for (int i = 0; i < size; i++) {
                edges[i] = Integer.parseInt(tokens[i]);
                if (edges[i] != 0) {
                    size++;
                }
            }
        }
    }

    public Edges abs() {
        for (int i = 0; i < edges.length; i++) {
            if (edges[i] < 0) {
                set(i);
            }
        }
        return this;
    }

    public Edges and(Edges toAnd) {
        NumericEdges andEdges = new NumericEdges(this.edges.length);
        for (int i = 0; i < edges.length; i++) {
            if (this.edges[i] == 0 || ((NumericEdges)toAnd).getInteger(i) == 0) {
                andEdges.zero(i);
            } else if (this.edges[i] < 0 || ((NumericEdges)toAnd).edges[i] < 0) {
                andEdges.negate(i);
            } else {
                if (this.edges[i] > ((NumericEdges)toAnd).edges[i]) {
                    andEdges.set(i);
                } else {
                    andEdges.set(i);
                }
            }
        }
        return andEdges;
    }

    public Edges add(Edges toAdd) {
        NumericEdges Summation = new NumericEdges(this.edges.length);
        for (int i = 0; i < this.edges.length; i++) {
            int leftEdge = this.edges[i];
            int rightEdge = ((NumericEdges)toAdd).edges[i];
            if (this.edges[i] == 0) {
                Summation.zero(i);
            } else if (leftEdge < 0 || rightEdge < 0) {
                Summation.negate(i);
            } else {
                Summation.edges[i] = leftEdge + rightEdge;
            }
        }
        return Summation;
    }

    public int getInteger(int edge) {
        return edges[edge];
    }

    public void set(int edge) {
        if (edge < edges.length) {
            if (edges[edge] == 0) {
                edges[edge] = 1;
                size++;
            } else {
                edges[edge] = 1;
            }
        }
    }

    public void zero(int edge) {
        if (edge < edges.length) {
            if (edges[edge] != 0) {
                edges[edge] = 0;
                size--;
            }
        }
    }

    public void negate(int edge) {
        if (edge < edges.length) {
            if (edges[edge] > 0) {
                edges[edge] = -1;
            } else if (edges[edge] == 0) {
                edges[edge] = -1;
                size++;
            }
        }
    }

    public int size() {
        return size;
    }

    public boolean equals(Edges otherEdge) {
        if (this.size == otherEdge.size()) {
            for (int i = 0; i < this.edges.length; i++) {
                if (this.edges[i] == 0 && !otherEdge.get(i)) {
                    return false;
                } else if (this.edges[i] != 0 && !otherEdge.get(i)) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    @Override
    public boolean get(int index) {
        return this.edges[index] != 0;
    }

    @Override
    public Edges inverted() {
        return null;
    }

    @Override
    public int length() {
        return 0;
    }

    @Override
    public void set(int i, boolean b) {
        this.edges[i] = 1;
    }

    @Override
    public Edges or(Edges solve) {
        return null;
    }

    @Override
    public String toString() {
        String EdgeString = "";
        for (int i : edges) {
            EdgeString += (i + " ");
        }
        return EdgeString;
    }
}
