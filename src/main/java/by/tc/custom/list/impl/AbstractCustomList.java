package by.tc.custom.list.impl;

import by.tc.custom.list.CustomList;

import java.io.Serializable;

/**
 * This class contains common methods implementations
 */
public abstract class AbstractCustomList implements CustomList, Serializable {
    private static final long serialVersionUID = 6879748199965422194L;

    @Override
    public Object get() {
        return get(length() - 1);
    }

    @Override
    public Object add(Object value) {
        return add(value, length());
    }

    @Override
    public Object delete() {
        return delete(length() - 1);
    }

    @Override
    public boolean isEmpty() {
        return length() == 0;
    }

}
