package by.tc.listik.impl;

import by.tc.listik.Listik;
import by.tc.listik.ListikIterator;
import org.apache.log4j.Logger;

/**
 * This is a list implementation of Listik class
 */
public class LinkedListik extends AbstractListik {
    private static final long serialVersionUID = -3155702771677544806L;
    private static final Logger logger = Logger.getLogger("log4j");

    private Element head;
    private Element tail;
    private int length;

    private static class Element {
        private Object val;
        private Element next;
        private Element prev;

        public Element(Object val, Element next, Element prev) {
            this.val = val;
            this.next = next;
            this.prev = prev;
        }
    }

    public LinkedListik(Object... values) {
        for(Object val : values) {
            add(val);
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
            return head.val;
        } else if (index == length - 1) {
            return tail.val;
        } else {
            Element currentElement = head;
            for (int i = 0; i < index; i++) {
                currentElement = currentElement.next;
            }
            return currentElement.val;
        }
    }

    @Override
    public Object add(Object val, int index) {
        logger.info("Trying to add " + val + " on position " + index);
        if (index < 0 || index > length) {
            logger.warn("An incorrect index value");
            return NOTHING;
        }

        logger.info("Adding a value");
        if (index == 0) {
            if (length == 0) {
                head = tail = new Element(val, null, null);
                length++;
                return head.val;
            }
            head.prev = new Element(val, head, null);
            head = head.prev;
            length++;
            return head.val;
        } else if (index == length) {
            tail.next = new Element(val, null, tail);
            tail = tail.next;
            length++;
            return tail.val;
        } else {
            Element currentElement = head;
            for (int i = 0; i < index; i++) {
                currentElement = currentElement.next;
            }
            currentElement.prev.next = new Element(val, currentElement, currentElement.prev);
            currentElement.prev = currentElement.prev.next;
            length++;
            return currentElement.prev.val;
        }
    }

    @Override
    public Object del(int index) {
        logger.info("Trying to delete a value on position " + index);
        if (index < 0 || index > length) {
            logger.warn("An incorrect index value");
            return NOTHING;
        }

        logger.info("Deleting a value");
        if (index == 0) {
            Object rem = head.val;
            head = head.next;
            head.prev = null;
            length--;
            return rem;
        } else if (index == length) {
            Object rem = tail.val;
            tail = tail.prev;
            tail.next = null;
            length--;
            return rem;
        } else {
            Element currentElement = head;
            for (int i = 0; i < index; i++) {
                currentElement = currentElement.next;
            }
            currentElement.prev.next = currentElement.next;
            length--;
            return currentElement.val;
        }
    }

    @Override
    public Object set(int index, Object val) {
        logger.info("Trying to assign " + val + " on position " + index);
        if (index < 0 || index >= length) {
            logger.warn("An incorrect index value");
            return NOTHING;
        }

        logger.info("Assigning the new value");
        if (index == 0) {
            Object rem = head.val;
            head.val = val;
            return rem;
        } else if (index == length - 1) {
            Object rem = tail.val;
            tail.val = val;
            return rem;
        } else {
            Element currentElement = head;
            for (int i = 0; i < index; i++) {
                currentElement = currentElement.next;
            }
            Object rem = currentElement.val;
            currentElement.val = val;
            return rem;
        }
    }

    @Override
    public ListikIterator iterator() {
        return new LinkedListikIterator(head);
    }

    @Override
    public int length() {
        return length;
    }

    @Override
    public boolean equals(Listik ls) {
        if (ls == null || length != ls.length()) { return false; }
        ListikIterator iterator1 = iterator();
        ListikIterator iterator2 = ls.iterator();
        while (iterator1.hasNext()) {
            if (iterator1.next() != iterator2.next()) { return false; }
        }
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) { return true; }
        if (obj == null || getClass() != obj.getClass()) { return false; }
        return equals((Listik) obj);
    }


    private class LinkedListikIterator implements ListikIterator {
        private Element head;
        private Element current;

        public LinkedListikIterator(Element head) {
            this.current = this.head = new Element(NOTHING, head, null);
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
                return Listik.NOTHING;
            }
            current = current.next;
            logger.info("Returning the next element");
            return current.val;
        }

        @Override
        public boolean hasPrev() {
            return current.prev != null;
        }

        @Override
        public Object prev() {
            logger.info("Trying to get the previous element");
            if (!hasPrev()) {
                logger.warn("There're no previous elements");
                return Listik.NOTHING;
            }
            current = current.prev;
            logger.info("Returning the previous elements");
            return current.val;
        }
    }
}
