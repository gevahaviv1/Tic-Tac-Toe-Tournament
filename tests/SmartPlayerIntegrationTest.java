import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Integration tests benchmarking <code>SmartPlayer</code> against other automatic players.
 * Verifies that <code>SmartPlayer</code> wins at least 80% of 10,000 rounds.
 *
 * @author Geva Haviv
 */
public class SmartPlayerIntegrationTest {

	/* Total number of rounds per tournament. */
	private static final int TOTAL_ROUNDS = 10000;

	/* Board size matching the default tournament parameters. */
	private static final int BOARD_SIZE = 4;

	/* Win streak matching the default tournament parameters. */
	private static final int WIN_STREAK = 3;

	/* Minimum win percentage required (80%). */
	private static final double MIN_WIN_RATE = 0.8;

	/* Minimum number of wins required out of TOTAL_ROUNDS. */
	private static final int MIN_WINS = (int) (TOTAL_ROUNDS * MIN_WIN_RATE);

	/* Renderer used during benchmarks — no output. */
	private static final Renderer RENDERER = new VoidRenderer();

	/* Runs a tournament and returns the win count for player1 (the SmartPlayer). */
	private int[] runTournament(Player smartPlayer, Player opponent) {
		int smartWins = 0;
		int opponentWins = 0;
		int ties = 0;
		for (int round = 0; round < TOTAL_ROUNDS; round++) {
			Game game;
			if (round % 2 == 0) {
				game = new Game(smartPlayer, opponent, BOARD_SIZE, WIN_STREAK, RENDERER);
			} else {
				game = new Game(opponent, smartPlayer, BOARD_SIZE, WIN_STREAK, RENDERER);
			}
			Mark result = game.run();
			if (result == Mark.BLANK) {
				ties++;
			} else if ((round % 2 == 0 && result == Mark.X)
					|| (round % 2 == 1 && result == Mark.O)) {
				smartWins++;
			} else {
				opponentWins++;
			}
		}
		return new int[]{smartWins, opponentWins, ties};
	}

	@Test
	public void testSmartVsWhatever() {
		Player smart = new SmartPlayer();
		Player whatever = new WhateverPlayer();
		int[] results = runTournament(smart, whatever);
		int smartWins = results[0];
		int whateverWins = results[1];
		int ties = results[2];
		System.out.println("=== Smart vs Whatever ===");
		System.out.println("Smart wins:    " + smartWins);
		System.out.println("Whatever wins: " + whateverWins);
		System.out.println("Ties:          " + ties);
		System.out.println("Win rate:      " + (smartWins * 100.0 / TOTAL_ROUNDS) + "%");
		assertTrue("SmartPlayer must win at least " + MIN_WINS
				+ " rounds vs WhateverPlayer, but won " + smartWins,
				smartWins >= MIN_WINS);
	}

	@Test
	public void testSmartVsNaive() {
		Player smart = new SmartPlayer();
		Player naive = new NaivePlayer();
		int[] results = runTournament(smart, naive);
		int smartWins = results[0];
		int naiveWins = results[1];
		int ties = results[2];
		System.out.println("=== Smart vs Naive ===");
		System.out.println("Smart wins: " + smartWins);
		System.out.println("Naive wins: " + naiveWins);
		System.out.println("Ties:       " + ties);
		System.out.println("Win rate:   " + (smartWins * 100.0 / TOTAL_ROUNDS) + "%");
		assertTrue("SmartPlayer must win at least " + MIN_WINS
				+ " rounds vs NaivePlayer, but won " + smartWins,
				smartWins >= MIN_WINS);
	}
}
