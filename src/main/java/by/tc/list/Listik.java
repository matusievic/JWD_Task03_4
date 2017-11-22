package by.tc.list;

import java.util.Iterator;

public interface Listik {
    Object get();
    Object get(int index);

    void add(Object obj);
    void add(Object obj, int index);

    void del();
    void del(int index);

    void set(int index, Object obj) throws IllegalArgumentException;

    Iterator iterator();

    int length();
    boolean isEmpty();
}
