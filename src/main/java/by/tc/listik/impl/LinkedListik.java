package by.tc.listik.impl;

import by.tc.listik.Listik;
import by.tc.listik.ListikIterator;

/**
 * This is a list implementation of Listik class
 */
public class LinkedListik extends AbstractListik {
    private static final long serialVersionUID = -3155702771677544806L;

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
        if (index < 0 || index >= length) { return NOTHING; }

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
        if (index < 0 || index > length) { return NOTHING; }

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
        if (index < 0 || index > length) { return NOTHING; }

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
        if (index < 0 || index >= length) { return NOTHING; }

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
            if (!hasNext()) { return Listik.NOTHING; }
            current = current.next;
            return current.val;
        }

        @Override
        public boolean hasPrev() {
            return current.prev != null;
        }

        @Override
        public Object prev() {
            if (!hasPrev()) { return Listik.NOTHING; }
            current = current.prev;
            return current.val;
        }
    }
}
