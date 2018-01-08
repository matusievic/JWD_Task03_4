package by.tc.tree;

import by.tc.custom.list.CustomList;

/**
 * An interface for Tree hierarchy
 */
public interface Tree {
    /**
     * Add an element to the tree
     * @param val a new element
     */
    void add (Comparable val);

    /**
     * Delete an element from the tree
     * @param val an element for deleting
     * @return a deleted element
     */
    Object delete(Comparable val);

    /**
     * Get element count
     * @return an element count
     */
    int length();

    //bypass algorithms
    CustomList bypassPreOrder();
    CustomList bypassInOrder();
    CustomList bypassPostOrder();
}