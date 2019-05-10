package regex;

import regex.nfa.NFA;

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
}