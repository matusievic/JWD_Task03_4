package by.tc.tree.impl;

import by.tc.listik.Listik;
import by.tc.listik.impl.ArrayListik;
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
            length++;
            return;
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
                add(node.right, val);
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


        public void bypassPreOrder(Listik result) {
            result.add(val);
            if (left != null) left.bypassPreOrder(result);
            if (right != null) right.bypassPreOrder(result);
        }

        void bypassInOrder(Listik result) {
            if (left != null) left.bypassInOrder(result);
            result.add(val);
            if (right != null) right.bypassInOrder(result);
        }

        void bypassPostOrder(Listik result) {
            if (left != null) left.bypassPostOrder(result);
            if (right != null) right.bypassPostOrder(result);
            ;
            result.add(val);
        }

    }


    public Listik bypassPreOrder() {
        Listik result = new ArrayListik();
        root.bypassPreOrder(result);
        return result;
    }

    public Listik bypassInOrder() {
        Listik result = new ArrayListik();
        root.bypassInOrder(result);
        return result;
    }

    public Listik bypassPostOrder() {
        Listik result = new ArrayListik();
        root.bypassPostOrder(result);
        return result;
    }

}
