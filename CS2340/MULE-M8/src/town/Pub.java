package town;

import player.Player;
import timer.Turn;
import java.util.Random;

/**
* @author Hussain Mumtaz
* @version 1.0
*/
public class Pub implements Building {

    @Override
    public boolean isOutfitter() {
        return false;
    }

    @Override
    public void action(Turn turn) {
        Random random = new Random();
        Player player = turn.getPlayer();
        int time = (turn.getTimer().getRemainingTime())/1000;
        int gamblingFactor = random.nextInt(10);
        player.addMoney(time * gamblingFactor);
        player.updateLabel();
        turn.getTimer().setRemainingTime(100);
    }
}