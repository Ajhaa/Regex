
import java.util.Arrays;

public class Regex {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Needs arghs");
            return;
        }

        String regex = args[0];
        String compared = args[1];

        Parser parser = new Parser(regex);

        State s = parser.parse();
        System.out.println(s.check(compared, 0));        
        
    }
}