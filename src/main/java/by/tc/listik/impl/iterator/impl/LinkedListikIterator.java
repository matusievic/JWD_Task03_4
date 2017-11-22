package by.tc.listik.impl.iterator.impl;

import by.tc.listik.impl.ListikIterator;
import by.tc.listik.Listik;

public class LinkedListikIterator implements ListikIterator {
    private Element head;
    private Element current;

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

    public LinkedListikIterator(Object head) {
        this.head = (Element) head;
    }

    @Override
    public boolean hasNext() {
        return current.next != null;
    }

    @Override
    public Object next() {
        if (!hasNext()) { return Listik.NOTHING; }
        current = (Element) current.next;
        return current.val;
    }

    @Override
    public boolean hasPrev() {
        return current.prev != null;
    }

    @Override
    public Object prev() {
        if (!hasPrev()) { return Listik.NOTHING; }
        current = (Element) current.prev;
        return current.prev;
    }
}
