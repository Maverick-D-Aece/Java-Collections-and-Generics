import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class SetTest {

	public static void main(String[] args) {

		// LinkedHashSet preserves the order of insertion
		Set<Contact> linkedPhonebook = new LinkedHashSet<>();
		linkedPhonebook.add(new Contact("Alex", "09127431"));
		linkedPhonebook.add(new Contact("Bob", "190243"));
		linkedPhonebook.add(new Contact("Cathy", "10928"));
		linkedPhonebook.add(new Contact("Elaine", "12094"));
		linkedPhonebook.add(new Contact("Felina", "120248"));
		linkedPhonebook.add(new Contact("Gabby", "098122"));
		linkedPhonebook.forEach(System.out::println);
		System.out.println();

		// HashSet does not give a damn
		Set<Contact> phonebook = new HashSet<>();
		phonebook.addAll(linkedPhonebook);
		phonebook.forEach(System.out::println);
		System.out.println();

		// TreeSet - a whole new beast, it completely disregards the 2nd rule of a Set
		// collection
		Set<Contact> treePhoneBook = new TreeSet<>(new PhoneComparator());
		treePhoneBook.addAll(phonebook);
		treePhoneBook.forEach(System.out::println);
		System.out.println();

		// Creating a Set of unique odd numbers from a list of integers using the Stream
		// API
		Set<Integer> uniqueOddNumbers = Arrays.asList(1, 1, 2, 3, 4, 4, 5, 6, 7, 8, 8, 9, 9, 0).stream()
				.filter(num -> num % 2 != 0).collect(Collectors.toSet());

		// iterating over a set using the forEach() method with Lambda Expression
		uniqueOddNumbers.forEach(System.out::println);
		System.out.println();

		// testing the clear method
		phonebook.clear();
		if (phonebook.isEmpty()) {
			System.out.println("The Set is empty!\n");
		}

		/**
		 * testing the bulk operations
		 * 
		 * Key:
		 * 
		 ****** Subset Operation -> .containsAll()
		 * 
		 ****** Union Operation -> .addAll()
		 * 
		 ****** Intersection Operation -> .retainAll() \ Difference Operation -> .removeAll()
		 * 
		 * Other examples have not been added for obvious reasons
		 */

		System.out.println(linkedPhonebook.containsAll(treePhoneBook));
		System.out.println();

		// concurrent sets
		Set<Integer> numbers = Collections.synchronizedSet(new HashSet<>());
		synchronized (numbers) {
			numbers.addAll(Arrays.asList(1, 1, 2, 2, 34, 546, 784, 78));
			numbers.forEach(System.out::println);
		}
		System.out.println();

		// testing other operations
		phonebook.addAll(linkedPhonebook);

		// Note that implementations made using "var" are of static type and cannot be
		// redefined to any other type except the type that the Collection was first
		// implemented with
		@SuppressWarnings("unused")
		var singletonSet = Collections.singleton(phonebook);
		// can't do this for singleton sets
		// singletonSet.add(new Contact("name", "198273"));

		var unmodifiableSet = Collections.unmodifiableSet(phonebook);

		// these will give a runtime exception UnsupportedOperationException
		// unmodifiableSet.add(new Contact("name", "198273"));
		// unmodifiableSet.removeIf(contact -> contact.getName().startsWith("C"));
		unmodifiableSet.forEach(System.out::println);
		System.out.println();

		var treeSet = new TreeSet<Contact>(new PhoneComparator());
		treeSet.addAll(phonebook);
		var unmodifiableSortedSet = Collections.unmodifiableSortedSet(treeSet);
		unmodifiableSortedSet.forEach(System.out::println);
	}
}