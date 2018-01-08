package by.tc.custom.list.impl;

import by.tc.custom.list.CustomList;
import by.tc.custom.list.CustomListIterator;
import org.apache.log4j.Logger;

/**
 * This is a list implementation of CustomList class
 */
public class LinkedCustomList extends AbstractCustomList {
    private static final long serialVersionUID = -3155702771677544806L;
    private static final Logger logger = Logger.getLogger("log4j");

    private Element head;
    private Element tail;
    private int length;

    private static class Element {
        private Object value;
        private Element next;
        private Element previous;

        public Element(Object value, Element next, Element previous) {
            this.value = value;
            this.next = next;
            this.previous = previous;
        }
    }

    public LinkedCustomList(Object... values) {
        for(Object value : values) {
            add(value);
        }
    }

    @Override
    public Object get(int index) {
        logger.info("Trying to get an element on position: " + index);
        if (index < 0 || index >= length) {
            logger.warn("Incorrect index value");
            return NOTHING;
        }

        logger.info("Returning value");
        if (index == 0) {
            return head.value;
        } else if (index == length - 1) {
            return tail.value;
        } else {
            Element currentElement = head;
            for (int i = 0; i < index; i++) {
                currentElement = currentElement.next;
            }
            return currentElement.value;
        }
    }

    @Override
    public Object add(Object value, int index) {
        logger.info("Trying to add " + value + " on position " + index);
        if (index < 0 || index > length) {
            logger.warn("An incorrect index value");
            return NOTHING;
        }

        logger.info("Adding a value");
        if (index == 0) {
            if (length == 0) {
                head = tail = new Element(value, null, null);
                length++;
                return head.value;
            }
            head.previous = new Element(value, head, null);
            head = head.previous;
            length++;
            return head.value;
        } else if (index == length) {
            tail.next = new Element(value, null, tail);
            tail = tail.next;
            length++;
            return tail.value;
        } else {
            Element currentElement = head;
            for (int i = 0; i < index; i++) {
                currentElement = currentElement.next;
            }
            currentElement.previous.next = new Element(value, currentElement, currentElement.previous);
            currentElement.previous = currentElement.previous.next;
            length++;
            return currentElement.previous.value;
        }
    }

    @Override
    public Object delete(int index) {
        logger.info("Trying to delete a value on position " + index);
        if (index < 0 || index > length) {
            logger.warn("An incorrect index value");
            return NOTHING;
        }

        logger.info("Deleting a value");
        if (index == 0) {
            Object removedObject = head.value;
            head = head.next;
            head.previous = null;
            length--;
            return removedObject;
        } else if (index == length) {
            Object removedObject = tail.value;
            tail = tail.previous;
            tail.next = null;
            length--;
            return removedObject;
        } else {
            Element currentElement = head;
            for (int i = 0; i < index; i++) {
                currentElement = currentElement.next;
            }
            currentElement.previous.next = currentElement.next;
            length--;
            return currentElement.value;
        }
    }

    @Override
    public Object set(int index, Object value) {
        logger.info("Trying to assign " + value + " on position " + index);
        if (index < 0 || index >= length) {
            logger.warn("An incorrect index value");
            return NOTHING;
        }

        logger.info("Assigning the new value");
        if (index == 0) {
            Object removedObject = head.value;
            head.value = value;
            return removedObject;
        } else if (index == length - 1) {
            Object removedObject = tail.value;
            tail.value = value;
            return removedObject;
        } else {
            Element currentElement = head;
            for (int i = 0; i < index; i++) {
                currentElement = currentElement.next;
            }
            Object removedObject = currentElement.value;
            currentElement.value = value;
            return removedObject;
        }
    }

    @Override
    public CustomListIterator iterator() {
        return new LinkedCustomListIterator(head);
    }

    @Override
    public int length() {
        return length;
    }

    @Override
    public boolean equals(CustomList list) {
        if (list == null || length != list.length()) { return false; }
        CustomListIterator iterator1 = iterator();
        CustomListIterator iterator2 = list.iterator();
        while (iterator1.hasNext()) {
            if (iterator1.next() != iterator2.next()) { return false; }
        }
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) { return true; }
        if (obj == null || getClass() != obj.getClass()) { return false; }
        return equals((CustomList) obj);
    }


    private class LinkedCustomListIterator implements CustomListIterator {
        private Element current;

        public LinkedCustomListIterator(Element head) {
            this.current = new Element(NOTHING, head, null);
        }

        @Override
        public boolean hasNext() {
            return current.next != null;
        }

        @Override
        public Object next() {
            logger.info("Trying to get the next element");
            if (!hasNext()) {
                logger.warn("There're no more elements");
                return CustomList.NOTHING;
            }
            current = current.next;
            logger.info("Returning the next element");
            return current.value;
        }

        @Override
        public boolean hasPrevious() {
            return current.previous != null;
        }

        @Override
        public Object previous() {
            logger.info("Trying to get the previous element");
            if (!hasPrevious()) {
                logger.warn("There're no previous elements");
                return CustomList.NOTHING;
            }
            current = current.previous;
            logger.info("Returning the previous elements");
            return current.value;
        }
    }
}
