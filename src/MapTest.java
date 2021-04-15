import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class MapTest {

	public static void main(String[] args) {
		Map<Integer, String> httpErrors = new HashMap<>();
		httpErrors.put(200, "OK");
		httpErrors.put(303, "See Other");
		httpErrors.put(404, "Not Found");
		httpErrors.put(500, "Internal Server Error");
		System.out.println(httpErrors);
		System.out.println();

		Set<Contact> linkedPhonebook = new LinkedHashSet<>();
		linkedPhonebook.add(new Contact("Alex", "09127431"));
		linkedPhonebook.add(new Contact("Bob", "190243"));
		linkedPhonebook.add(new Contact("Cathy", "10928"));
		linkedPhonebook.add(new Contact("Elaine", "12094"));
		linkedPhonebook.add(new Contact("Felina", "120248"));
		linkedPhonebook.add(new Contact("Gabby", "098122"));

		// creating a LinkedHashMap
		Map<String, String> phoneBook = new LinkedHashMap<>();
		linkedPhonebook.forEach(c -> phoneBook.put(c.getName(), c.getPhone()));
		phoneBook.keySet().forEach(key -> System.out.println(key + ": " + phoneBook.get(key)));
		System.out.println();

		// creating a TreeMap
		Set<Contact> phBook = new HashSet<>();
		phBook.addAll(linkedPhonebook);

		// adding an unnecessary comparator
		var treePhoneBook = new TreeMap<String, String>((s, t) -> s.compareTo(t));
		phBook.forEach(c -> treePhoneBook.put(c.getName(), c.getPhone()));
		treePhoneBook.keySet().forEach(key -> System.out.println(key + ": " + phoneBook.get(key)));
		System.out.println();

		// testing the entrySet() method
		Set<Map.Entry<String, String>> entries = treePhoneBook.entrySet();
		entries.forEach(entry -> System.out.println(entry.getKey() + " => " + entry.getValue()));
		System.out.println();

		/**
		 * A summary of bulk operations on Maps
		 * 
		 * clear() - removes all mappings from the map
		 * 
		 * putAll(Map<K, V> m) - copies all the mappings from the specified map
		 */

		// creating a concurrent map
		// we can use ConcurrentHashMap too but the following example features
		// Collections.synchronizedMap() method or synchronizedSortedMap() in this case
		// of TreeMap
		Map<String, String> concurrentMap = Collections.synchronizedSortedMap(treePhoneBook);
		// and this needs to be manually synchronized when iterating
		Set<String> keySet = concurrentMap.keySet();
		synchronized (concurrentMap) {
			Iterator<String> iterator = keySet.iterator();
			while (iterator.hasNext()) {
				String key = iterator.next();
				String value = concurrentMap.get(key);
				System.out.println(value);
			}
		}

	}
}