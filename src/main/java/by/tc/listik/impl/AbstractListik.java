package by.tc.listik.impl;

import by.tc.listik.Listik;

import java.io.Serializable;

/**
 * This class contains common method implementations
 */
public abstract class AbstractListik implements Listik, Serializable {
    private static final long serialVersionUID = 6879748199965422194L;

    @Override
    public Object get() {
        return get(length() - 1);
    }

    @Override
    public Object add(Object obj) {
        return add(obj, length());
    }

    @Override
    public Object del() {
        return del(length() - 1);
    }

    @Override
    public boolean isEmpty() {
        return length() == 0;
    }

}
