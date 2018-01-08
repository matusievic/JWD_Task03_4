package by.tc.tree.impl;

import by.tc.listik.Listik;
import by.tc.listik.ListikIterator;
import by.tc.listik.impl.ArrayListik;
import by.tc.tree.Tree;
import org.apache.log4j.Logger;

import java.io.Serializable;

/**
 * A binary three implementation of Tree interface
 */
public class BinaryTree implements Tree, Serializable {
    private static final long serialVersionUID = 8243204005036393181L;
    private static final Logger logger = Logger.getLogger("log4j");

    private Node root;
    private int length;

    public BinaryTree() {
    }

    public BinaryTree(Comparable... values) {
        for (Comparable val : values) {
            add(val);
        }
    }

    @Override
    public void add(Comparable val) {
        logger.info("Trying to add a new node");
        if (val == null) {
            logger.warn("Cannot to add a null node");
            return;
        }
        if (root == null) {
            root = new Node(val, null);
            length++;
            logger.info("A root node was added");
            return;
        }
        add(root, val);
        length++;
        logger.info("A node was added");
    }

    @Override
    public Object del(Comparable val) {
        logger.info("Trying to delete an element");
        ListikIterator iterator = this.getNodes().iterator();
        while (iterator.hasNext()) {
            Node cur = (Node) iterator.next();
            if (cur.val.equals(val)) {
                logger.info("An appropriate node was found");
                if (cur.parent == null) {
                    if (cur.left == null && cur.right == null) {
                        root = null;
                    } else if (cur.left != null && cur.right != null) {
                        this.root = cur.left;
                        Node placeForRight = findPlace(cur.right.val);
                        insertNode(placeForRight, cur.right);
                    } else if (cur.left != null) {
                        this.root = cur.left;
                    } else {
                        this.root = cur.right;
                    }
                    logger.info("A root node was deleted");
                } else {
                    Node parent = cur.parent;
                    if (parent.left == cur) {
                        parent.left = null;
                    }
                    if (parent.right == cur) {
                        parent.right = null;
                    }

                    if (cur.left != null) {
                        Node placeForLeft = findPlace(cur.left.val);
                        insertNode(placeForLeft, cur.left);
                    }
                    if (cur.right != null) {
                        Node placeForRight = findPlace(cur.right.val);
                        insertNode(placeForRight, cur.right);
                    }
                    logger.info("A non-root node was deleted");
                }
                return cur;
            }
            logger.warn("There's no such node at the tree");
        }
        return null;
    }

    private Listik getNodes() {
        Listik result = new ArrayListik();
        root.bypassNodes(result);
        return result;
    }

    private Node findPlace(Comparable val) {
        if (val == null) {
            logger.info("Cannot to find a place for null node");
            return null;
        }
        Node cur = root, parent = null;
        while (cur != null) {
            parent = cur;
            if (cur.val.compareTo(val) > 0) {
                cur = cur.left;
            } else if (cur.val.compareTo(val) < 0) {
                cur = cur.right;
            } else {
                return null;
            }
        }
        logger.info("Returning a place for the node");
        return parent;
    }

    @Override
    public int length() {
        return length;
    }

    private void add(Node node, Comparable val) {
        if (node.val.compareTo(val) > 0) {
            if (node.left == null) {
                node.left = new Node(val, node);
                logger.info("The node was added");
            } else {
                add(node.left, val);
            }
        } else if (node.val.compareTo(val) < 0) {
            if (node.right == null) {
                node.right = new Node(val, node);
                logger.info("The node was added");
            } else {
                add(node.right, val);
            }
        }
    }

    private void insertNode(Node parent, Node child) {
        if (parent.val.compareTo(child.val) > 0) {
            parent.left = child;
        } else if (parent.val.compareTo(child.val) < 0) {
            parent.right = child;
        }
    }

    private class Node {
        private Comparable val;
        private Node parent;
        private Node left;
        private Node right;

        private Node(Comparable val, Node parent) {
            this.val = val;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }


        private void bypassPreOrder(Listik result) {
            result.add(val);
            if (left != null) left.bypassPreOrder(result);
            if (right != null) right.bypassPreOrder(result);
        }

        private void bypassInOrder(Listik result) {
            if (left != null) left.bypassInOrder(result);
            result.add(val);
            if (right != null) right.bypassInOrder(result);
        }

        private void bypassPostOrder(Listik result) {
            if (left != null) left.bypassPostOrder(result);
            if (right != null) right.bypassPostOrder(result);
            result.add(val);
        }

        private void bypassNodes(Listik result) {
            result.add(this);
            if (left != null) left.bypassNodes(result);
            if (right != null) right.bypassNodes(result);
        }
    }


    public Listik bypassPreOrder() {
        Listik result = new ArrayListik();
        if (root != null) {
            root.bypassPreOrder(result);
        }
        return result;
    }

    public Listik bypassInOrder() {
        Listik result = new ArrayListik();
        if (root != null) {
            root.bypassInOrder(result);
        }
        return result;
    }

    public Listik bypassPostOrder() {
        Listik result = new ArrayListik();
        if (root != null) {
            root.bypassPostOrder(result);
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        Listik items = this.bypassInOrder();
        Listik otherItems = ((Tree) o).bypassInOrder();

        return items.equals(otherItems);
    }
}
