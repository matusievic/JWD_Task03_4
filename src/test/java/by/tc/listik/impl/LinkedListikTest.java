package by.tc.listik.impl;

import by.tc.listik.Listik;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListikTest {
    Listik list1;
    Listik list2;

    /*@BeforeEach
    void setUp() {
        list1 = new LinkedListik();
        list2 = new LinkedListik();

        for (int i = 0; i < 15; i++) {
            list1.add(i);
            list2.add(i);
        }
    }

    @Test
    void getWithoutParams() {
        int expected = 14;
        int actual = (int) list1.get();

        assertEquals(expected, actual);
    }

    @Test
    void addWithoutParams() {
        int expected = 3;
        int actual = (int) list1.add(3);

        assertEquals(expected, actual);
    }

    @Test
    void delWithoutParams() {
        int expected = 14;
        int actual = (int) list1.del();

        assertEquals(expected, actual);
    }

    @Test
    void setWithoutParams() {
        int expected = 4;
        int actual = (int) list1.set(4, 4);

        assertEquals(expected, actual);
    }

    @Test
    void getWithParams() {
        int expected = 8;
        int actual = (int) list2.get(8);

        Object expectedWithBigIndex = Listik.NOTHING;
        Object actualWithBigIndex = list2.get(100);

        Object expectedWithSmallIndex = Listik.NOTHING;
        Object actualWithSmallIndex = list2.get(-10);

        assertAll(() -> assertEquals(expected, actual),
                () -> assertEquals(expectedWithBigIndex, actualWithBigIndex),
                () -> assertEquals(expectedWithSmallIndex, actualWithSmallIndex));
    }

    @Test
    void addWithParams() {
        int expected = 10;
        int actual = (int) list2.add(10, 5);

        Object expectedWithBigIndex = Listik.NOTHING;
        Object actualWithBigIndex = list2.add(12, 100);

        Object expectedWithSmallIndex = Listik.NOTHING;
        Object actualWithSmallIndex = list2.add(22, -10);

        assertAll(() -> assertEquals(expected, actual),
                () -> assertEquals(expectedWithBigIndex, actualWithBigIndex),
                () -> assertEquals(expectedWithSmallIndex, actualWithSmallIndex));

    }

    @Test
    void delWithParams() {
        int expected = 5;
        int actual = (int) list1.del(5);

        Object expectedWithBigIndex = Listik.NOTHING;
        Object actualWithBigIndex = list1.del(100);

        Object expectedWithSmallIndex = Listik.NOTHING;
        Object actualWithSmallIndex = list1.del(-10);

        assertAll(() -> assertEquals(expected, actual),
                () -> assertEquals(expectedWithBigIndex, actualWithBigIndex),
                () -> assertEquals(expectedWithSmallIndex, actualWithSmallIndex));
    }

    @Test
    void isEmpty() {
        assertFalse(list2.isEmpty());
    }


    @Test
    void length() {
        int expected = 15;
        int actual = list1.length();

        assertEquals(expected, actual);
    }

    @Test
    void equals() {
        assertTrue(list1.equals(list2));
    }
*/
}