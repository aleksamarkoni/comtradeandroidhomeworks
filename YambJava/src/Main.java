public class Main {

    public static void main(String args[]) {
        Hand hand = new Hand();
        hand.nextThrow(); //prvo bacanje
        //kraj
        hand.zadrziKockice();
        hand.nextThrow(); //drugo bacanje
        //kraj
        hand.zadrziKockice();
        //hand.nextThrow(); // trece bacanje
        //kraj
        System.out.println(hand);
    }
}