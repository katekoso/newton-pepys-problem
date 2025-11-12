import java.util.Random;

/**
 * The NewtonPepys class solves the Newton Pepys problem by simulating throwing
 * the dice to
 * find out whether it is more probable to get at least one "6" out of 6 throws,
 * at least
 * two "6"s out of 12 throws or at least three "6"s out of 18 throws. The class
 * also
 * provides a general solution by simulating 6n throws and computating a
 * probability
 * of getting at least n "6"s.
 * 
 * @author Ekaterina Kosourikhina
 * @version 24.02.25
 */
public class NewtonPepys {
    public static final int NUM_RUNS = 10000;
    public int[] dice6;
    public int[] dice12;
    public int[] dice18;
    public Random ran;

    /**
     * Constructor for objects of class NewtonPepys. Initialises dice6, dice12 and
     * dice18
     * to empty arrays of sizes 6, 12 and 18 and ran to a new Random object.
     */
    public NewtonPepys() {
        dice6 = new int[6];
        dice12 = new int[12];
        dice18 = new int[18];
        ran = new Random();
    }

    public static void main(String[] args) {
        NewtonPepys np = new NewtonPepys();
        np.graph();
        System.out.println(np.nSixes(10) + "%");
    }

    /**
     * Populates dice6, dice12 and dice18 with random numbers between 1 and 6
     * (inclusive).
     */
    public void populate() {
        for (int i = 0; i < dice6.length; i++) {
            dice6[i] = ran.nextInt(6) + 1;
        }

        for (int i = 0; i < dice12.length; i++) {
            dice12[i] = ran.nextInt(6) + 1;
        }

        for (int i = 0; i < dice18.length; i++) {
            dice18[i] = ran.nextInt(6) + 1;
        }
    }

    /**
     * Finds out if dice6 contains at least one "6", dice12 contains at least two
     * "6"s
     * and dice18 contains at least three "6"s.
     * 
     * @return An array of boolean values showing if the needed amount of "6"s was
     *         found
     *         in dice6, dice12 and dice18 or not.
     */
    public boolean[] findSixes() {
        boolean[] foundSixes = { false, false, false };

        for (int item : dice6) {
            if (item == 6) {
                foundSixes[0] = true;
            }
        }

        int sixes12 = 0;
        for (int item : dice12) {
            if (item == 6) {
                sixes12++;
            }
        }

        if (sixes12 >= 2) {
            foundSixes[1] = true;
        }

        int sixes18 = 0;
        for (int item : dice18) {
            if (item == 6) {
                sixes18++;
            }
        }

        if (sixes18 >= 3) {
            foundSixes[2] = true;
        }

        return foundSixes;
    }

    /**
     * Simulates throwing dice6, dice12 and dice18 a given amount of times and
     * counts
     * how many times at least one "6" was found in dice 6, at least two "6"s were
     * found in
     * dice12 and at least three "6"s were found in dice18.
     * 
     * @param n The amount of times each set of dice is thrown
     * @return An array of the total numbers of times the needed amount of "6"s was
     *         found in
     *         dice6, dice12 and dice18.
     */
    public int[] runSimulation(int n) {
        int sixes6 = 0;
        int sixes12 = 0;
        int sixes18 = 0;
        boolean[] foundSixes = new boolean[3];

        for (int i = 0; i < n; i++) {
            populate();
            foundSixes = findSixes();

            if (foundSixes[0] == true) {
                sixes6++;
            }

            if (foundSixes[1] == true) {
                sixes12++;
            }

            if (foundSixes[2] == true) {
                sixes18++;
            }
        }

        int[] numberOfFindings = { sixes6, sixes12, sixes18 };
        return numberOfFindings;
    }

    /**
     * Runs the simulation, calculates the percentages of the needed amount of "6"s
     * being found, prints a graph with the percentages in the terminal.
     */
    public void graph() {
        int[] numberOfFindings = runSimulation(NUM_RUNS);
        double numRuns = NUM_RUNS;
        double percentage6 = numberOfFindings[0] / numRuns * 100;
        double percentage12 = numberOfFindings[1] / numRuns * 100;
        double percentage18 = numberOfFindings[2] / numRuns * 100;

        for (int i = 0; i < (int) percentage6; i++) {
            System.out.print("*");
        }
        System.out.println("       " + String.format("%.1f", percentage6) + "%");

        for (int i = 0; i < (int) percentage12; i++) {
            System.out.print("*");
        }
        System.out.println("       " + String.format("%.1f", percentage12) + "%");

        for (int i = 0; i < (int) percentage18; i++) {
            System.out.print("*");
        }
        System.out.println("       " + String.format("%.1f", percentage18) + "%");
    }

    /**
     * Solves a general version of a problem, calculates the probability of n or
     * more "6"s
     * if 6n dice are thrown.
     * 
     * @param n The minimal number of "6"s that should be found
     * @return The probability of the needed amount of "6"s being found
     */
    public double nSixes(int n) {
        int[] diceN = new int[6 * n];
        int enoughSixes = 0;

        for (int i = 0; i < NUM_RUNS; i++) {
            for (int j = 0; j < diceN.length; j++) {
                diceN[j] = ran.nextInt(6) + 1;
            }

            boolean isNSixes = false;
            int sixesN = 0;
            for (int item : diceN) {
                if (item == 6) {
                    sixesN++;
                }
            }

            if (sixesN >= n) {
                isNSixes = true;
            }

            if (isNSixes == true) {
                enoughSixes++;
            }
        }

        double numRuns = NUM_RUNS;
        double percentageN = enoughSixes / numRuns * 100;
        return percentageN;
    }
}