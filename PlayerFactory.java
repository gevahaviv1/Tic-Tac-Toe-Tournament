/**
 * Factory for creating <code>Player</code> instances by type name.
 *
 * @author Geva Haviv
 */
public class PlayerFactory {

	/**
	 * Constructs a new PlayerFactory.
	 */
	public PlayerFactory() {
	}

	/**
	 * Creates and returns a <code>Player</code> matching the given type string.
	 *
	 * @param type the player type: "human", "whatever", "naive", or "smart".
	 * @return a new <code>Player</code> of the requested type, or <code>null</code>
	 * if the type is unrecognized.
	 */
	public Player buildPlayer(String type) {
		switch (type) {
		case "human":
			return new HumanPlayer();
		case "whatever":
			return new WhateverPlayer();
		case "naive":
			return new NaivePlayer();
		case "smart":
			return new SmartPlayer();
		default:
			return null;
		}
	}
}
