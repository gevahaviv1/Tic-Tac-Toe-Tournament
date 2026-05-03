import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for the <code>RendererFactory</code> class.
 *
 * @author Geva Haviv
 */
public class RendererFactoryTest {

	/* Factory instance used across all tests. */
	private final RendererFactory factory = new RendererFactory();

	/* Board size passed to the factory. */
	private static final int BOARD_SIZE = 4;

	@Test
	public void testBuildConsoleRenderer() {
		Renderer renderer = factory.buildRenderer("console", BOARD_SIZE);
		assertNotNull(renderer);
		assertTrue(renderer instanceof ConsoleRenderer);
	}

	@Test
	public void testBuildVoidRenderer() {
		Renderer renderer = factory.buildRenderer("void", BOARD_SIZE);
		assertNotNull(renderer);
		assertTrue(renderer instanceof VoidRenderer);
	}

	@Test
	public void testBuildUnrecognizedReturnsNull() {
		assertNull(factory.buildRenderer("unknown", BOARD_SIZE));
	}

	@Test
	public void testBuildEmptyStringReturnsNull() {
		assertNull(factory.buildRenderer("", BOARD_SIZE));
	}

	@Test
	public void testBuildCaseSensitive() {
		assertNull(factory.buildRenderer("Console", BOARD_SIZE));
		assertNull(factory.buildRenderer("VOID", BOARD_SIZE));
	}
}
