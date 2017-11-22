package by.tc.listik.impl;

import by.tc.listik.Listik;

public class ArrayListik extends AbstractListik {
    private static final long serialVersionUID = -33460992926824480L;
    private Object[] data;
    private int length;
    private int capacity = 10;

    public ArrayListik() {
        data = new Object[capacity];
    }

    private boolean isEnoughSpace() {
        if (length < data.length) {
            return true;
        }
        return false;
    }

    private void addSpace() {
        Object[] old = data;
        data = new Object[length + capacity];
        System.arraycopy(old, 0, data, 0, length);
    }

    @Override
    public Object get(int index) {
        if (index < 0 || index >= length) { return NOTHING; }
        return data[index];
    }

    @Override
    public Object add(Object val, int index) {
        if (!isEnoughSpace()) {
            addSpace();
        }
        if (index == length) {
            data[index] = val;
        } else if (index < length && index >= 0){
            System.arraycopy(data, index, data, index + 1, length - index);
            data[index] = val;
        } else { return NOTHING; }
        length++;
        return data[index];
    }

    @Override
    public Object del(int index) {
        if (index < 0 || index >= length) { return NOTHING; }
        Object rem = data[index];
        int elementToCopy = length - index - 1;
        System.arraycopy(data, index + 1, data, index, elementToCopy);
        length--;
        return rem;
    }

    @Override
    public Object set(int index, Object val) {
        if (index < 0 || index >= length) { return NOTHING; }
        Object rem = data[index];
        data[index] = val;
        return rem;
    }

    @Override
    public ListikIterator iterator() {
        return new ArrayListikIterator(data);
    }

    @Override
    public int length() {
        return length;
    }

    @Override
    public boolean equals(Listik ls) {
        if (ls == null || length != ls.length()) { return false; }
        for (int i = 0; i < length; i++) {
            if (!data[i].equals(ls.get(i))) { return false; }
        }
        return true;
    }


    public static class ArrayListikIterator implements ListikIterator {
        private int currentElement = 0;
        private Object[] data;

        public ArrayListikIterator(Object[] data) {
            this.data = data;
        }

        @Override
        public boolean hasNext() {
            return data.length - 1 > currentElement;
        }

        @Override
        public Object next() {
            if (!hasNext()) { return Listik.NOTHING; }
            return data[++currentElement];
        }

        @Override
        public boolean hasPrev() {
            return currentElement > 0;
        }

        @Override
        public Object prev() {
            if (!hasPrev()) { return Listik.NOTHING; }
            return data[--currentElement];
        }
    }
}
