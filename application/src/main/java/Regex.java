
import java.util.Arrays;

public class Regex {
    public static void main(String[] args) {
        /*if (args.length == 0) {
            System.out.println("Needs arghs");
        } else {
            System.out.println(args[0]);
        } */

        State f = new State('ʒ', 'ʒ', null, null, true);

        State a = new State('a', 'ʒ', null, null, false);
        
        State b = new State('ʒ', 'ʒ', a, null, false);

        a.setLeft(b);

        State d = new State('b', 'ʒ', null, f, false);
        
        State c = new State('ʒ', 'ʒ', d, null, false);

        a.setRight(c);

        d.setLeft(c);


        State s = new State('ʒ', 'ʒ', b, null, false);

        System.out.println(s.check("", 0));
        System.out.println(s.check("bbb", 0));
        System.out.println(s.check("aabb", 0));
        System.out.println(s.check("aaaa", 0));




    }
}