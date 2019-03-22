
public class Parser {
    String input;
    int start = 0;
    int current = 0;

    private final char e = 'ʒ';

    State first = new State(e, e, null, null);


    public Parser(String input) {
        this.input = input;
    }

    public State parse() {
        return parse(first, null, first);
    }

    public State parse(State currentS, State previous, State localFirst) {
        State end = new State (e, e, null, null, true);
        
        while (current < input.length()) {
            char c = input.charAt(current);
            State newState;
            switch (c) {
                case '*':          
                    char letter = input.charAt(current - 1);
                    previous.left = new Transition(e, currentS);
                    currentS.left = new Transition(e, null);

                    newState = new State(e, letter, null, currentS);
                    
                    currentS.setLeft(newState);
                    previous = currentS;
                    currentS = newState;
                    
                    break;
                case '|':
                    State newLocalFirst = new State(e, e, null, null);
                    localFirst.right = new Transition(e, localFirst);

                    current++;

                    newState = parse(newLocalFirst, localFirst, newLocalFirst);
                    localFirst.right = new Transition(e, newState);

                    break;
                default:
                    previous = currentS;
                    newState = new State(e, e, null, null);
                    currentS.left = new Transition(c, newState);
                    currentS = newState;
            }
            current++;
        }
        

        currentS.left = new Transition(e, end);
        return localFirst;
    }


}