package by.tc.list;

public abstract class AbstractListik implements Listik {
    @Override
    public Object get() {
        return get(length() - 1);
    }

    @Override
    public void add(Object obj) {
        add(obj, length() - 1);
    }

    @Override
    public void del() {
        del(length() - 1);
    }

    @Override
    public boolean isEmpty() {
        return length() == 0;
    }

}
