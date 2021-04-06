import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class CollectionExample {
    public static void main(String[] args) throws Exception {

        // using the raw collection framework - added the <Object> generic to remove the
        // annoying warnings
        Collection<Object> collection = new ArrayList<Object>();
        collection.add("String");
        collection.add(1);
        collection.add(true);
        collection.add(2.5);

        // using iterator to iterate amongst the elements of the collection
        Iterator<Object> itr = collection.iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }

        // using a generic collection for a collection of strings
        Collection<String> strings = new ArrayList<>();
        strings.add("apple");
        strings.add("ball");
        strings.add("cat");
        strings.add("dog");
        strings.add("egg");

        Iterator<String> strItr = strings.iterator();
        System.out.print("\n" + strItr.next());
        while (strItr.hasNext()) {
            System.out.print(", " + strItr.next());
        }
    }
}
