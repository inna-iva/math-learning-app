
public abstract class MathQuestion {
	
	// We are using protected because we need to use variable in subclasses
	// Accessible in subclasses: first number, second number, and the operator (+ or -)
	protected int operand1;
	protected int operand2;
	protected String operator;
	
	//Create constructor  to initialize question values
	public MathQuestion(int operand1, int operand2, String operator) {
		this.operand1 = operand1;
		this.operand2 = operand2;
		this.operator = operator;
	}
	
	// Create abstract methods to implement it in inheritors
	public abstract boolean checkAnswer(int answer); // checks if the user's answer is correct
	public abstract void print();  // prints the question 
	public abstract int getCorrectResponse(); // returns the correct answer
	
	// Returns the question
	public String getQuestionText() {
		return operand1 + " " + operator + " " + operand2 + " = ?";
	}
	
	//Returns question text when object is printed
	@Override
	public String toString() {
	    return getQuestionText();
	}
}
