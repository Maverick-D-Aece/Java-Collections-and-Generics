import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListTest {
	public static void main(String[] args) {
		Set<Contact> phonebook = new HashSet<>();
		phonebook.add(new Contact("Alex", "09127431"));
		phonebook.add(new Contact("Bob", "190243"));
		phonebook.add(new Contact("Cathy", "10928"));
		phonebook.add(new Contact("Elaine", "12094"));
		phonebook.add(new Contact("Felina", "120248"));
		phonebook.add(new Contact("Gabby", "098122"));

		phonebook.forEach(System.out::println);

		var contactList = new ArrayList<Contact>(100);
		contactList.addAll(phonebook);

		// this is sorting based on the name of the Contact because we have implemented
		// the compareTo method to do exactly that (Compare based on the name)
		Collections.sort(contactList);
		System.out.println("\n");
		contactList.forEach(System.out::println);

		// this will produce a compile time error
		// contactList = new LinkedList<Contact>(100);

		var item = contactList.set(2, new Contact("Bella", "812371"));
		System.out.println("\nRemoved Contact - " + item);
		// contactList.add(item);
		if (contactList.contains(item)) {
			System.out.println("Contact exists in the phonebook!");
		} else {
			System.out.println(item.getName() + "'s contact does not exist in the phonebook!");
		}

		// A collection can be sorted in one of the following ways
		Collections.sort(contactList);
		/*
		 * the following provides much flexibility as it has one parameter asking for a
		 * comparator which can be custom-implemented for varying results
		 */
		// contactList.sort(null);

		System.out.println();
		contactList.forEach(System.out::println);

		// Testing Collections.copyList() method
		List<String> sourceList = new ArrayList<String>();
		sourceList.add("A");
		sourceList.add("B");
		sourceList.add("C");
		sourceList.add("D");
		List<String> destList = new ArrayList<String>(10);
		destList.add("V");
		destList.add("W");
		destList.add("X");
		destList.add("Y");
		destList.add("Z");
		System.out.println("\ndestList before copy: " + destList);
		Collections.copy(destList, sourceList);
		System.out.println("destList after copy: " + destList);

		// Testing the Collections.shuffle() method
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		System.out.println("\nBefore shuffling -> " + numbers);
		Collections.shuffle(numbers);
		System.out.println("After shuffling  -> " + numbers);

		// Testing the Collections.reverse() method
		List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		System.out.println("\nBefore reversing -> " + nums);
		Collections.reverse(nums);
		System.out.println("After reversing  -> " + nums);

		// List to Stream Example
		int sum = nums.stream().reduce(0, (i, j) -> i + j);
		System.out.println("\nSum of 1 to 10 = " + sum);

		// Concurrent ArrayList equivalent
		List<Contact> concurrentContactList = new CopyOnWriteArrayList<>();
		concurrentContactList.addAll(contactList);

		// printing a concurrent list through a parallel stream
		System.out.println();
		Collections.sort(concurrentContactList); // this does not seem to work
		concurrentContactList.parallelStream().forEach(System.out::println);

		// testing the Comparators
		Collections.sort(contactList, new PhoneComparator());
		System.out.println("\nSorting using Phone Comparator");
		contactList.forEach(System.out::println);

		Collections.sort(contactList, new NameComparator());
		System.out.println("\nSorting using Name Comparator");
		contactList.forEach(System.out::println);
	}
}

class Contact implements Comparable<Contact> {

	private static long id = 0;
	private String name;
	private String phone;

	public Contact(String name, String phone) {
		this.name = name;
		this.phone = phone;
		id++;
	}

	public static long getId() {
		return id;
	}

	public static void setId(long id) {
		Contact.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Contact [name=" + name + ", phone=" + phone + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contact other = (Contact) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		return true;
	}

	@Override
	public int compareTo(Contact o) {
		return this.getName().compareTo(o.getName());
	}
}

// implementing some comparator classes for the Contacts
class NameComparator implements Comparator<Contact> {

	@Override
	public int compare(Contact o1, Contact o2) {
		return o1.getName().compareTo(o2.getName());
	}

}

class PhoneComparator implements Comparator<Contact> {

	@Override
	public int compare(Contact o1, Contact o2) {
		return new BigInteger(o1.getPhone()).compareTo(new BigInteger(o2.getPhone()));
	}

}