import java.util.Random;
/**
*This is a Joke class it extends Stormtrooper
* @author Hussain Mumtaz
* @version 1.0
*/
public class Joke extends Stormtrooper {
    /**
    * Overrides the default attack method of Stormtrooper
    * @param target takes in a target from the Soldier class to attack
    */
    private int x = 0;
    @Override
    public double attack(Soldier target) {
        Random probability = new Random();
        double atkProb = probability.nextInt(101);
        if (atkProb < 65) {
            x = 0;
        }
        if (atkProb >= 65) {
            if (x < 1) {
                target.hurt(this.getAttack());
                x = x + 1;
            } else if (x >= 1) {
                target.hurt(this.getAttack() * 1.3);
            }
        }
        return this.getAttack();
    }
    /**
    * creates a Joke object
    * @param health takes in health
    * @param attack takes in attack
    * @param defense takes in defense
    * @param identifier takes in a 2-letter and 2-number identifier
    */
    Joke(double health, double attack, double defense,
            String identifier) {
        super(health, attack, defense, identifier);
    }
}