package HW_4;

import java.util.Arrays;

public class KruskalMST {
	private static final double FLOATING_POINT_EPSILON = 1E-12;

	private double weight; // weight of MST
	private Queue<Edge> mst = new Queue<Edge>(); // edges in MST

	/**
	 * Compute a minimum spanning tree (or forest) of an edge-weighted graph.
	 * 
	 * @param G the edge-weighted graph
	 */
	public KruskalMST(EdgeWeightedGraph G) {

		// create array of edges, sorted by weight
		Edge[] edges = new Edge[G.E()];
		int t = 0;
		for (Edge e : G.edges()) {
			edges[t++] = e;
		}
		Arrays.sort(edges);

		// run greedy algorithm
		UF uf = new UF(G.V());
		for (int i = 0; i < G.E() && mst.size() < G.V() - 1; i++) {
			Edge e = edges[i];
			int v = e.either();
			int w = e.other(v);

			// v-w does not create a cycle
			if (uf.find(v) != uf.find(w)) {
				uf.union(v, w); // merge v and w components
				mst.enqueue(e); // add edge e to mst
				weight += e.weight();
			}
		}

	}

	/**
	 * Returns the edges in a minimum spanning tree (or forest).
	 * 
	 * @return the edges in a minimum spanning tree (or forest) as an iterable of
	 *         edges
	 */
	public Iterable<Edge> edges() {
		return mst;
	}

	/**
	 * Returns the sum of the edge weights in a minimum spanning tree (or forest).
	 * 
	 * @return the sum of the edge weights in a minimum spanning tree (or forest)
	 */
	public double weight() {
		return weight;
	}
}
// check optimality conditions (takes time proportional to E V lg* V)
