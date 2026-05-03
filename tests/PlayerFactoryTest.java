import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for the <code>PlayerFactory</code> class.
 *
 * @author Geva Haviv
 */
public class PlayerFactoryTest {

	/* Factory instance used across all tests. */
	private final PlayerFactory factory = new PlayerFactory();

	@Test
	public void testBuildHumanPlayer() {
		Player player = factory.buildPlayer("human");
		assertNotNull(player);
		assertTrue(player instanceof HumanPlayer);
	}

	@Test
	public void testBuildWhateverPlayer() {
		Player player = factory.buildPlayer("whatever");
		assertNotNull(player);
		assertTrue(player instanceof WhateverPlayer);
	}

	@Test
	public void testBuildNaivePlayer() {
		Player player = factory.buildPlayer("naive");
		assertNotNull(player);
		assertTrue(player instanceof NaivePlayer);
	}

	@Test
	public void testBuildSmartPlayer() {
		Player player = factory.buildPlayer("smart");
		assertNotNull(player);
		assertTrue(player instanceof SmartPlayer);
	}

	@Test
	public void testBuildUnrecognizedReturnsNull() {
		assertNull(factory.buildPlayer("unknown"));
	}

	@Test
	public void testBuildEmptyStringReturnsNull() {
		assertNull(factory.buildPlayer(""));
	}

	@Test
	public void testBuildCaseSensitive() {
		assertNull(factory.buildPlayer("Human"));
		assertNull(factory.buildPlayer("SMART"));
		assertNull(factory.buildPlayer("Naive"));
	}
}
