package random;

/**
 * @author Janet Liang
 * @version 1.0
 */

public enum RandomEvent {
	PACKAGE("You just received a package from a GT Alumni containing 3 food and 2 energy units.", true),
	HOSPITALITY("A wandering Tech Student repaid your hospility by leaving 2 bars of ore.", true),
	COMPUTER("The museum bought your antique personal computer for $", true),
	MOOSERAT("You found a dead moose rat and sold the hide for $", true),
	CATBUG("Flying cat-bugs ate the roof off your house. Repairs cost $", false),
	UGASTUDENTS("Mischievous UGA students broke into your storage shed and stole half of your food.", false),
	SPACEGYPSY("Your space gypsy inlaws made a mess of the town. It cost you $", false);

	private String description;
	private boolean good;

	/**
	 * Constructer for a RandomEvent enum.
	 *
	 * @param descrip, description of event
	 * @param positive, boolean if the event has a negative or positive effect
	 */
	private RandomEvent(String descrip, boolean positive) {
		description = descrip;
		good = positive;
	}

	/**
	 * Get description.
	 *
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Get boolean good for event.
	 *
	 * @return good
	 */
	public boolean getGood() {
		return good;
	}
}
