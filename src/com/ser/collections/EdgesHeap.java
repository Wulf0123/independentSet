package com.ser.collections;

import com.ser.graph.Edges;

/**
 * Created by Bradley
 * on 11/8/2015.
 */
public class EdgesHeap {
    private Node root;

    public EdgesHeap(){

    }

    public void add(Edges edges){
        Node toAdd = new Node(edges);
        if(root == null){
            root = toAdd;
        } else{
            root.add(toAdd);
        }
    }

    public Edges pop(){
        Edges poppedValue = root == null ? null : root.getValue();
        if(root != null) {
            root = root.delete();
        }
        return poppedValue;
    }

    public static class Node{
        private Edges value;
        private Node leftChild;
        private Node rightChild;
        private int leftTreeSize;
        private int rightTreeSize;

        public Node(Edges value){
            this.value = value;
        }

        public void add(Node toAdd){
            if(toAdd.getValue().size() > this.getValue().size()){
                Node.swapValue(this, toAdd);
            }
            if(leftChild == null){
                leftChild = toAdd;
            } else if (rightChild == null){
                rightChild = toAdd;
            } else{
                if(rightTreeSize < leftTreeSize){
                    rightChild.add(toAdd);
                } else{
                    leftChild.add(toAdd);
                }
            }
        }

        public Node delete(){
            if(leftChild == null && rightChild == null){
                return null;
            }
            if(rightChild != null){
                rightChild.add(leftChild);
                return rightChild;
            } else{
                return leftChild;
            }
        }

        private Edges getValue(){
            return value;
        }

        private void setValue(Edges value){
            this.value = value;
        }

        public static void swapValue(Node one, Node two){
            Edges tmp = one.getValue();
            one.setValue(two.getValue());
            two.setValue(tmp);
        }
    }
}
