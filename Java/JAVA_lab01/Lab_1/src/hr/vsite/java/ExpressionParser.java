package hr.vsite.java;

import java.lang.reflect.Array;
import java.util.List;

public class ExpressionParser {

    private String[] expression;
    public Double Result;

    public ExpressionParser(String expr){
        /// Convert simple string expression into char array
        char[] arr = expr.toCharArray();
        expression = new String[expr.length()];
        ///---------------------------------------------------

        // Initialize String Array 'expression' to "" value
        for(int i = 0; i < expr.length(); ++i)
            expression[i] = "";
        ///---------------------------------------------------
        Result = 0.0;
        parse(arr);

    }
    public Double Evaluate() {

        String[] operators = new String[]{"*", "/", "+", "-"};
        Double res = 0.0;


        for (int i = 0; i < operators.length; ++i) {
            for (int j = 0; j < expression.length; ++j)
                if (expression[j].equals(operators[i])) {
                    res = eval(Double.parseDouble(expression[j - 1]), Double.parseDouble(expression[j + 1]), operators[i]);
                    expression[j - 1] = res.toString();
                    expression[j] = "";
                    expression[j + 1] = "";
                    evalFormat(expression);
                    continue;
                }
        }
        return Double.parseDouble(expression[0]);
    }
    public void PrintExpressionInSequence(){
        for( String st : expression){
            System.out.println(st);
        }
    }

    /// Parser that differentiates operators from operands
    private void parse(char[] arr) {
        int j = 0;
        for (int i = 0; i < arr.length; ++i) {
            // Recognize the operator from within the char[] arr
            if (!checkIfOperator(arr[i])) {
                expression[j] += String.valueOf(arr[i]);
                continue;
            }
            // Recognize the operand value from within the char[] arr
            else {
                j++;
                expression[j] = String.valueOf(arr[i]);
                j++;
            }
        }
    }

    /// helper method for establishing the four main operators
    public boolean checkIfOperator(char op){

        switch(op){
            case '+':
            case '-':
            case '/':
            case '*':
                return true;
            default:
                return false;

        }
    }

    // helper method for calculation based od operator type
    private double eval(double op1, double op2, String oper) {
        double result = 0;
        switch (oper) {
            case "+":
                result = op1 + op2;
                break;
            case "-":
                result = op1 - op2;
                break;
            case "/":
                result = op1 / op2;
                break;
            case "*":
                result = op1 * op2;
                break;
            default:
                return 0;

        }
        return result;
    }

    /// helper method for formatting expression
    public static void evalFormat(String[] eval){

        for (int i = 0; i < eval.length; ++i){

            if(eval[i] == "" || eval[i] == " " || eval[i] == "  "){
                // scan
                for(int j = i+1; j < eval.length; ++j){
                    if(eval[j] != "" && eval[j] != " " && eval[j] != "  "){
                        eval[i] = eval[j];
                        eval[j] = "";
                        break;
                    }
                }
            }

        }
    }
}
