package regex.nfa;

public class Transition {
    public char input;
    public State state;

    public Transition(char input, State state) {
        this.input = input;
        this.state = state;
    }

    public boolean hasNext() {
        return state != null;
    }

    public boolean isEpsilon() {
        return input == 'ʒ';
    }
}