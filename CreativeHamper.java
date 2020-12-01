package part2Q2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CreativeHamper extends ArrayListItemHamper<Item> {
    /**
     * invariant: if hamper contains 5 or more items, it must contain at least 2 toy cars (at least 1 must be premium) and at least 2 fruits. Otherwise, adding an item should do nothing
     * creative hamper has a price surcharge of $10
     */


    @Override
    public int getPrice() {
        int price = 0;
        Iterator<Count<Item>> countIterator = this.iterator();
        List<Item> count = new ArrayList<>();
        while (countIterator.hasNext()) {
            Count<Item> countnext = countIterator.next();
            count.add(countnext.getElement());
        }
        for (Item item : count) {
            price += item.getPrice();
        }
        price = price + 10;
        return price;
    }

    @Override
    public void add(Item e, int n) {
        int toyCars = 0;
        int fruits = 0;
        
        ArrayList<Count<Item>> counts = this.getCounts();
        List<Item> count = new ArrayList<>();
        for (Count<Item> itemCount : counts) {
            count.add(itemCount.getElement());
            if (itemCount.getElement() instanceof Apple) {
                toyCars++;
            }
            if (itemCount.getElement() instanceof Avocado) {
                fruits++;
            }
        }
        if (count.size() == 5) {
            if (e instanceof ToyCar) {
                toyCars++;
            } else if (e instanceof Fruit) {
                fruits++;
            }
            if (toyCars < 2 || fruits < 2) {
                return;
            }
        }
        super.add(e, n);

    }
}
