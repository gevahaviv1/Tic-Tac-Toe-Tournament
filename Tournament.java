/**
 * Runs a tournament of multiple Tic-Tac-Toe games between two players.
 * Players alternate between <code>X</code> and <code>O</code> each round.
 *
 * @author Geva Haviv
 */
public class Tournament {

	/* Header printed before the results summary. */
	private static final String RESULTS_HEADER = "######### Results #########";

	/* Format for each player's win count line. */
	private static final String PLAYER_RESULT_FORMAT = "Player %d, %s won: %d rounds";

	/* Format for the ties line. */
	private static final String TIES_FORMAT = "Ties: %d";

	/* The number of games to play in the tournament. */
	private final int rounds;

	/* The renderer used to display the board during each game. */
	private final Renderer renderer;

	/* The first player. */
	private final Player player1;

	/* The second player. */
	private final Player player2;

	/**
	 * Constructs a new tournament.
	 *
	 * @param rounds   the number of games to play.
	 * @param renderer the <code>Renderer</code> used to display the board.
	 * @param player1  the first player.
	 * @param player2  the second player.
	 */
	public Tournament(int rounds, Renderer renderer, Player player1, Player player2) {
		this.rounds = rounds;
		this.renderer = renderer;
		this.player1 = player1;
		this.player2 = player2;
	}

	/**
	 * Plays the full tournament and prints the results to standard output.
	 *
	 * @param size        the board size for each game.
	 * @param winStreak   the win streak required to win a game.
	 * @param playerName1 the display name of player 1.
	 * @param playerName2 the display name of player 2.
	 */
	public void playTournament(int size, int winStreak, String playerName1, String playerName2) {
		int wins1 = 0;
		int wins2 = 0;
		int ties = 0;
		for (int round = 0; round < rounds; round++) {
			Game game;
			if (round % 2 == 0)
				game = new Game(player1, player2, size, winStreak, renderer);
			else
				game = new Game(player2, player1, size, winStreak, renderer);
			Mark result = game.run();
			if (result == Mark.BLANK) {
				ties++;
			} else if ((round % 2 == 0 && result == Mark.X)
					|| (round % 2 == 1 && result == Mark.O)) {
				wins1++;
			} else {
				wins2++;
			}
		}
		System.out.println(RESULTS_HEADER);
		System.out.println(String.format(PLAYER_RESULT_FORMAT, 1, playerName1, wins1));
		System.out.println(String.format(PLAYER_RESULT_FORMAT, 2, playerName2, wins2));
		System.out.println(String.format(TIES_FORMAT, ties));
	}

	/* Creates and returns a Renderer instance matching the given type name and board size. */
	private static Renderer buildRenderer(String name, int size) {
		if (name.equals("console"))
			return new ConsoleRenderer(size);
		return new VoidRenderer();
	}

	/**
	 * Entry point of the program. Expects arguments: rounds, size, winStreak,
	 * rendererType, playerType1, playerType2.
	 *
	 * @param args command-line arguments.
	 */
	public static void main(String[] args) {
		int rounds = Integer.parseInt(args[0]);
		int size = Integer.parseInt(args[1]);
		int winStreak = Integer.parseInt(args[2]);
		String rendererType = args[3];
		String playerName1 = args[4];
		String playerName2 = args[5];
		Renderer renderer = buildRenderer(rendererType, size);
		PlayerFactory playerFactory = new PlayerFactory();
		Player player1 = playerFactory.buildPlayer(playerName1);
		Player player2 = playerFactory.buildPlayer(playerName2);
		Tournament tournament = new Tournament(rounds, renderer, player1, player2);
		tournament.playTournament(size, winStreak, playerName1, playerName2);
	}
}
