package hr.vsite.java;

import java.util.Arrays;

public class main {
    public static void main(String[] args){

        ExpressionParser ps = new ExpressionParser(args[0]);
        ps.PrintExpressionInSequence();
        Double res = ps.Evaluate();

        System.out.println("Result: " + res);

    }

}
