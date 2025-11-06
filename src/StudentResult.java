
public class StudentResult {

	// Fields are private (encapsulation) — access only through getters	
	private String studentName;
	private int totalQuestions;
	private int correctAnswers;
	private double score;

	// Create constructor with results which we know (initialize student result data)
	public StudentResult(String studentName, int totalQuestions, int correctAnswers, double score) {
		this.studentName = studentName;
		this.totalQuestions = totalQuestions;
		this.correctAnswers = correctAnswers;
		this.score = score;
	}

	// Getter for student name
	public String getStudentName() {
		return studentName;
	}

	 // Getter for total number of questions
	public int getTotalQuestions() {
		return totalQuestions;
	}

	// Getter for number of correct answers
	public int getCorrectAnswers() {
		return correctAnswers;
	}

	// Getter for score (percentage)
	public double getScore() {
		return score;
	}

	// Prints the result in a formatted way
	public void printResult() {
		System.out.printf("Student: %s | Correct: %d/%d | Score: %.2f%%\n",
				studentName, correctAnswers, totalQuestions, score);
	}
	 // Returns result info as a string (using for saving to file)
	@Override
	public String toString() {
		return String.format("Student: %s | Correct: %d/%d | Score: %.2f%%", 
				studentName, correctAnswers, totalQuestions, score);
	}
}
