package by.tc.listik;

public interface Listik {
    Object get();
    Object get(int index);

    Object add(Object val);
    Object add(Object val, int index);

    Object del();
    Object del(int index);

    Object set(int index, Object val);

    ListikIterator iterator();

    int length();
    boolean isEmpty();
    boolean equals(Listik ls);

    Nothing NOTHING = Nothing.NOTHING;
    final class Nothing {
        public static final Nothing NOTHING = new Nothing();
        private Nothing() {}
    }
}
