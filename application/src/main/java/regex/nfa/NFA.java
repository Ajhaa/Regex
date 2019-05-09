package regex.nfa;

public class NFA {
    public State first;
    public State last;
    public NFA inner;

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
        this.first.left = new Transition(c, this.last);
    }

    public void connectInner(NFA nfa) {
        if (inner == null) {
            this.inner = nfa;
            this.first.left = new Transition(e, nfa.first);
        } else {
            this.inner.last.left = new Transition(e, nfa.first);
            this.inner = nfa;
        }
    }

    public void branch(NFA nfa) {
        this.inner = nfa;
        this.first.right = new Transition(e, nfa.first);
    }

    public void connectToLast() {
        if (inner != null) {
            inner.last.left = new Transition(e, this.last);
        }
    }

    public boolean check(String s) {
        return this.first.check(s, 0);
    }

    /**
     * Wraps an NFA into a kleene star
     */
    public void makeKleene() {
        State temp = new State(first.left, first.right);
        State newLast = new State();

        first.left = new Transition(e, temp);
        first.right = new Transition(e, newLast);

        last.left = new Transition(e, newLast);
        last.right = new Transition(e, temp);
        last = newLast;
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