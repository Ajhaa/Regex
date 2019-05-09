package regex.nfa;


/**
 * Representation of a single state in an NFA
 * Has a maximum of two transitions to other states
 */
public class State {
    public Transition left;
    public Transition right;
    public boolean accept;

    /**
     * 
     * @param left letter of left transition
     * @param right letter of right transition
     * @param leftS state in left transition
     * @param rightS state in right transition
     * @param accept is true, the state is accepting
     */
    public State(char left, char right, State leftS, State rightS, boolean accept) {
        this.left = new Transition(left, leftS);
        this.right = new Transition(right, rightS);
        this.accept = accept;
    }

    public State(Transition left, Transition right) {
        this.left = left;
        this.right = right;
        this.accept = false;
    }

    public State(char left, char right, State leftS, State rightS) {
        this(left, right, leftS, rightS, false);
    }

    public State() {
        this('ʒ', 'ʒ', null, null);
    }

    /**
     * Checks a string recursively against a NFA starting from this state
     * @param s string to test
     * @param i index of the string
     * @return
     */
    public boolean check(String s, int i) {
        boolean any = false;

        char c = 'ʒ';
        if (i < s.length()) {
            c = s.charAt(i);
        } else if (!left.hasNext() && !right.hasNext()) {
            return accept;
        }

        if (left.hasNext()) {
            if (left.isEpsilon()) {
                any = any || left.state.check(s, i);
            } else if (c == left.input){
                any = any || left.state.check(s, i+1);
            }
        }

        if (right.hasNext()) {
            if (right.isEpsilon()) {
                any = any || right.state.check(s, i);
            } else if (c == right.input){
                any = any || right.state.check(s, i+1);
            }
        }

        return any;

    }

    public void setLeft(State l) {
        this.left.state = l;
    }

    public void setRight(State r) {
        this.right.state = r;
    }
}