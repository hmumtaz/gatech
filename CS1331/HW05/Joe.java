import java.util.Random;
/**
*This is a Joe class it extends RebelSoldier
* @author Hussain Mumtaz
* @version 1.0
*/
public class Joe extends RebelSoldier {
    /**
    * Overrides the default attack method of RebelSoldier
    * @param target takes in a target from the Soldier class to attack
    */
    private int condition = 5;
    @Override
    public double attack(Soldier target) {
        Random probability = new Random();
        double atkProb = probability.nextInt(101);
        if (atkProb > condition) {
            target.hurt(this.getAttack());
        }
        if ((atkProb < condition) && (condition < 40)) {
            condition = condition + 5;
        }
        return this.getAttack();
    }
    /**
    * creates a Joe object
    * @param health takes in health
    * @param attack takes in attack
    * @param defense takes in defense
    * @param identifier takes in a 2-letter and 2-number identifier
    */
    Joe(double health, double attack, double defense,
            String identifier) {
        super(health, attack, defense, identifier);
    }
}