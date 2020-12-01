package part2Q2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FruitHamper extends ArrayListItemHamper<Fruit> {
    /**
     * invariant: FruitHamper must have at least 2 apples and 2 avocados when have >= 6 fruits. Otherwise, adding an item should do nothing
     * fruit hamper has price surcharge of 25%, rounded up to nearest integer
     */

    @Override
    public int getPrice() {
        int price = 0;
        Iterator<Count<Fruit>> countIterator = this.iterator();
        List<Fruit> count = new ArrayList<>();
        while (countIterator.hasNext()) {
            Count<Fruit> countnext = countIterator.next();
            count.add(countnext.getElement());
        }
        for (Fruit fruit : count) {
            price += fruit.getPrice();
        }
        price = new Double(price * 1.25).intValue();
        return price;
    }

    @Override
    public void add(Fruit e, int n) {
        int apples = 0;
        int avocados = 0;
        
        ArrayList<Count<Fruit>> counts = this.getCounts();
        List<Fruit> count = new ArrayList<>();
        for (Count<Fruit> fruitCount : counts) {
            count.add(fruitCount.getElement());
            if (fruitCount.getElement() instanceof Apple) {
                apples++;
            }
            if (fruitCount.getElement() instanceof Avocado) {
                avocados++;
            }
        }
        if (count.size() == 5) {
            if (e instanceof Apple) {
                apples++;
            } else if (e instanceof Avocado) {
                avocados++;
            }
            if (apples < 2 || avocados < 2) {
                return;
            }
        }
        super.add(e, n);

    }
}
