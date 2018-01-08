package by.tc.custom.list.impl;

import by.tc.custom.list.CustomList;
import by.tc.custom.list.CustomListIterator;
import org.apache.log4j.Logger;

/**
 * This is an array implementation of CustomList class
 */
public class ArrayCustomList extends AbstractCustomList {
    private static final long serialVersionUID = -33460992926824480L;
    private static final Logger logger = Logger.getLogger(ArrayCustomList.class);

    private int capacity = 10;
    private Object[] data = new Object[capacity];
    private int length;

    public ArrayCustomList() {}

    public ArrayCustomList(Object... values) {
        if (values.length > capacity) {
            data = new Object[values.length + capacity];
        }
        for (Object value : values) {
            add(value);
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
    public Object add(Object value, int index) {
        logger.info("Trying to add element: " + value);
        if (!isEnoughSpace()) {
            addSpace();
        }
        if (index == length) {
            data[index] = value;
        } else if (index < length && index >= 0) {
            System.arraycopy(data, index, data, index + 1, length - index);
            data[index] = value;
        } else {
            logger.warn("Incorrect index");
            return NOTHING;
        }
        length++;
        logger.info("Element added: " + data[index]);
        return data[index];
    }

    @Override
    public Object delete(int index) {
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
    public Object set(int index, Object value) {
        logger.info("Trying to assign " + value + " to element on position " + index);
        if (index < 0 || index >= length) {
            logger.warn("Incorrect index value");
            return NOTHING;
        }
        Object removedElement = data[index];
        data[index] = value;
        logger.info("Reassigned: " + removedElement + " -> " + value);
        return removedElement;
    }

    @Override
    public CustomListIterator iterator() {
        return new ArrayCustomListIterator(this);
    }

    @Override
    public int length() {
        logger.info("Returning length");
        return length;
    }

    @Override
    public boolean equals(CustomList list) {
        if (list == null || length != list.length()) { return false; }
        for (int i = 0; i < length; i++) {
            if (!data[i].equals(list.get(i))) { return false; }
        }
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) { return true; }
        if (obj == null || getClass() != obj.getClass()) { return false; }
        return equals((CustomList) obj);
    }

    private static class ArrayCustomListIterator implements CustomListIterator {
        private int currentElement = 0;
        private ArrayCustomList list;

        public ArrayCustomListIterator(ArrayCustomList ls) {
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
                return CustomList.NOTHING;
            }
            logger.info("Returning the next element");
            return list.data[currentElement++];
        }

        @Override
        public boolean hasPrevious() {
            return currentElement > 0;
        }

        @Override
        public Object previous() {
            if (!hasPrevious()) {
                logger.warn("There're no previous elements");
                return CustomList.NOTHING;
            }
            return list.data[--currentElement];
        }
    }
}
