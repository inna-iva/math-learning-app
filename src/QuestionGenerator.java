import java.util.*;

public class QuestionGenerator {

	// Main method to create and return a list of questions
	public static List<AdditionQuestion> generateQuestions() {
		List<AdditionQuestion> questions = new ArrayList<>();
		
		generateSimpleQuestions(questions); // 0-9  Adds simple single-digit questions
		generateTwoDigitQuestions(questions); // 10,20,30,.. Adds clean double-digit questions 
		generateSpecialDoubleDigit(questions); // 23, 45, 89,.. Adds tricky double-digit numbers 
		
		return questions;
	}
	
	 // Generate single-digit addition (0–10), no carrying
	private static void generateSimpleQuestions(List<AdditionQuestion> questions) {
		Random random = new Random();
		for (int i = 0; i < 4; i++) {
			int a = random.nextInt(11); //0-10
			int b = random.nextInt(11 - a); // Make sure sum doesn't exceed 10
			questions.add(new AdditionQuestion(a, b));
			
		}
	}
	
	// Generate round double-digit numbers (like 20 + 30)
	private static void generateTwoDigitQuestions(List<AdditionQuestion> questions) {
		Random random = new Random();
		
		for (int i = 0; i < 3; i++) {
			int a = (random.nextInt(9) + 1) * 10; // 10, 20...,90
			int b = (random.nextInt(9) + 1) * 10;
			questions.add(new AdditionQuestion(a, b, true)); // true  show as vertical fraction
		}
	}
	
	// Generate double-digit numbers that avoid carrying (like 23 + 54)
	private static void generateSpecialDoubleDigit(List<AdditionQuestion> questions) {
		Random random = new Random();
		int count = 0;
		
		while (count < 3) {
			
			int a = 10 + random.nextInt(90); // Random number 10-99
			int b = 10 + random.nextInt(90);
			
			int aOnes = a % 10; 
			int bOnes = b % 10;
			int aTens = a / 10;
			int bTens = b / 10;
			
			// Only allow if no carrying is needed in ones or tens digits
			if ((aOnes + bOnes <= 9) && (aTens + bTens <= 9)) {
				questions.add(new AdditionQuestion(a, b, true));
				count++;
			}
		}
	}
}
