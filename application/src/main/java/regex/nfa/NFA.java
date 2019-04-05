package regex.nfa;

public class NFA {
    public State first;
    public State last;

    private static char e = 'ʒ';

    public NFA(State first, State last) {
        this.first = first;
        this.last = last;
    }

    public NFA() {
        this(new State(), new State());
    }

    public NFA(char c) {
        this();
        NFA nfa = new NFA();
        nfa.first.left = new Transition(c, nfa.last);
    }

    public static NFA root() {
        NFA nfa = new NFA();
        nfa.last.accept = true;
        return nfa;
    }

    /**
     *
     * @return NFA with epsilon transition between first state and last state
     */
    public static NFA epsilon() {
        return new NFA(e);
    }

    /**
     *
     * @param c accepted character for the transition
     * @return NFA with character transition between first state and last state
     */
    public static NFA concatenation(char c) {
        return new NFA(c);
    }
}