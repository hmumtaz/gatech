package timer;

import player.Player;
import random.RandomEventHandler;

import java.util.Arrays;

/**
 * Represents a turn class which controls the logic of turns
 *
 * @author Hussain Mumtaz
 * @version 1.0
 */
public class Turn implements Runnable {
    Timer timer;
    private int time;
    Player[] players;
    int index;
    int round;


    /**
    * Turn constructor
    *
    * @param players array of players playing
    * @param round Current round number
    */
    public Turn(Player[] players, int round) {
        this.players = players;
        index = 0;
        this.round = round;
        Arrays.sort(players, (one, two) -> one.getScore() - two.getScore());
    }


    /**
    *Gets index of current player
    *@return returns index
    */
    public int getIndex() {
        return index;
    }

    /**
    *Sets index, used to switch turns
    *
    *@param x represents new index
    */
    public void setIndex(int x) {
        index = x;
    }

    /**
    * Gets current player
    * @return current player
    */
    public Player getPlayer() {
        return players[index];
    }

    /**
    * Goes to next player's turn
    */
    public void nextTurn() {
        System.out.println(round);
        players[index].dropMule();
        players[index].removeImage();
        index += 1;
        if (index >= players.length) {
            round += 1;
            index = 0;
            calculateChanges();
        }
        beginTurn(index);
    }

    /**
     * Calculates production and other changes that occur at the end of a round.
     */
    private void calculateChanges() {
        for (int i = 0; i < players.length; i += 1) {
            players[i].calculateProduction();
            players[i].updateLabel();
        }
        Arrays.sort(players, (one, two) -> one.getScore() - two.getScore());
    }

    /**
    * gets current round
    * @return round
    */
    public int getRound() {
        return round;
    }

    /**
    * sets current round
    * @param x round to set to
    */
    public void setRound(int x) {
        round = x;
    }

    /**
    *Returns current timer
    *@return timer
    */
    public Timer getTimer() {
        return timer;
    }

    /**
    * Gets players
    * @return player index
    */
    public Player[] getPlayers() {
        return players;
    }

    /**
    * Runs a complete turn
    */
    public void run() {
        Player player = players[index];
        player.updateLabel();
        nextTurn();
    }

    /**
    * Starts a turn
    *
    * @param index player whose turn it is
    */
    public void beginTurn(int index) {
        if (game.Manager.getGame().inTown()) {
            game.Manager.getGame().leaveTown();
        }
        Player player = players[index];
        players[index].resetPosition();
        if (round < 13) {
            RandomEventHandler event = new RandomEventHandler(this);
            int result = event.performEvent();
            player.updateLabel();
            if (result == 1) {
                player.getLabel().setText(player.getLabel().getText() + "------");
            } else {
                player.getLabel().setText(player.getLabel().getText() + "------"
                    + event.getDescription());
                if (result > 1) {
                    player.setMoney(0);
                }
            }
        } else {
            player.getLabel().setText(player.getLabel().getText() + "------");
        }
        time = calculateTurnTime(player);
        timer = new Timer(time, this);
        game.Manager.getGame().enterTown();
        game.Manager.getGame().leaveTown();
    }

    /**
    * calculates turn time
    *
    * @param player whose turn time is being calculated
    * @return time of turn
    */
    private int calculateTurnTime(Player player) {
        int requiredFood = (round - 1) / 4 + 3;
        int time = 0;
        if (player.getFood() < 1) {
            time = 5 * ITimer.SECOND;
        } else if (player.getFood() < requiredFood) {
            time = 30 * ITimer.SECOND;
            player.setFood(0);
        } else {
            time = 50 * ITimer.SECOND;
            player.addFood(0 - requiredFood);
        }
        return time;
    }
}
