/**
* Creates a Sith object it extends Soldier and implements ForceSensitive
* @author Hussain Mumtaz
* @version 1.0
*/
public class Sith extends Soldier implements ForceSensitive {

    private double power = ((1 / 6) * (this.getAttack()
        + this.getDefense()));
    private int x = 0;
    /**
    * implementation of useTheForce method from ForceSensitive class
    */
    public void useTheForce() {
        this.hurt(.1 * power);
        this.changeAttack(power);
        x = x + 1;
    }
    /**
    * Overrides the default attack method of Soldier
    * @param target takes in a target from the Soldier class to attack
    */
    @Override
    public double attack(Soldier target) {
        target.hurt(this.getAttack());
        if (x > 0) {
            this.changeAttack(-power);
            x = 0;
        }
        return this.getAttack();
    }
    /**
    * creates a Sith object
    * @param health takes in health
    * @param attack takes in attack
    * @param defense takes in defense
    * @param identifier takes in a 2-letter and 2-number identifier
    */
    Sith(double health, double attack, double defense,
            String identifier) {
        super(health, attack, defense, ("Sith " + identifier));
    }
}