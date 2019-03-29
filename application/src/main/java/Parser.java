
public class Parser {
    private final static char e = 'ʒ';


    public static State parse(String input) {
        State first = new State();
        State last = parse(first, null, 0, input);
        last.left = new Transition(e, new State(e, e, null, null, true));
        return first;
    }


    public static State parse(State currentS, State previous, int i, String input) {
        State localFirst = currentS;
        State ns = new State();
        localFirst.setLeft(ns);
        previous = currentS;
        currentS = ns;

        State localLast = new State();
        while (i < input.length()) {
            char c = input.charAt(i);
            State newState;
            switch (c) {
                case '*':
                    newState = new State();
                    previous.setRight(newState);
                    State temp = new State(previous.left.input, e, previous.left.state, null);
                    previous.left = new Transition(e, temp);
                    currentS.setLeft(newState);
                    currentS.setRight(previous.left.state);
                    previous = currentS;
                    currentS = newState;
                    break;
                case '|':
                    currentS.setLeft(localLast);
                    newState = new State();
                    localFirst.setRight(newState);
                    previous = localFirst;
                    currentS = newState;
                    localFirst = newState;
                    break;
                case '(':
                    int i0 = i;
                    int braces = 1;
                    i++;
                    while (i < input.length()) {
                        char ch = input.charAt(i);
                        if (ch == ')') {
                            braces--;
                        } else if (ch == '(') {
                            braces++;
                        }

                        if (braces == 0) {
                            newState = new State();
                            currentS.setLeft(newState);
                            previous = currentS;
                            currentS = newState;

                            currentS = parse(currentS, previous, 0, input.substring(i0+1, i));
                            break;
                        }
                        i++;
                    }
                    break;
                default:
                    previous = currentS;
                    newState = new State();
                    currentS.left = new Transition(c, newState);
                    currentS = newState;
            }
            i++;

        }
        currentS.setLeft(localLast);

        return localLast;
    }
}