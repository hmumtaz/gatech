/**
* Creates a Jedi object it extends Soldier and implements ForceSensitive
* @author Hussain Mumtaz
* @version 1.0
*/
public class Jedi extends Soldier implements ForceSensitive {

    private double power = (.25 * this.getDefense());
    private int x = 0;
    /**
    * implementation of useTheForce method from ForceSensitive class
    */
    public void useTheForce() {
        this.heal(.25 * power);
        this.changeDefense(power);
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
            this.changeDefense(-power);
            x = 0;
        }
        return this.getAttack();
    }
    /**
    * creates a Jedi object
    * @param health takes in health
    * @param attack takes in attack
    * @param defense takes in defense
    * @param identifier takes in a 2-letter and 2-number identifier
    */
    Jedi(double health, double attack, double defense,
            String identifier) {
        super(health, attack, defense, ("Jedi " + identifier));
    }
}