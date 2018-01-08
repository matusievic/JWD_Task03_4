package by.tc.custom.list.impl;

import by.tc.custom.list.CustomList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayCustomListTest {
    CustomList list1;
    CustomList list2;

    @BeforeEach
    void setUp() {
        list1 = new ArrayCustomList();
        list2 = new ArrayCustomList();
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
        int actual = (int) list1.delete();

        assertEquals(expected, actual);
    }

    @Test
    void setWithoutParams() {
        int expected = 4;
        int actual = (int) list1.set(4, 4);

        assertEquals(expected, actual);
    }

    @Test
    void length() {
        int expected = 15;
        int actual = list1.length();

        assertEquals(expected, actual);
    }

    @Test
    void equals() {
        assertEquals(list1, list2);
    }

    @Test
    void getWithParams() {
        int expected = 8;
        int actual = (int) list2.get(8);

        Object expectedWithBigIndex = CustomList.NOTHING;
        Object actualWithBigIndex = list2.get(100);

        Object expectedWithSmallIndex = CustomList.NOTHING;
        Object actualWithSmallIndex = list2.get(-10);

        assertAll(() -> assertEquals(expected, actual),
                () -> assertEquals(expectedWithBigIndex, actualWithBigIndex),
                () -> assertEquals(expectedWithSmallIndex, actualWithSmallIndex));
    }

    @Test
    void addWithParams() {
        int expected = 10;
        int actual = (int) list2.add(10, 5);

        Object expectedWithBigIndex = CustomList.NOTHING;
        Object actualWithBigIndex = list2.add(12, 100);

        Object expectedWithSmallIndex = CustomList.NOTHING;
        Object actualWithSmallIndex = list2.add(22, -10);

        assertAll(() -> assertEquals(expected, actual),
                () -> assertEquals(expectedWithBigIndex, actualWithBigIndex),
                () -> assertEquals(expectedWithSmallIndex, actualWithSmallIndex));

    }

    @Test
    void delWithParams() {
        int expected = 5;
        int actual = (int) list1.delete(5);

        Object expectedWithBigIndex = CustomList.NOTHING;
        Object actualWithBigIndex = list1.delete(100);

        Object expectedWithSmallIndex = CustomList.NOTHING;
        Object actualWithSmallIndex = list1.delete(-10);

        assertAll(() -> assertEquals(expected, actual),
                () -> assertEquals(expectedWithBigIndex, actualWithBigIndex),
                () -> assertEquals(expectedWithSmallIndex, actualWithSmallIndex));
    }

    @Test
    void isEmpty() {
        assertFalse(list2.isEmpty());
    }

}