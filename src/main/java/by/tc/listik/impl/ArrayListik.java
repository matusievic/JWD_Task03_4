package by.tc.listik.impl;

import by.tc.listik.Listik;
import by.tc.listik.ListikIterator;
import org.apache.log4j.Logger;

/**
 * This is an array implementation of Listik class
 */
public class ArrayListik extends AbstractListik {
    private static final long serialVersionUID = -33460992926824480L;
    private static final Logger logger = Logger.getLogger(ArrayListik.class);

    private int capacity = 10;
    private Object[] data = new Object[capacity];
    private int length;

    public ArrayListik() {

    }

    public ArrayListik(Object... values) {
        if (values.length > capacity) {
            data = new Object[values.length + capacity];
        }
        for (Object val : values) {
            add(val);
        }
    }


    private boolean isEnoughSpace() {
        if (length < data.length) {
            logger.info("Enough space");
            return true;
        }
        logger.info("Not enough space");
        return false;
    }

    private void addSpace() {
        logger.info("Adding space");
        Object[] old = data;
        data = new Object[length + capacity];
        System.arraycopy(old, 0, data, 0, length);
    }

    @Override
    public Object get(int index) {
        logger.info("Trying to get element");
        if (index < 0 || index >= length) {
            logger.warn("Incorrect index");
            return NOTHING;
        }
        logger.info("Returning element: " + data[index]);
        return data[index];
    }

    @Override
    public Object add(Object val, int index) {
        logger.info("Trying to add element: " + val);
        if (!isEnoughSpace()) {
            addSpace();
        }
        if (index == length) {
            data[index] = val;
        } else if (index < length && index >= 0) {
            System.arraycopy(data, index, data, index + 1, length - index);
            data[index] = val;
        } else {
            logger.warn("Incorrect index");
            return NOTHING;
        }
        length++;
        logger.info("Element added: " + data[index]);
        return data[index];
    }

    @Override
    public Object del(int index) {
        logger.info("Trying to delete element on position: " + index);
        if (index < 0 || index >= length) {
            logger.warn("Incorrect index value");
            return NOTHING;
        }
        Object rem = data[index];
        int elementToCopy = length - index - 1;
        System.arraycopy(data, index + 1, data, index, elementToCopy);
        length--;
        logger.info("Element was removed: " + rem);
        return rem;
    }

    @Override
    public Object set(int index, Object val) {
        logger.info("Trying to assign " + val + " to element on position " + index);
        if (index < 0 || index >= length) {
            logger.warn("Incorrect index value");
            return NOTHING;
        }
        Object rem = data[index];
        data[index] = val;
        logger.info("Reassigned: " + rem + " -> " + val);
        return rem;
    }

    @Override
    public ListikIterator iterator() {
        return new ArrayListikIterator(this);
    }

    @Override
    public int length() {
        logger.info("Returning length");
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) { return true; }
        if (obj == null || getClass() != obj.getClass()) { return false; }
        return equals((Listik) obj);
    }

    private static class ArrayListikIterator implements ListikIterator {
        private int currentElement = 0;
        private ArrayListik list;

        public ArrayListikIterator(ArrayListik ls) {
            this.list = ls;
        }

        @Override
        public boolean hasNext() {
            return list.length > currentElement;
        }

        @Override
        public Object next() {
            logger.info("Trying to get the next element");
            if (!hasNext()) {
                logger.warn("There're no more elements");
                return Listik.NOTHING;
            }
            logger.info("Returning the next element");
            return list.data[currentElement++];
        }

        @Override
        public boolean hasPrev() {
            return currentElement > 0;
        }

        @Override
        public Object prev() {
            if (!hasPrev()) {
                logger.warn("There're no previous elements");
                return Listik.NOTHING;
            }
            return list.data[--currentElement];
        }
    }
}
