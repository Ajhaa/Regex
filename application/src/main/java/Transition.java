
public class Transition {
    char input;
    State state;

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