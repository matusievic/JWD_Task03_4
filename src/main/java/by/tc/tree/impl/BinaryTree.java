package by.tc.tree.impl;

import by.tc.tree.Tree;

import java.io.Serializable;

public class BinaryTree implements Tree, Serializable {
    private static final long serialVersionUID = 8243204005036393181L;
    private Node root;
    private int length;


    public BinaryTree(Comparable... values) {
        for(Comparable val : values) {
            add(val);
        }
    }

    @Override
    public void add(Comparable val) {
        if (val == null) { return; }
        if (root == null) {
            root = new Node(val, null);
        }
        add(root, val);
        length++;
    }

    private void add(Node node, Comparable val) {
        if (node.val.compareTo(val) > 0) {
            if (node.left == null) {
                node.left = new Node(val, node);
            } else {
                add(node.left, val);
            }
        } else if (node.val.compareTo(val) < 0) {
            if (node.right == null) {
                node.right = new Node(val, node);
            } else {
                add(node.right, null);
            }
        }
    }

    private class Node {
        private Comparable val;
        private Node parent;
        private Node left;
        private Node right;

        public Node(Comparable val, Node parent) {
            this.val = val;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }


        public void bypassPreOrder(Comparable[] array, int index) {
            array[index++] = val;
            if (left != null) left.bypassPreOrder(array, index);
            if (right != null) right.bypassPreOrder(array, index);
        }

        void bypassInOrder(Comparable[] array, int index) {
            if (left != null) left.bypassInOrder(array, index);
            array[index++] = val;
            if (right != null) right.bypassInOrder(array, index);
        }

        void bypassPostOrder(Comparable[] array, int index) {
            if (left != null) left.bypassPostOrder(array, index);
            if (right != null) right.bypassPostOrder(array, index);
            ;
            array[index++] = val;
        }

    }


    public Comparable[] bypassPreOrder() {
        Comparable[] result = new Comparable[length];
        root.bypassPreOrder(result, 0);
        return result;
    }

    public Comparable[] bypassInOrder() {
        Comparable[] result = new Comparable[length];
        root.bypassPreOrder(result, 0);
        return result;
    }

    public Comparable[] bypassPostOrder() {
        Comparable[] result = new Comparable[length];
        root.bypassPreOrder(result, 0);
        return result;
    }

}
