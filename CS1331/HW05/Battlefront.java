import java.util.Scanner;
/**
* This is a Battlefront object, it simulates a battle
* @author Hussain Mumtaz
* @version 1.0
*/
public class Battlefront {
    /**
    * Creates a method to run simulation.java
    * @param args args
    */
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        int rebelSoldiers;
        int jedi;
        int stormtroopers;
        int sith;

        System.out.println("+-------------------------------------------+");
        System.out.println("| Welcome to the Battlefront1331 Simulator! |");
        System.out.println("+-------------------------------------------+");

        System.out.println("How many Rebel Soldiers would you like to"
            + " include?");
        rebelSoldiers = keyboard.nextInt();

        System.out.println("How many Jedi would you like to include?");
        jedi = keyboard.nextInt();

        System.out.println("How many Stormtroopers would you like to"
            + " include?");
        stormtroopers = keyboard.nextInt();

        System.out.println("How many Sith would you like to include?");
        sith = keyboard.nextInt();

        Joe joe = new Joe(50, 60, 50, "ds23");
        Joke joke = new Joke(60, 80, 80, "ss23");

        Simulation simulate = new Simulation(rebelSoldiers, jedi,
            stormtroopers, sith, joe, joke);

        keyboard.nextLine();
        System.out.println("Press enter to begin the simulation");
        keyboard.nextLine();
        simulate.simulateSkirmish(false);

        int x = 0;
        while ((simulate.getNumRebellionRemaining() > 0)
            && (simulate.getNumEmpireRemaining() > 0)) {

            int numbers = simulate.getNumRebelsRemaining()
                + simulate.getNumJediRemaining()
                + simulate.getNumTroopersRemaining()
                + simulate.getNumSithRemaining();
            System.out.println("Press enter to continue the simulation");
            keyboard.nextLine();
            simulate.simulateSkirmish(false);

            int numbersAfter = simulate.getNumRebelsRemaining()
                + simulate.getNumJediRemaining()
                + simulate.getNumTroopersRemaining()
                + simulate.getNumSithRemaining();

            if (numbers == numbersAfter) {
                x = x + 1;
                if (x == 3) {
                    break;
                }
            } else {
                x = 0;
            }
        }

        System.out.println("Simulation Complete!");
        if (simulate.getNumRebellionRemaining()
            > simulate.getNumEmpireRemaining()) {
            System.out.println("The Rebels won!");
        } else {
            System.out.println("The Empire won!");
        }
        System.out.println(simulate.getNumRebelsRemaining()
            + " of " + rebelSoldiers + " Rebel Soldiers remain!");
        System.out.println(simulate.getNumJediRemaining() + " of " + jedi
            + " Jedi remain!");
        System.out.println(simulate.getNumTroopersRemaining() + " of "
            + stormtroopers + " Stormtroopers remain!");
        System.out.println(simulate.getNumSithRemaining() + " of " + sith
            + " Sith remain!");
    }
}