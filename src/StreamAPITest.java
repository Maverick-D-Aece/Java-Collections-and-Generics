import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamAPITest {

	public static void main(String[] args) throws Exception {

		List<Student> studentList = new ArrayList<>();
		studentList.add(new Student("Alex", 91));
		studentList.add(new Student("Bob", 82));
		studentList.add(new Student("Caesar", 93));
		studentList.add(new Student("Dan", 74));
		studentList.add(new Student("Ethan", 85));
		studentList.add(new Student("Fymryn", 86));
		studentList.add(new Student("Gretchen", 77));
		studentList.add(new Student("Holly", 98));

		System.out.println("All students:");
		studentList.forEach(System.out::println);

		// finding students with marks >= 85 using Stream API
		List<Student> goodStudentList = studentList.stream().filter(s -> s.getScore() >= 85).collect(Collectors.toList());

		// printing the list of goodStudents using Stream API
		System.out.println("\nGood students:");
		goodStudentList.stream().forEach(System.out::println);

		// calculating the average score for all students
		double average = studentList.stream().mapToInt(s -> s.getScore()).average().getAsDouble();
		System.out.println("\nAverage score of all students: " + average);

		// a stream of integers from an array
		int[] intArr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 };
		IntStream intStream = Arrays.stream(intArr);
		System.out.print("\nEven numbers in the integer array/stream are: ");
		intStream.filter(i -> i % 2 == 0).sorted().forEach(eI -> System.out.print(eI + ", "));

		// obtaining a stream from a file
		BufferedReader bReader = new BufferedReader(new FileReader("file.txt"));
		Stream<String> streamLines = bReader.lines();
		System.out.println("\n\nLines from the file - ");
		streamLines.forEach(s -> System.out.println(s));
		bReader.close();

		/**
		 * Some of the common intermediate operations are: map(), filter(), limit(),
		 * distinct()
		 */

		/**
		 * Some of the common terminal operations are: collect(), reduce(), forEach(),
		 * count()
		 */

		// creating a parallel stream
		System.out.println();
		studentList.parallelStream().filter(s -> s.getScore() >= 85).forEach(System.out::println);

		// a stream does not change the original collection of which it is a stream

	}
}

class Student implements Comparable<Student> {
	private String name;
	private int score;

	public Student(String name, int score) {
		this.name = name;
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return this.name + " - " + this.score;
	}

	@Override
	public int compareTo(Student another) {
		return another.getScore() - this.score;
	}
}
