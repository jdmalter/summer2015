package collection;

/**
 * Secondary hierarchy within collection package. Defines behaviors needed for a
 * graph.
 * 
 * @author Jacob Malter
 *
 * @param <V>
 *            The type of the data within vertices stored in this collection.
 * @param <E>
 *            The type of the data within edges stored in this collection.
 */
public interface Graph<V, E> {

	/**
	 * Defines behaviors for an edge.
	 * 
	 * @author Jacob Malter
	 *
	 * @param <V>
	 *            The type of the data within vertices stored in this
	 *            collection.
	 * @param <E>
	 *            The type of the data within edges stored in this collection.
	 */
	public interface Edge<V, E> {

		/**
		 * Returns value stored within this edge.
		 * 
		 * @return stored value
		 */
		E edgeValue();

		/**
		 * Finds if edge leads to itself.
		 * 
		 * @return true if edge's vertices are all the same, false otherwise
		 */
		boolean isLoop();

		/**
		 * Determines if another edge is adjacent to invoking edge.
		 * 
		 * @param other
		 *            possibly parallel edge
		 * @return true if edge shares all vertices, false otherwise
		 */
		boolean isParallel(Edge<V, E> other);

		/**
		 * Returns a collection of every vertex on this edge.
		 * 
		 * @return every vertex on this edge
		 */
		Collection<Vertex<E, V>> points();

	}

	/**
	 * Defines behaviors for a vertex.
	 * 
	 * @author Jacob Malter
	 *
	 * @param <V>
	 *            The type of the data within vertices stored in this
	 *            collection.
	 * @param <E>
	 *            The type of the data within edges stored in this collection.
	 */
	public interface Vertex<V, E> {

		/**
		 * Determines if another vertex is adjacent to invoking vertex.
		 * 
		 * @param other
		 *            possibly adjacent vertex
		 * @return true if vertex shares an edge, false otherwise
		 */
		boolean areAdjacent(Vertex<V, E> other);

		/**
		 * Returns the number of connected edges.
		 * 
		 * @return number of connected edges.
		 */
		int degree();

		/**
		 * Returns a collection of every edge on this vertex.
		 * 
		 * @return every edge on this vertex
		 */
		Collection<Edge<E, V>> incident();

		/**
		 * Returns a collection of vertices on incident edges to this vertex.
		 * 
		 * @return every edge on every vertex incident to this vertex.
		 */
		Collection<Vertex<E, V>> opposite();

		/**
		 * Creates a list of every vertex between two vertices including the
		 * invoking and parameter instances.
		 * 
		 * @param other
		 *            final vertex
		 * @return path from invoking to final vertex with invoking at 0 and
		 *         final at size()
		 */
		List<Vertex<E, V>> path(Vertex<V, E> other);

		/**
		 * Returns value stored within this vertex.
		 * 
		 * @return stored value
		 */
		V vertexValue();

	}

	/**
	 * Adds an edge containing e.
	 * 
	 * @param e
	 *            element being added into edge and therefore graph
	 * @return true if graph changed by operation, false otherwise
	 */
	boolean addEdge(E e);

	/**
	 * Adds an edge containing e with all vertices in parameter collection.
	 * 
	 * @param vertices
	 *            every vertex incident to added edge
	 * @param e
	 *            element being added into edge and therefore graph
	 * @return true if graph changed by operation, false otherwise
	 */
	boolean addEdge(Collection<Vertex<? extends V, ? extends E>> vertices, E e);

	/**
	 * Adds a vertex containing v.
	 * 
	 * @param v
	 *            element being added into vertex and therefore graph
	 * @return true if graph changed by operation, false otherwise
	 */
	boolean addVertex(V v);

	/**
	 * Adds a vertex containing v with all edges in parameter collection.
	 * 
	 * @param edges
	 *            every edge which is a point to added vertex
	 * @param v
	 *            element being added into vertex and therefore graph
	 * @return true if graph changed by operation, false otherwise
	 */
	boolean addVertex(Collection<Edge<? extends V, ? extends E>> edges, V v);

}
