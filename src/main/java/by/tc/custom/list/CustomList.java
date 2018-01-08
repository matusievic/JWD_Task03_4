package by.tc.custom.list;

/**
 * This is an interface for lists hierarchy
 */
public interface CustomList {
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
     * @param value an element
     * @return an added element
     */
    Object add(Object value);

    /**
     * Add an element at the specific position of the list
     * @param value an element
     * @param index a position for adding
     * @return an added element of NOTHING if the index is incorrect
     */
    Object add(Object value, int index);

    /**
     * Remove the last element of the list
     * @return a removed element of NOTHING if the list is empty
     */
    Object delete();

    /**
     * Remove an element from the specific position of the list
     * @param index a position for removing
     * @return a removed element of NOTHING if the index is incorrect
     */
    Object delete(int index);

    /**
     * Change the value of the specific list element
     * @param index a position for changing
     * @param value a new value
     * @return the old value or NOTHING if the index is incorrect
     */
    Object set(int index, Object value);

    /**
     * Get an interator
     * @return an iterator
     */
    CustomListIterator iterator();

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
     * @param list the second list
     * @return true if list are equals, false - otherwise
     */
    boolean equals(CustomList list);

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
