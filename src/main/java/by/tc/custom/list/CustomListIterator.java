package by.tc.custom.list;

/**
 * This is an interface for iterators hierarchy
 */
public interface CustomListIterator {
    boolean hasNext();
    Object next();

    boolean hasPrevious();
    Object previous();
}
