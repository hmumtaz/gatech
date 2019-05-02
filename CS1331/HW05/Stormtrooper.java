import java.util.Random;
/**
*This is a Stormtrooper class it extends Soldier
* @author Hussain Mumtaz
* @version 1.0
*/
public class Stormtrooper extends Soldier {
    /**
    * Overrides the default attack method of Soldier
    * @param target takes in a target from the Soldier class to attack
    */
    private int x = 0;
    @Override
    public double attack(Soldier target) {
        Random probability = new Random();
        double atkProb = probability.nextInt(101);
        double atk = 0;
        if (atkProb < 40) {
            x = 0;
        }
        if (atkProb > 40) {
            if (x < 1) {
                target.hurt(this.getAttack());
                x = x + 1;
                atk = this.getAttack();
            } else if (x >= 1) {
                target.hurt(this.getAttack() * 1.25);
                atk = (this.getAttack() * 1.25);
            }
        }
        return atk;
    }
    /**
    * creates a Stormtrooper object
    * @param health takes in health
    * @param attack takes in attack
    * @param defense takes in defense
    * @param identifier takes in a 2-letter and 2-number identifier
    */
    Stormtrooper(double health, double attack, double defense,
            String identifier) {
        super(health, attack, defense, ("Stormtrooper " + identifier));
    }
}