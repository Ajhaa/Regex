package regex;

import regex.nfa.NFA;
import regex.nfa.State;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Needs arghs");
            return;
        }

        String regex = args[0];
        String compared = args[1];

        NFA n = Parser.parseNFA(regex);
        //System.out.println(n);
        System.out.println(n.check(compared));
    }

    public static String stateString(State st) {
        if (st == null) {
            return "";
        }
        String s = "(";
        s += st.left.input;
        s += stateString(st.left.state);
        return s;
    }
}