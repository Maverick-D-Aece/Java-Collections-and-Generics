import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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
        System.out.println("\n");

        // using Map.Entry to get all the values in a map
        Map<String, String> phonebook = new HashMap<>();
        phonebook.put("Alex", "9876543210");
        phonebook.put("Bob", "9999999999");
        phonebook.put("Catrina", "9878767650");

        // Entry is a nested interface inside the Map interface
        Set<Map.Entry<String, String>> values = phonebook.entrySet();
        for (Map.Entry<String, String> entry : values) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

    }
}
