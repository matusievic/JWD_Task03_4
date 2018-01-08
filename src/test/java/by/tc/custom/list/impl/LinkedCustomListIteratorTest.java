package by.tc.custom.list.impl;

import by.tc.custom.list.CustomList;
import by.tc.custom.list.CustomListIterator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedCustomListIteratorTest {
    CustomListIterator iterator;
    final int dataLength = 5;

    @BeforeEach
    void setUp() {
        CustomList customList = new LinkedCustomList();
        for (int i = 0; i < dataLength; i++) {
            customList.add(i);
        }
        iterator = customList.iterator();
    }

    @Test
    void hasNextFirstElement() {
        assertTrue(iterator.hasNext());
    }

    @Test
    void hasNextMiddleElement() {
        for (int i = 0; i < 3; i++) {
            iterator.next();
        }
        assertTrue(iterator.hasNext());
    }

    @Test
    void hasNextLastElement() {
        for (int i = 0; i < dataLength; i++) {
            iterator.next();
        }
        assertFalse(iterator.hasNext());
    }

    @Test
    void next() {
        int expected = 0;
        int actual = (int) iterator.next();

        for (int i = 1; i < dataLength; i++) {
            iterator.next();
        }

        Object expectedNothing = CustomList.NOTHING;
        Object actualNothing = iterator.next();

        assertAll(() -> assertEquals(expected, actual),
                () -> assertEquals(expectedNothing, actualNothing));
    }

    @Test
    void hasPrevFirstElement() {
        assertFalse(iterator.hasPrevious());
    }

    @Test
    void hasPrevMiddleElement() {
        for (int i = 0; i < 3; i++) {
            iterator.next();
        }
        assertTrue(iterator.hasPrevious());
    }

    @Test
    void hasPrevLastElement() {
        assertFalse(iterator.hasPrevious());
    }

    @Test
    void prev() {
        Object expectedNothing = CustomList.NOTHING;
        Object actualNothing = iterator.previous();

        for (int i = 0; i < 2; i++) {
            iterator.next();
        }

        int expected = 0;
        int actual = (int) iterator.previous();

        assertAll(() -> assertEquals(expected, actual),
                () -> assertEquals(expectedNothing, actualNothing));
    }

}