package regex;

import regex.nfa.State;
import regex.nfa.Transition;
import regex.nfa.NFA;

import java.util.Stack;

public class Parser {
    private final static char e = 'ʒ';

    private static Stack<NFA> stack;
    private static Stack<ParserState> state;

    /**
     *
     * Starts parsing an NFA by pushing the root NFA into the stack
     */
    public static NFA parseNFA(String input) {
        stack = new Stack<>();
        state = new Stack<>();

        stack.push(NFA.root());
        return parseNFA(input, 0);
    }

    private static NFA parseNFA(String input, int i) {
        while (i < input.length()) {
            char c = input.charAt(i);

            switch (c) {
            case '|':
                state.push(ParserState.UNION);
                unionize();
                break;
            case '*':
                NFA last = stack.peek().inner;
                last.makeKleene();
                break;
            case '(':
                NFA inner = NFA.epsilon();
                stack.peek().connectInner(inner);
                stack.push(inner);
                state.push(ParserState.GROUPING);
                break;
            case ')':
                while (state.pop() == ParserState.UNION) {
                    stack.pop().connectToLast();
                }
                stack.pop().connectToLast();
                break;
            default:
                stack.peek().connectInner(NFA.concatenation(c));
            }
            i++;
        }
        NFA last = null;
        while (!stack.isEmpty()) {
            last = stack.pop();
            last.connectToLast();
        }

        return last;
    }

    private static void unionize() {
        NFA outer = stack.peek();
        outer.connectToLast();
        NFA branch = NFA.epsilon();
        outer.branch(branch);
        stack.push(branch);
    }
}