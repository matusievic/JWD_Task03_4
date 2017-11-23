package by.tc.listik;

/**
 * This is an interface for lists hierarchy
 */
public interface Listik {
    /**
     * Get the last element of the list
     * @return the last list element or NOTHING if the list if empty
     */
    Object get();

    /**
     * Get some list element
     * @param index an index of element
     * @return an list element or NOTHING if the index is incorrect
     */
    Object get(int index);

    /**
     * Add an element to the end of the list
     * @param val an element
     * @return an added element
     */
    Object add(Object val);

    /**
     * Add an element at the specific position of the list
     * @param val an element
     * @param index a position for adding
     * @return an added element of NOTHING if the index is incorrect
     */
    Object add(Object val, int index);

    /**
     * Remove the last element of the list
     * @return a removed element of NOTHING if the list is empty
     */
    Object del();

    /**
     * Remove an element from the specific position of the list
     * @param index a position for removing
     * @return a removed element of NOTHING if the index is incorrect
     */
    Object del(int index);

    /**
     * Change the value of the specific list element
     * @param index a position for changing
     * @param val a new value
     * @return the old value or NOTHING if the index is incorrect
     */
    Object set(int index, Object val);

    /**
     * Get an interator
     * @return an iterator
     */
    ListikIterator iterator();

    /**
     * Get the length of a list
     * @return the length
     */
    int length();

    /**
     * Check list emptiness
     * @return true if the list is empty, false - otherwise
     */
    boolean isEmpty();

    /**
     * Check if two lists are equals
     * @param ls the second list
     * @return true if list are equals, false - otherwise
     */
    boolean equals(Listik ls);

    Nothing NOTHING = Nothing.NOTHING;
    final class Nothing {
        public static final Nothing NOTHING = new Nothing();
        private Nothing() {}

        @Override
        public String toString() {
            return "NOTHING";
        }
    }
}
