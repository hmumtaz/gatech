import java.util.Random;
/**
*This is a rebel soldier class it extends Soldier
* @author Hussain Mumtaz
* @version 1.0
*/
public class RebelSoldier extends Soldier {
    /**
    * Overrides the default attack method of Soldier
    * @param target takes in a target from the Soldier class to attack
    */
    @Override
    public double attack(Soldier target) {
        Random probability = new Random();
        double atkProb = probability.nextInt(101);
        if (atkProb > 20) {
            target.hurt(this.getAttack());
        }
        return this.getAttack();
    }
    /**
    * creates a Rebel Soldier object
    * @param health takes in health
    * @param attack takes in attack
    * @param defense takes in defense
    * @param identifier takes in a 2-letter and 2-number identifier
    */
    RebelSoldier(double health, double attack, double defense,
            String identifier) {
        super(health, attack, defense, ("Rebel Soldier " + identifier));
    }
}