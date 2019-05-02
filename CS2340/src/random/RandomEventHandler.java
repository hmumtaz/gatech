package random;

import timer.Turn;
import player.Player;

import java.util.Random;

/**
 * @author Janet Liang
 * @version 1.0
 */

public class RandomEventHandler {
	private static final int MAX = 7;
	private static final int[] factor = {25, 25, 25, 50, 50, 50, 50, 75, 75, 75, 75, 100};

	private Turn turn;
	private RandomEvent event;
	private Random rand;
	private int m;

	/**
	 * Constructor for a RandomEventHandler
	 * Finds the round factor and picks event
	 *
	 * @param turn The current Turn instance
	 */
	public RandomEventHandler(Turn turn) {
		this.turn = turn;
		rand = new Random();
		m = factor[turn.getRound() - 1];
		event = pickEvent();
	}

	/**
	 * Method to pick the event.
	 *
	 * @return chosen RandomEvent randomly picked
	 */
	private RandomEvent pickEvent() {
		RandomEvent chosen;
		int num = rand.nextInt(MAX);
		if (num == 0) {
			chosen = RandomEvent.PACKAGE;
		} else if (num == 1) {
			chosen = RandomEvent.HOSPITALITY;
		} else if (num == 2) {
			chosen = RandomEvent.COMPUTER;
		} else if (num == 3) {
			chosen = RandomEvent.MOOSERAT;
		} else if (num == 4) {
			chosen = RandomEvent.CATBUG;
		} else if (num == 5) {
			chosen = RandomEvent.UGASTUDENTS;
		} else {
			chosen = RandomEvent.SPACEGYPSY;
		}
		return chosen;
	}

	/**
	 * Method to check if the event can happen to the player.
	 *
	 * @return boolean true if can happen, false if can't
	 */
	public boolean check() {
		return rand.nextInt(100) < 27 && (event.getGood() || turn.getIndex() > 0);
	}

	/**
	 * Method to perform the event.
	 *
	 * @return int 0 if done, 1 if check failed, 2 if player doesn't have money
	 */
	public int performEvent() {
		int status = 0;
		Player p = turn.getPlayer();
		if (check()) {
			int x = event.ordinal();
			int amount = 0;
			if (x == 0) {
				p.setFood(p.getFood() + 3);
				p.setEnergy(p.getEnergy() + 2);
			} else if (x == 1) {
				p.setOre(p.getOre() + 2);
			} else if (x == 2) {
				amount = 8 * m;
				p.setMoney(p.getMoney() + amount);
			} else if (x == 3) {
				amount = 2 * m;
				p.setMoney(p.getMoney() + amount);
			} else if (x == 4) {
				amount = 4 * m;
				if (p.getMoney() - amount >= 0) {
					p.setMoney(p.getMoney() - amount);
				} else {
					status = 2;
				}
			} else if (x == 5) {
				p.setFood(p.getFood() / 2);
			} else {
				amount = 6 * m;
				if (p.getMoney() - amount >= 0) {
					p.setMoney(p.getMoney() - amount);
				} else {
					status = 2;
				}
			}
		} else {
			status = 1;
		}
		return status;
	}

	/**
	 * Method to get the description of the event.
	 *
	 * @return description of event
	 */
	public String getDescription() {
		int x = event.ordinal();
		int amount = 0;
		String str = "";
		if (x == 0) {
			str = event.getDescription();
		} else if (x == 1) {
			str = event.getDescription();
		} else if (x == 2) {
			amount = 8 * m;
			str = event.getDescription() + amount + ".";
		} else if (x == 3) {
			amount = 2 * m;
			str = event.getDescription() + amount + ".";
		} else if (x == 4) {
			str = event.getDescription();
		} else if (x == 5) {
			str = event.getDescription();
		} else {
			amount = 4 * m;
			str = event.getDescription() + amount + " to get it fixed.";
		}
		return str;
	}
}
