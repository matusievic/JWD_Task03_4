package by.tc.tree.impl;

import by.tc.custom.list.CustomList;
import by.tc.custom.list.CustomListIterator;
import by.tc.custom.list.impl.ArrayCustomList;
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
        for (Comparable value : values) {
            add(value);
        }
    }

    @Override
    public void add(Comparable value) {
        logger.info("Trying to add a new node");
        if (value == null) {
            logger.warn("Cannot to add a null node");
            return;
        }
        if (root == null) {
            root = new Node(value, null);
            length++;
            logger.info("A root node was added");
            return;
        }
        add(root, value);
        length++;
        logger.info("A node was added");
    }

    @Override
    public Object delete(Comparable value) {
        logger.info("Trying to delete an element");
        CustomListIterator iterator = this.getNodes().iterator();
        while (iterator.hasNext()) {
            Node current = (Node) iterator.next();
            if (current.value.equals(value)) {
                logger.info("An appropriate node was found");
                if (current.parent == null) {
                    if (current.left == null && current.right == null) {
                        root = null;
                    } else if (current.left != null && current.right != null) {
                        this.root = current.left;
                        Node placeForRight = findPlace(current.right.value);
                        insertNode(placeForRight, current.right);
                    } else if (current.left != null) {
                        this.root = current.left;
                    } else {
                        this.root = current.right;
                    }
                    logger.info("A root node was deleted");
                } else {
                    Node parent = current.parent;
                    if (parent.left == current) {
                        parent.left = null;
                    }
                    if (parent.right == current) {
                        parent.right = null;
                    }

                    if (current.left != null) {
                        Node placeForLeft = findPlace(current.left.value);
                        insertNode(placeForLeft, current.left);
                    }
                    if (current.right != null) {
                        Node placeForRight = findPlace(current.right.value);
                        insertNode(placeForRight, current.right);
                    }
                    logger.info("A non-root node was deleted");
                }
                return current;
            }
            logger.warn("There's no such node at the tree");
        }
        return null;
    }

    private CustomList getNodes() {
        CustomList result = new ArrayCustomList();
        root.bypassNodes(result);
        return result;
    }

    private Node findPlace(Comparable value) {
        if (value == null) {
            logger.info("Cannot to find a place for null node");
            return null;
        }
        Node current = root, parent = null;
        while (current != null) {
            parent = current;
            if (current.value.compareTo(value) > 0) {
                current = current.left;
            } else if (current.value.compareTo(value) < 0) {
                current = current.right;
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

    private void add(Node node, Comparable value) {
        if (node.value.compareTo(value) > 0) {
            if (node.left == null) {
                node.left = new Node(value, node);
                logger.info("The node was added");
            } else {
                add(node.left, value);
            }
        } else if (node.value.compareTo(value) < 0) {
            if (node.right == null) {
                node.right = new Node(value, node);
                logger.info("The node was added");
            } else {
                add(node.right, value);
            }
        }
    }

    private void insertNode(Node parent, Node child) {
        if (parent.value.compareTo(child.value) > 0) {
            parent.left = child;
        } else if (parent.value.compareTo(child.value) < 0) {
            parent.right = child;
        }
    }

    private class Node {
        private Comparable value;
        private Node parent;
        private Node left;
        private Node right;

        private Node(Comparable value, Node parent) {
            this.value = value;
            this.parent = parent;
        }


        private void bypassPreOrder(CustomList result) {
            result.add(value);
            if (left != null) {
                left.bypassPreOrder(result);
            }
            if (right != null) {
                right.bypassPreOrder(result);
            }
        }

        private void bypassInOrder(CustomList result) {
            if (left != null) {
                left.bypassInOrder(result);
            }
            result.add(value);
            if (right != null) {
                right.bypassInOrder(result);
            }
        }

        private void bypassPostOrder(CustomList result) {
            if (left != null) {
                left.bypassPostOrder(result);
            }
            if (right != null) {
                right.bypassPostOrder(result);
            }
            result.add(value);
        }

        private void bypassNodes(CustomList result) {
            result.add(this);
            if (left != null) {
                left.bypassNodes(result);
            }
            if (right != null) {
                right.bypassNodes(result);
            }
        }
    }


    public CustomList bypassPreOrder() {
        CustomList result = new ArrayCustomList();
        if (root != null) {
            root.bypassPreOrder(result);
        }
        return result;
    }

    public CustomList bypassInOrder() {
        CustomList result = new ArrayCustomList();
        if (root != null) {
            root.bypassInOrder(result);
        }
        return result;
    }

    public CustomList bypassPostOrder() {
        CustomList result = new ArrayCustomList();
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

        CustomList items = this.bypassInOrder();
        CustomList otherItems = ((Tree) o).bypassInOrder();

        return items.equals(otherItems);
    }
}
