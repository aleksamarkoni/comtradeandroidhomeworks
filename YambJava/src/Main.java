import java.util.Random;

public class Main {

    public static void main(String args[]) {
        Hand hand = new Hand();
        hand.nextHand();
        System.out.println(hand);
        hand.nextHand(new int[]{1, 2, 5, 6});
        System.out.println(hand);
        hand.nextHand(new int[]{1, 2, 6});
        System.out.println(hand);
    }
}
