/**
 * Factory for creating <code>Renderer</code> instances by type name.
 *
 * @author Geva Haviv
 */
public class RendererFactory {

	/**
	 * Constructs a new RendererFactory.
	 */
	public RendererFactory() {
	}

	/**
	 * Creates and returns a <code>Renderer</code> matching the given type string.
	 *
	 * @param type the renderer type: "console" or "void".
	 * @param size the board size to pass to the renderer.
	 * @return a new <code>Renderer</code> of the requested type, or <code>null</code>
	 * if the type is unrecognized.
	 */
	public Renderer buildRenderer(String type, int size) {
		switch (type) {
		case "console":
			return new ConsoleRenderer(size);
		case "void":
			return new VoidRenderer();
		default:
			return null;
		}
	}
}
