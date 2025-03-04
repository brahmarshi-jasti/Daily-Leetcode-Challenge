import java.util.*;

class UnionFind {

	private int n;
	private int[] parent;
	private int[] rank;

	public UnionFind(int n) {
		this.n = n;
		this.parent = new int[n];
		this.rank = new int[n];
		for (int i = 0; i < n; i++) {
			rank[i] = 0;
			parent[i] = i;
		}
	}

	public void unionMethod(int u, int v) {
		int i = find(u);
		int j = find(v);
		if (i == j)
			return;

		int x = rank[i];
		int y = rank[j];
		if (x > y) {
			parent[j] = i;
		} else if (x < y) {
			parent[i] = j;
		} else {
			parent[i] = j;
			rank[j] = y + 1;
		}
	}

	public int find(int u) {
		if (parent[u] == u) {
			return u;
		}
		return find(parent[u]);
	}
}

class Solution {

	public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {

		int mstWeight = findMstWeight(edges, n, new int[] {}, -1);
		List<Integer> criticalEdges = new ArrayList<>();
		List<Integer> pseudoCriticalEdges = new ArrayList<>();

		int index = 0;
		for (int[] edge : edges) {
			if (findMstWeight(edges, n, new int[] {}, index) > mstWeight)
				criticalEdges.add(index);
			else if (findMstWeight(edges, n, edge, -1) == mstWeight)
				pseudoCriticalEdges.add(index);
			index++;
		}

		return new ArrayList<>(Arrays.asList(criticalEdges, pseudoCriticalEdges));
	}

	// The below function is used to return weight of MST obtained using BORUVKA
	// algorithm
	public int findMstWeight(int[][] edges, int n, int[] mustIncludeEdge, int deletedEdgeIndex) {

		int mstWeight = 0;
		UnionFind uf = new UnionFind(n);
		int components = n;
		int tmp = -1, index = 0;
		List<List<Integer>> minWtEdges = new ArrayList<>();

		for (int x = 0; x < n; x++) {
			minWtEdges.add(Arrays.asList(-1, -1, -1));
		}

		if (mustIncludeEdge.length == 3) {
			uf.unionMethod(mustIncludeEdge[0], mustIncludeEdge[1]);
			mstWeight += mustIncludeEdge[2];
			components = n - 1;
		}

		while (components > 1) {
			boolean flag = false;
			index = 0;
			for (int[] edge : edges) {
				if (index == deletedEdgeIndex) {
					index++;
					continue;
				}
				int u = edge[0];
				int v = edge[1];
				int s1 = uf.find(u);
				int s2 = uf.find(v);
				int w = edge[2];
				if (s1 != s2) {
					tmp = minWtEdges.get(s1).get(2);
					if (tmp > w || tmp == -1) {
						minWtEdges.set(s1, Arrays.asList(u, v, w));
					}
					tmp = minWtEdges.get(s2).get(2);
					if (tmp > w || tmp == -1) {
						minWtEdges.set(s2, Arrays.asList(u, v, w));
					}
				}
				index++;
			}

			for (int z = 0; z < n; z++) {
				int w = minWtEdges.get(z).get(2);
				if (w != -1) {
					int u = minWtEdges.get(z).get(0);
					int v = minWtEdges.get(z).get(1);
					int s1 = uf.find(u);
					int s2 = uf.find(v);

					if (s1 != s2) {
						uf.unionMethod(s1, s2);
						mstWeight += w;
						flag = true;
						components--;
					}
				}
			}

			for (List<Integer> list : minWtEdges) {
				list.set(2, -1);
			}
			if (flag == false)
				break;
		}

		int finalParent = uf.find(0);
		for (int i = 0; i < n; i++) {
			int f = uf.find(i);
			if (f != finalParent)
				return Integer.MAX_VALUE;
		}

		return mstWeight;
	}
}
