
import java.util.Arrays;

public class Regex {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Needs arghs");
            return;
        }

        String regex = args[0];
        String compared = args[1];

        State s1 = Parser.parse(regex);
        //System.out.println(s.check(compared, 0));
        System.out.println(s1.check(compared, 0));
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
}