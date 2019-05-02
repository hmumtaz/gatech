package town;

import player.Player;
import player.Mule;
import timer.Turn;

public class MuleBuilding implements Building {

    public int quantity = 25;
    public int price = 100;

    @Override
    public boolean isOutfitter() {
        return false;
    }

    @Override
    public void action(Turn turn) {
        Player player = turn.getPlayer();
        if (player.getMule() == null && quantity > 0 && player.getMoney() >= price) {
            player.addMule(new Mule());
            quantity -= 1;
            player.addMoney(0 - price);
        }
    }
}
