package part2Q2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * A hamper implemented using an ArrayList.
 *
 * @param <E>
 * @author Matthew Perry
 * @invariant for all c in counts, c.getCount() > 0
 */
public class ArrayListItemHamper<E extends Item> implements Hamper<E> {

    private ArrayList<Count<E>> counts;

    /**
     * Create a new empty hamper.
     */
    public ArrayListItemHamper() {
        this.counts = new ArrayList<Count<E>>();
    }

    private Count<E> getCount(Object o) {
        for (Count<E> c : counts)
            if (c.getElement().equals(o))
                return c;
        return null;
    }

    @Override
    public void add(E e) {
        add(e, 1);
    }

    @Override
    public void add(E e, int n) {
        Count<E> c = getCount(e);
        if (c != null) {
            c.incrementCount(n);
        } else if (n > 0) {
            counts.add(new Count<E>(e, n));
        }
    }

    @Override
    public void remove(E e) {
        remove(e, 1);
    }

    @Override
    public void remove(E e, int n) {
        Count<E> c = getCount(e);
        if (c != null) {
            c.decrementCount(n);
            if (c.getCount() <= 0) {
                counts.remove(c);
            }
        }
    }

    @Override
    public int count(Object o) {
        Count<E> c = getCount(o);
        if (c != null)
            return c.getCount();
        return 0;
    }

    @Override
    public int size() {
        int size = 0;
        for (Count<E> c : counts) {
            size += c.getCount();
        }
        return size;
    }

    @Override
    public Hamper<E> sum(Hamper<? extends E> hamper) {
        Iterator<? extends Count<? extends E>> iterator = hamper.iterator();
        while (iterator.hasNext()) {
            Count<E> count = (Count<E>) iterator.next();
            this.add(count.getElement(), count.getCount());
        }
        return this;
    }

    @Override
    public Iterator<Count<E>> iterator() {
        return counts.iterator();
    }

    /**
     * For this method, hampers should be the same class to be equal (ignore the generic type component). For example, a CreativeHamper cannot be equal to a FruitHamper,
     * And a FruitHamper cannot be equal to an ArrayListItemHamper<Fruit>,
     * However an ArrayListItemHamper<Fruit> can be equal to a ArrayListItemHamper<Item> if they both only contain fruit.
     * HINT: use getclass() to compare objects.
     */
    @Override
    public boolean equals(Object o) {
        if (o.getClass() == this.getClass()) {
            Iterator<Count<E>> countIterator1 = this.iterator();
            Iterator<Count<E>> countIterator2 = ((ArrayListItemHamper) o).iterator();
            List<E> count1 = new ArrayList<>();
            List<E> count2 = new ArrayList<>();
            List<E> tmp = new ArrayList<>();
            int size1 = 0;
            int size2 = 0;
            while (countIterator1.hasNext()) {
                Count<E> countnext = countIterator1.next();
                size1 += countnext.getCount();
                count1.add(countnext.getElement());
            }
            while (countIterator2.hasNext()) {
                Count<E> countnext = countIterator2.next();
                size2 += countnext.getCount();
                count2.add(countnext.getElement());
            }
            if (size1 != size2) {
                return Boolean.FALSE;
            }
            if (count1.size() != count2.size()) {
                return Boolean.FALSE;
            }
            if (count1.containsAll(count2)) {
                return Boolean.TRUE;
            }
        }
        return Boolean.TRUE;
    }

    /**
     * @return price of the hamper - for ArrayListItemHamper, this should be the sum of the prices of items with each price multiplied by the number of times that item occurs
     */
    @Override
    public int getPrice() {
        return 0;
    }

    @Override
    public String toString() {
        return counts.toString();
    }

    public ArrayList<Count<E>> getCounts(){
        return counts;
    }
}
