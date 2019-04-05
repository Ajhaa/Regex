package regex;

import regex.nfa.State;
import regex.nfa.Transition;

/**
 *  Used to parse a regex string to an NFA
 * 
 */

public class Parser {
    // Shortcut for epsilon character
    private final static char e = 'ʒ';

    

    /**
     * The god-method used for parsing.
     * @param currentS Current state of the parser; the last state that was created
     * @param previous State created befure currentS
     * @param i index of the regex string
     * @param input input regex string
     * @return first state of the NFA
     */
    public static State parse(State currentS, State previous, int i, String input) {
        State localFirst = currentS;
        State ns = new State();
        localFirst.setLeft(ns);
        previous = currentS;
        currentS = ns;

        State localLast = new State();
        while (i < input.length()) {
            char c = input.charAt(i);
            State newState;
            switch (c) {
                // Kleene star; zero or more
                case '*':
                    newState = new State();
                    previous.setRight(newState);
                    State temp = new State(previous.left.input, e, previous.left.state, null);
                    previous.left = new Transition(e, temp);
                    currentS.setLeft(newState);
                    currentS.setRight(previous.left.state);
                    previous = currentS;
                    currentS = newState;
                    break;
                // Union; or    
                case '|':
                    currentS.setLeft(localLast);
                    newState = new State();
                    localFirst.setRight(newState);
                    previous = localFirst;
                    currentS = newState;
                    localFirst = newState;
                    break;
                // Braces; grouping    
                case '(':
                    int i0 = i;
                    int braces = 1;
                    i++;
                    while (i < input.length()) {
                        char ch = input.charAt(i);
                        // Consume characters until ending brace is found
                        if (ch == ')') {
                            braces--;
                        } else if (ch == '(') {
                            braces++;
                        }

                        if (braces == 0) {
                            newState = new State();
                            currentS.setLeft(newState);
                            previous = currentS;
                            currentS = newState;
                            
                            // Recursively call parse for substring inside braces
                            currentS = parse(currentS, previous, 0, input.substring(i0+1, i));
                            break;
                        }
                        i++;
                    }
                    break;
                //Default handles concatenation
                default:
                    previous = currentS;
                    newState = new State();
                    currentS.left = new Transition(c, newState);
                    currentS = newState;
            }
            i++;

        }
        currentS.setLeft(localLast);

        return localLast;
    }

    /**
     * Method to start the parse
     */
    public static State parse(String input) {
        State first = new State();
        State last = parse(first, null, 0, input);
        last.left = new Transition(e, new State(e, e, null, null, true));
        return first;
    }
}