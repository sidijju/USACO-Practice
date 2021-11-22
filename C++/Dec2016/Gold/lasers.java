import java.util.*;
import java.io.*;

public class lasers {

	// Store points and lists of which y's link to which x's and vice versa.
	public static int n;
	public static int[][] pts;
	public static HashSet[] xLinks;
	public static HashSet[] yLinks;

	public static void main(String[] args) throws Exception {

		BufferedReader stdin = new BufferedReader(new FileReader("lasers.in"));
		StringTokenizer tok = new StringTokenizer(stdin.readLine());
		n = Integer.parseInt(tok.nextToken());
		pts = new int[n+2][2];

		// These will store unique x and y values for coordinate compression.
		TreeSet<Integer> xVals = new TreeSet<Integer>();
		TreeSet<Integer> yVals = new TreeSet<Integer>();

		// Read in start and end.
		for (int i=0; i<2; i++) {
			pts[i][0] = Integer.parseInt(tok.nextToken());
			pts[i][1] = Integer.parseInt(tok.nextToken());
			xVals.add(pts[i][0]);
			yVals.add(pts[i][1]);
		}

		// Read in the other points.
		for (int i=2; i<n+2; i++) {
			tok = new StringTokenizer(stdin.readLine());
			pts[i][0] = Integer.parseInt(tok.nextToken());
			pts[i][1] = Integer.parseInt(tok.nextToken());
			xVals.add(pts[i][0]);
			yVals.add(pts[i][1]);
		}

		// Make Hash Maps of x and y vals.
		HashMap<Integer,Integer> xMap = makeMap(xVals);
		HashMap<Integer,Integer> yMap = makeMap(yVals);

		// coordinate compress first
		for (int i=0; i<n+2; i++) {
			pts[i][0] = xMap.get(pts[i][0]);
			pts[i][1] = yMap.get(pts[i][1]);
		}

		// Set up arrays of hashsets. xLinks[i] will store all y values we can reach with a particular x value.
		xLinks = new HashSet[xMap.size()];
		for (int i=0; i<xLinks.length; i++) xLinks[i] = new HashSet<Integer>();

		// yLinks[i] will store all x values we can reach with a particular y value.
		yLinks = new HashSet[yMap.size()];
		for (int i=0; i<yLinks.length; i++) yLinks[i] = new HashSet<Integer>();

		// Set up links.
		for (int i=0; i<n+2; i++) {
			xLinks[pts[i][0]].add(pts[i][1]);
			yLinks[pts[i][1]].add(pts[i][0]);
		}

		// Write out the result, the lower of the two possible ending states.
		PrintWriter out = new PrintWriter(new FileWriter("lasers.out"));
		out.println(bfs(0,1)-1);
		out.close();
		stdin.close();
	}

	public static int bfs(int s, int e) {

		// Screen out this case first - just shoot! (We add 1 because we subtract one from our final result.)
		if (pts[s][0] == pts[e][0] || pts[s][1] == pts[e][1]) return 1;

		// Will store distances to each x and y distance.
		int[][] dist = new int[2][];
		dist[0] = new int[xLinks.length];
		dist[1] = new int[yLinks.length];
		Arrays.fill(dist[0], -1);
		Arrays.fill(dist[1], -1);

		// These are actually 1 (exception is the starting pt itself which is 0).
		dist[0][pts[s][0]] = 1;
		dist[1][pts[s][1]] = 1;

		// Queue for BFS.
		LinkedList<Integer> q = new LinkedList<Integer>();

		// LSB of value in queue is 0 if x coordinate, 1 if y coordinate.
		q.offer(pts[s][0] << 1);
		q.offer((pts[s][1] << 1) + 1);

		// Run BFS.
		while (q.size() > 0) {

			// Get next item, extract out pieces.
			int cur = q.poll();
			int xy = cur&1;
			int val = cur >> 1;

			// We've got to the end!
			if (val == pts[e][xy]) return dist[xy][val];

			// We will add from the opposite list.
			HashSet[] list = xy == 0 ? xLinks : yLinks;

			// Go through each new value we can reach from val.
			for (Integer item : (HashSet<Integer>)list[val]) {
				if (dist[1-xy][item] == -1) {
					dist[1-xy][item] = dist[xy][val] + 1;
					q.offer( (item<<1) + (1-xy));
				}
			}
		}

		// Never made it.
		return -1;
	}

	// Returns a hashmap of all values in ts mapped in integer order, starting at 0.
	public static HashMap<Integer,Integer> makeMap(TreeSet<Integer> ts) {

		// Make map by polling each item from the tree set in order.
		HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
		for (int i=0; ts.size()>0; i++)
			map.put(ts.pollFirst(), i);
		return map;
	}
}
