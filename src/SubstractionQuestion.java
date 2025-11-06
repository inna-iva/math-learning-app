
public class SubstractionQuestion extends MathQuestion {
	
	private boolean fraction; // if true, show question vertically
	
	 // Constructor without fraction format
	public SubstractionQuestion(int a, int b) {
		super(Math.max(a, b), Math.min(a, b), "-");
		this.fraction = false;
	}	
	
	// Constructor with option for vertical format
	public SubstractionQuestion(int a, int b, boolean fraction) {
		super(Math.max(a, b), Math.min(a, b), "-");
		this.fraction = fraction;
	}
	
	// Compare user's result in child with superclass' result
	@Override
	public boolean checkAnswer(int answer) {
		
		return operand1 - operand2 == answer;
	}
	
	 // Prints the question: vertically if fraction is true
	@Override
	public void print() {
		if (fraction) {
			System.out.printf("% 4d\n-%3d\n----\n", operand1, operand2);
		} else {
			System.out.printf("%d - %d = ?\n", operand1, operand2);  
	    }
	}
		
	// Use this method to show correct answer if user make mistake
	@Override
	public int getCorrectResponse() {
		return operand1 - operand2;
	}	
	
	// Returns the question as text
	@Override
    public String toString() {
        if (fraction) {
            String line = "-----";
            return String.format("  %d\n- %d\n%s", operand1, operand2, line);
        } else {
            return operand1 + " - " + operand2 + " = ?";
        }
    }
}
