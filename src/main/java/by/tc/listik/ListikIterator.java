package by.tc.listik;

/**
 * This is an interface for iterators hierarchy
 */
public interface ListikIterator {
    boolean hasNext();
    Object next();

    boolean hasPrev();
    Object prev();
}
