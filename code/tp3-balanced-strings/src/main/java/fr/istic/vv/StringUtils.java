package fr.istic.vv;

import java.util.List;
import java.util.Objects;
import java.util.Stack;

public class StringUtils {

    private StringUtils() {}

    private static final List<Character> groupingSymbols_open = List.of('(', '[', '{');
    private static final List<Character> groupingSymbols_close = List.of(')', ']', '}');


    //Exemple : {[][]}({})
    public static boolean isBalanced(String str) {
        if(str == null){throw new IllegalArgumentException();}
        // Stack to keep track of opening brackets
        Stack<Character> stack = new Stack<>();

        // Loop through each character in the string
        for (char ch : str.toCharArray()) {
            // Push opening brackets onto the stack
            if (groupingSymbols_open.contains(ch)) {
                stack.push(ch);
            }
            // For closing brackets, check if they match the top of the stack
            else if (groupingSymbols_close.contains(ch)) {
                if (stack.isEmpty()) {
                    return false; // Unmatched closing bracket
                }

                char top = stack.pop();
                // Ensure the popped bracket matches the current closing bracket
                if (!isMatchingPair(top, ch)) {
                    return false;
                }

            }
        }
        // If the stack is empty, all brackets were matched
        return stack.isEmpty();
    }

    // Helper method to check if brackets match
    private static boolean isMatchingPair(char open, char close) {
        return (open == '(' && close == ')') ||
                (open == '[' && close == ']') ||
                (open == '{' && close == '}');
    }

}

