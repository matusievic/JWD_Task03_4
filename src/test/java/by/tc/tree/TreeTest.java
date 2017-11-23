package by.tc.tree;

import by.tc.listik.Listik;
import by.tc.listik.ListikIterator;
import by.tc.listik.impl.ArrayListik;
import by.tc.tree.impl.BinaryTree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TreeTest {
    Tree tree;

    @BeforeEach
    void setUp() {
        tree = new BinaryTree(5, 1, 2, 4, 7, 3, 0, 8, 6, 9);
    }

    @Test
    void bypassPreOrder() {
        Listik expected = new ArrayListik(5, 1, 0, 2, 4, 3, 7, 6, 8, 9);
        Listik actual = tree.bypassPreOrder();
        assertEquals(expected, actual);
    }

    @Test
    void bypassInOrder() {
        Listik expected = new ArrayListik(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        Listik actual = tree.bypassInOrder();
        assertEquals(expected, actual);
    }

    @Test
    void bypassPostOrder() {
        Listik expected = new ArrayListik(0, 3, 4, 2, 1, 6, 9, 8, 7, 5);
        Listik actual = tree.bypassPostOrder();
        assertEquals(expected, actual);
    }

}