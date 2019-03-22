import java.util.HashMap;

public class State {
    Transition left;
    Transition right;
    boolean accept;

    public State(char left, char right, State leftS, State rightS, boolean accept) {
        this.left = new Transition(left, leftS);
        this.right = new Transition(right, rightS);
        this.accept = accept;
    }

    public State(char left, char right, State leftS, State rightS) {
        this(left, right, leftS, rightS, false);
    }

    public boolean check(String s, int i) {
        boolean any = false;

        //System.out.println("i: " + i + " current: " + this + " l: " + left.input + " " + left.state + " r: "+ right.input + " " + right.state);

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