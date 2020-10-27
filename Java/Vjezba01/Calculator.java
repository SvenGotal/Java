public class Calculator{

	public static void main(String args[]){
	
		double op1 = Double.valueOf(args[0]);
		String operator = args[1];
		double op2 = Double.valueOf(args[2]);

		double result = 0;

		switch(operator){
	
			case "*":
				result = op1 * op2;
				break;
			case "/":
				result = op1 / op2;
				break;
			case "+":
				result = op1 + op2;
				break;
			case "-":
				result = op1 - op2;
				break;
			default:
				System.out.println("Error");
				return;
		}
		System.out.println("Rezultat je: " + result);
		return;
	}

}
