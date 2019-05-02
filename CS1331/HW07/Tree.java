/**
* Represents a Tree object.
*
* @author Hussain Mumtaz
* @version 1.0
*/
public class Tree extends Zone {
    /**
    * Constructs a Tree Object
    */
    public Tree() {
        this.add(new Gnome());
        for (int i = 0; i < 9; i = i + 1) {
            this.add(new Keeble());
        }
    }
}