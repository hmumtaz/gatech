/**
*This class represents a UndergradStudent object
* @author Hussain Mumtaz
* @version 1.00
*/
public class UndergradStudent extends Student {
    protected int hope = 100;
    protected int pizza = 0;
    /**
    *gets and returns student's hope
    *@return hope
    */
    public int getHope() {
        return hope;
    }
    /**
    *gets and returns student's pizza consumed
    *@return pizza
    */
    public int getPizza() {
        return pizza;
    }
    /**
    *subtracts hope from a student's hope
    *@param hopeLoss amount of hope to be lost
    */
    public void loseHope(int hopeLoss) {
        if (hopeLoss < 0) {
            this.hope = hope;
        } else {
            this.hope = hope - hopeLoss;
        }

        if (this.hope < 0) {
            this.hope = 0;
        }

        if (this.hope > 100) {
            this.hope = 100;
        }
    }
    /**
    *adds pizzas eaten to a student's pizzas
    *@param pizzas amount of pizzas to be added
    */
    public void eatPizza(int pizzas) {
        if (pizzas < 0) {
            this.pizza = pizza;
        } else {
            this.pizza = pizza + pizzas;
        }

        if (pizza < 0) {
            this.pizza = 0;
        }
    }
    /**
    *creates a UndergradStudent object
    * @param first first name of UndergradStudent
    * @param last last name of UndergradStudent
    * @param user username of UndergradStudent
    */
    UndergradStudent(String first, String last, String user) {
        super(first, last, user);
    }
}
