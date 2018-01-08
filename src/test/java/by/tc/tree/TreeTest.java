package by.tc.tree;

import by.tc.custom.list.CustomList;
import by.tc.custom.list.impl.ArrayCustomList;
import by.tc.tree.impl.BinaryTree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TreeTest {
    Tree tree;

    @BeforeEach
    void setUp() {
        tree = new BinaryTree(7, 3, 1, 0, 2, 5, 4, 6, 14, 12, 8, 13, 20, 19, 27);
    }

    @Test
    void bypassPreOrderTest() {
        CustomList expected = new ArrayCustomList(7, 3, 1, 0, 2, 5, 4, 6, 14, 12, 8, 13, 20, 19, 27);
        CustomList actual = tree.bypassPreOrder();
        assertEquals(expected, actual);
    }

    @Test
    void bypassInOrderTest() {
        CustomList expected = new ArrayCustomList(0, 1, 2, 3, 4, 5, 6, 7, 8, 12, 13, 14, 19, 20, 27);
        CustomList actual = tree.bypassInOrder();
        assertEquals(expected, actual);
    }

    @Test
    void bypassPostOrderTest() {
        CustomList expected = new ArrayCustomList(0, 2, 1, 4, 6, 5, 3, 8, 13, 12, 19, 27, 20, 14, 7);
        CustomList actual = tree.bypassPostOrder();
        assertEquals(expected, actual);
    }

    @Test
    void deleteNonRootNodeTest() {
        Tree expected = new BinaryTree(7, 3, 1, 0, 2, 5, 4, 6, 14, 12, 8, 13, 19, 27);
        tree.delete(20);
        Tree actual = tree;
        assertEquals(expected, actual);
    }

    @Test
    void deleteRootNodeWithChildrenTest() {
        Tree expected = new BinaryTree(3, 1, 0, 2, 5, 4, 6, 14, 12, 8, 13, 20, 19, 27);
        tree.delete(7);
        Tree actual = tree;
        assertEquals(expected, actual);
    }

    @Test
    void deleteRootNodeWithoutChildrenTest() {
        Tree expected = new BinaryTree();
        Tree actual = new BinaryTree(5);
        actual.delete(5);
        assertEquals(expected, actual);
    }

    @Test
    void deleteRootWithoutChildTest() {
        Tree expectedWithoutLeftChild = new BinaryTree(7);
        Tree actualWithoutLeftChild = new BinaryTree(5, 7);
        actualWithoutLeftChild.delete(5);

        Tree expectedWithoutRightChild = new BinaryTree(4);
        Tree actualWithoutRightChild = new BinaryTree(5, 4);
        actualWithoutRightChild.delete(5);

        assertAll(() -> assertEquals(expectedWithoutLeftChild, actualWithoutLeftChild),
                  () -> assertEquals(expectedWithoutRightChild, actualWithoutRightChild));
    }

    @Test
    void deleteLeafTest() {
        Tree expected = new BinaryTree(7, 3, 1, 0, 2, 5, 4, 6, 14, 12, 8, 13, 20, 19);
        tree.delete(27);
        Tree actual = tree;
        assertEquals(expected, actual);
    }
}