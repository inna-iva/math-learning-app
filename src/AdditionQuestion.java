
public class AdditionQuestion extends MathQuestion {
	private boolean fraction; // flag to print question vertically

	// Create constructor by Super class 
	// Constructor without fraction format
	public AdditionQuestion(int a, int b) {
		super(a, b, "+");
		this.fraction = false;
	}
	
	 // Constructor with option for vertical format
	public AdditionQuestion(int a, int b, boolean fraction) {
		super(a, b, "+");
		this.fraction = fraction;
	}
	
	// Prints the question: vertically if fraction is true, otherwise in one line
	@Override
	public void print() {
		if (fraction) {
			System.out.printf(" %4d\n+%3d\n----\n", operand1, operand2);
		} else {
	        System.out.printf("%d + %d = ?\n", operand1, operand2);  
	    }
	}
	
	// Override methods inherited from Super class
	// Compare user's result in child with superclass' result
	@Override
	public boolean checkAnswer(int answer) {
		return operand1 + operand2 == answer;
	}
	
	//This method help us to get correct answer if we got mistake
	@Override
	public int getCorrectResponse() {
		return operand1 + operand2;
	}
	
	// Returns the question text for console
	@Override
    public String toString() {
        if (fraction) {
            String line = "-----";
            return String.format("  %d\n+ %d\n%s", operand1, operand2, line);
        } else {
            return operand1 + " + " + operand2 + " = ?";
        }
    }

	
}
