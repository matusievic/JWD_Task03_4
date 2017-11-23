package by.tc.tree;

import by.tc.listik.Listik;

public interface Tree {
    void add (Comparable val);

    Listik bypassPreOrder();

    Listik bypassInOrder();

    Listik bypassPostOrder();
}
