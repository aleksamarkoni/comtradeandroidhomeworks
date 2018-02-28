import java.util.Arrays;
import java.util.Random;

public class Hand {
    public static int[] hand;
    int rez[];
    private int numberOfDiceces;
    private int numberOfSides;
    Random random;

    public Hand() {
        this.numberOfDiceces = 6;
        this.numberOfSides = 6;
        hand = new int[numberOfDiceces];
        rez = new int[numberOfSides];
        random = new Random(400);
    }

    public Hand(int numberOfDiceces, int numberOfSides) {
        this.numberOfDiceces = numberOfDiceces;
        this.numberOfSides = numberOfSides;
        hand = new int[numberOfDiceces];
        rez = new int[numberOfSides];
        random = new Random(400);
    }

    public void nextHand() {
        for (int i = 0; i < numberOfDiceces; i++) {
            hand[i] = 1 + random.nextInt(numberOfSides);
        }
        izbrojKockice();
    }

    public void nextHand(int izborKockica[]) {
        for (int i = 0; i < izborKockica.length; i++) {
            int kockicaKojuMenjamo = izborKockica[i]-1;
            hand[kockicaKojuMenjamo] = 1 + random.nextInt(numberOfSides);
        }
        izbrojKockice();
    }

    public int yumb() {
        return daLiImaBarem(5);
    }

    public int poker() {
        return daLiImaBarem(4);
    }

    public int triling() {
        return daLiImaBarem(3);
    }

    public int max() {
        int sum = 0;
        int min = numberOfSides + 1;
        for (int i = 0; i < numberOfDiceces; i++) {
            sum += hand[i];
            if (hand[i] < min) {
                min = hand[i];
            }
        }
        return sum - min;
    }

    public int min() {
        int copy[] = Arrays.copyOf(hand, hand.length);
        Arrays.sort(copy);
        int sum = 0;
        for (int i = 0; i < numberOfDiceces - 1; i++) {
            sum += copy[i];
        }
        return sum;
    }

    private int daLiImaBarem(int x) {
        for (int i = numberOfSides - 1; i >= 0; i--) {
            if (rez[i] >= x) {
                return (i + 1) * x;
            }
        }
        return -1;
    }

    private void izbrojKockice() {
        rez = new int[numberOfSides];
        for (int i = 0; i < numberOfDiceces; i++) {
            rez[hand[i]-1]++;
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (int i = 0; i < numberOfDiceces - 1; i++) {
            stringBuilder.append(hand[i])
                    .append(" ");
        }
        stringBuilder.append(hand[numberOfDiceces - 1]).append("]");

        stringBuilder.append(" stats: [");
        for (int i = 0; i < numberOfSides-1; i++) {
            stringBuilder.append(i+1).append(":").append(rez[i]).append(", ");
        }
        stringBuilder.append(numberOfSides).append(":").append(rez[numberOfSides-1]).append("]");
        stringBuilder.append("{");
        int yumb = yumb();
        if (yumb != -1) {
            stringBuilder.append("yumb:").append(yumb).append(", ");
        }
        int poker = poker();
        if (poker != -1) {
            stringBuilder.append("poker:").append(poker).append(", ");
        }
        int triling = triling();
        if (triling != -1) {
            stringBuilder.append("triling:").append(triling).append(", ");
        }
        stringBuilder.append("max:").append(max()).append(", ");
        stringBuilder.append("min:").append(min()).append(", ");
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
