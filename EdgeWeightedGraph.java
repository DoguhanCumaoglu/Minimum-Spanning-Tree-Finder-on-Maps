package HW_4;

import java.util.Iterator;
import java.util.Stack;

public class EdgeWeightedGraph {
	private static final String NEWLINE = System.getProperty("line.separator");

	private final int V;
	private int E;
	private Bag<Edge>[] adj;

	/**
	 * Initializes an empty edge-weighted graph with {@code V} vertices and 0 edges.
	 *
	 * @param V the number of vertices
	 * @throws IllegalArgumentException if {@code V < 0}
	 */
	public EdgeWeightedGraph(int V) {
		this.V = V;
		this.E = 0;
		adj = (Bag<Edge>[]) new Bag[V];
		for (int v = 0; v < V; v++) {
			adj[v] = new Bag<Edge>();
		}
	}

	/**
	 * Returns the number of vertices in this edge-weighted graph.
	 *
	 * @return the number of vertices in this edge-weighted graph
	 */
	public int V() {
		return V;
	}

	/**
	 * Returns the number of edges in this edge-weighted graph.
	 *
	 * @return the number of edges in this edge-weighted graph
	 */
	public int E() {
		return E;
	}

	// throw an IllegalArgumentException unless {@code 0 <= v < V}

	public void addEdge(Edge e) {
		int v = e.either();
		int w = e.other(v);

		adj[v].add(e);
		adj[w].add(e);
		E++;
	}

	public Iterable<Edge> adj(int v) {

		return adj[v];
	}
	/**
	 * Returns all edges in this edge-weighted graph. To iterate over the edges in
	 * this edge-weighted graph, use foreach notation:
	 * {@code for (Edge e : G.edges())}.
	 *
	 * @return all edges in this edge-weighted graph, as an iterable
	 */
	public Iterable<Edge> edges() {
		Bag<Edge> list = new Bag<Edge>();
		for (int v = 0; v < V; v++) {
			int selfLoops = 0;
			for (Edge e : adj(v)) {
				if (e.other(v) > v) {
					list.add(e);
				}
				// add only one copy of each self loop (self loops will be consecutive)
				else if (e.other(v) == v) {
					if (selfLoops % 2 == 0)
						list.add(e);
					selfLoops++;
				}
			}
		}
		return list;
	}
}
