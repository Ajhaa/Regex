package regex;

import java.util.Random;

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
        times(regex);

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

    public static void times(String r) {
        Random rand = new Random();

        String alphabet = "abcdefghijklmnopqrstuwxyz";
        
        String s = "a";
        long time = System.nanoTime();
        NFA n = Parser.parseNFA(r);
        n.check(s);
        System.out.println("Parse time: " + (System.nanoTime() - time));
        for (int i = 0; i < 1000; i++) {
            //System.out.println(s);

            time = System.nanoTime();
            n.check(s);
            System.out.println(System.nanoTime() - time);
            //s += alphabet.charAt(rand.nextInt(alphabet.length()));
            s+= 'a';
        }
    }
}