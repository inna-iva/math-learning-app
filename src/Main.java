import java.io.*;
import java.util.*;

public class Main {

	// Create fields with Objects which will be using by all methods bellow
	// Attributes will be static because we use it in all methods
	// Store all quiz results
	static List<StudentResult> results = new ArrayList<>(); 
	
	// Read user's input
	static Scanner input = new Scanner(System.in); 

	public static void main(String[] args) {

		boolean runningProgram = true;

		// Keep showing the menu until user exits
		while (runningProgram) {
			showMenu();

			int choice = input.nextInt();
			input.nextLine(); // skip \n after number

			switch (choice) {
			case 1 -> takeQuiz(); 		// Start a new quiz
			case 2 -> saveResultsToFile();// Save results to file
			case 3 -> displayResults(); // Show all results
			case 4 -> {
				System.out.println("Good Bye!"); 
				runningProgram = false;   // Exit the program
			}
			default -> System.out.print("This option is not correct. Try again.");
			}
		}

	}

	// Show the main menu to the user
	static void showMenu() {
		System.out.print("	Welcome\n" + "***********************\n" + "1. Take a test\n" + "2. Save results to file\n"
				+ "3. Show all results\n" + "4. Exit\n" + "***********************\n"
				+ "Enter you option (1, 2, 3 or 4): ");

	}

	// Handles the whole quiz process
	static void takeQuiz() {
		System.out.print("Enter student's name: ");
		String name = input.nextLine();

		// Create randomly 5 questions (add, sub)
		List<MathQuestion> questions = new ArrayList<>();
		Random random = new Random();

		// Simple questions (first 4 questions), numbers in range 0-10
		for (int i = 0; i < 4; i++) {
			int a, b;
			do {
				a = random.nextInt(11);
				b = random.nextInt(11);
			} while (a + b > 10); // keep total small

			// If the random value is correct, you perform an addition operation.
			if (random.nextBoolean()) {
				questions.add(new AdditionQuestion(a, b));
			} else {
				// If the random value is incorrect, you perform a subtraction operation.
				questions.add(new SubstractionQuestion(Math.max(a, b), Math.min(a, b)));
			}

		}

		// Next 3 questions with round numbers like 10, 20, ..., 90
		for (int i = 0; i < 3; i++) { // three questions
			int[] options = { 10, 20, 30, 40, 50, 60, 70, 80, 90 };
			int a, b;
			do {
				a = options[random.nextInt(options.length)];
				b = options[random.nextInt(options.length)];
			} while (a + b > 100);

			if (random.nextBoolean()) {
				questions.add(new AdditionQuestion(a, b, true));
			} else {
				questions.add(new SubstractionQuestion(Math.max(a, b), Math.min(a, b), true));
			}
		}

		 // Last 3 questions with 2-digit numbers
		int count = 0;
		while (count < 3) {
			int aTens = random.nextInt(9) + 1;
			int bTens = random.nextInt(9) + 1;
			int aOnes = random.nextInt(10);
			int bOnes = random.nextInt(10);

			int a = aTens * 10 + aOnes; // create number
			int b = bTens * 10 + bOnes; 

			// Check that the sum of the numbers is not greater than 100 and that they are correct
			if (a <= 99 && b <= 99) {
				if (random.nextBoolean()) {
					if (a + b <= 99 && (a % 10 + b % 10) < 10) {
						questions.add(new AdditionQuestion(a, b, true));
						count++;
					}
				} else {
					if (a >= b && (a % 10) >= (b % 10)) {
						questions.add(new SubstractionQuestion(a, b, true));
						count++;
					}

				}
			}
		}

		// Ask each question, check answer, and count correctness
		int correctCount = 0;
		for (MathQuestion q : questions) {
			System.out.println("Generated question: \n" + q);// Print the question

			int studentAnswer;
			while (true) {
				System.out.print("Your answer: ");
				if (input.hasNextInt()) {
					studentAnswer = input.nextInt();
					input.nextLine();
					break;
				} else {
					System.out.println("Please enter a valid number!");
					input.nextLine(); // skip invalid input
				}
			}

			if (q.checkAnswer(studentAnswer)) {
				System.out.println("Correct!\n");
				correctCount++;
			} else {
				System.out.printf("Wrong! Correct answer: %d\n", q.getCorrectResponse());
			}
		}

		// Calculate and store the result
		int total = questions.size();
		double score = (correctCount * 100.0) / total;
		StudentResult result = new StudentResult(name, total, correctCount, score);
		results.add(result);
	}

	// Display all saved quiz results
	static void displayResults() {
		if (results.isEmpty()) {
			System.out.println("No results yet.");
		} else {
			for (StudentResult r : results) {
				r.printResult();
			}
		}
	}
	
	 // Save results to a text file sorted by score
	static void saveResultsToFile() {
		if (results.isEmpty()) {
			System.out.println("No results to save.");
			return;
		}

		// Sort results from highest to lowest score
		results.sort((r1, r2) -> Double.compare(r2.getScore(), r1.getScore()));

		// Try-catch block to handle any input/output exceptions and print an error message
		// Write results to the file and name it 
		// Iterate through the list of results and write each one to the file
		try (FileWriter writer = new FileWriter(new File("resourcesForFiles/results.txt"))) {
			writer.write("Results sorted by score (highest to lowest):\n\n");
			for (StudentResult r : results) {
				writer.write(r.toString() + "\n");
			}
			 // Notify the user in the console that the results were successfully saved
			System.out.println("Results saved to results.txt");
		} catch (IOException e) {
			System.out.println("Error writing to file: " + e.getMessage());
		}
	}
}
