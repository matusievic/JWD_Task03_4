package by.tc.listik.impl;

import by.tc.listik.Listik;
import by.tc.listik.ListikIterator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayListikIteratorTest {
    ListikIterator iterator;
    final int dataLength = 5;

    @BeforeEach
    void setUp() {
        Listik listik = new ArrayListik();
        for (int i = 0; i < dataLength; i++) {
            listik.add(i);
        }
        iterator = listik.iterator();
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

        Object expectedNothing = Listik.NOTHING;
        Object actualNothing = iterator.next();

        assertAll(() -> assertEquals(expected, actual),
                () -> assertEquals(expectedNothing, actualNothing));
    }

    @Test
    void hasPrevFirstElement() {
        assertFalse(iterator.hasPrev());
    }

    @Test
    void hasPrevMiddleElement() {
        for (int i = 0; i < 3; i++) {
            iterator.next();
        }
        assertTrue(iterator.hasPrev());
    }

    @Test
    void hasPrevLastElement() {
        assertFalse(iterator.hasPrev());
    }

    @Test
    void prev() {
        Object expectedNothing = Listik.NOTHING;
        Object actualNothing = iterator.prev();

        iterator.next();
        int expected = 0;
        int actual = (int) iterator.prev();

        assertAll(() -> assertEquals(expected, actual),
                () -> assertEquals(expectedNothing, actualNothing));
    }

}