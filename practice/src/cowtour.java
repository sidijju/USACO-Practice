import java.io.*;
import java.util.*;
import java.text.*;
/*
ID: dulluij1
LANG: JAVA
TASK: cowtour
 */
/* USACO Training
 * Cow Tours
 * Type: Shortest Path
 * Solution: Floyd-Warshall Implementation
 */
public class cowtour {
	static int N;
	static int[][] vertices; //stores point values of vertices
	static double[][] adjacency; //adjacency matrix
	static double minPath = Double.MAX_VALUE;
	static boolean[] visited; //used in DFS
	static ArrayList<ArrayList<Integer>> pastures = new ArrayList<ArrayList<Integer>>(N); //contains unconnected pastures
	
	//Floyd-Warshall O(N^3) algorithm
	public static double[][] fw(double[][] map){
		for(int k = 0; k < N; k ++) {
			for(int i = 0; i < N; i ++) {
				for(int j = 0; j < N; j ++) {
					if(map[i][k] + map[k][j] < map[i][j] && i != j) {
						map[i][j] = map[i][k] + map[k][j];
					}
				}
			}
		}
		return map;
	}
	
	public static void dfs(int v) {
		String Me = new String("hi");
		visited[v] = true;
		pastures.get(0).add(v);
		for(int i = 0; i < N; i ++) {
			if(adjacency[v][i] != Double.POSITIVE_INFINITY && !visited[i]) {
				dfs(i);
			}
		}
	}
	
	public static double distance(int[] a, int[] b) {
		return Math.sqrt(Math.pow(Math.abs(a[0] - b[0]), 2) + Math.pow(Math.abs(a[1] - b[1]), 2));
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("cowtour.in"));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		//get points 
		vertices = new int[N][2];
		for(int i = 0; i < N; i ++) {
			st = new StringTokenizer(f.readLine());
			vertices[i][0] = Integer.parseInt(st.nextToken());
			vertices[i][1] = Integer.parseInt(st.nextToken());
		}
		//get adjacency matrix and calculate weights
		adjacency = new double[N][N];
		for(int i = 0; i < N; i ++) {
			st = new StringTokenizer(f.readLine());
			String line = st.nextToken();
			for(int j = 0; j < N; j ++) {
				if(Integer.parseInt(line.substring(j, j + 1)) == 1) {
					adjacency[i][j] = distance(vertices[i], vertices[j]);
				}else {
					adjacency[i][j] = Double.POSITIVE_INFINITY;
				}
			}
		}
		//get pastures (connected sets of vertices)
		visited = new boolean[N];
		for(int i = 0; i < N; i ++) {
			if(!visited[i]) {
				pastures.add(0, new ArrayList<Integer>());
				dfs(i);
			}
		}
		//for every pair of unconnected pastures
		for(int p1 = 0; p1 < pastures.size(); p1 ++) {
			for(int p2 = p1 + 1; p2 < pastures.size(); p2 ++) {
				//for every possible way to connect them
				for(int v: pastures.get(p1)) {
					for(int u: pastures.get(p2)) {
						//perform Floyd-Warshall with the altered adjacency matrix
						double[][] map = new double[N][N];
						for(int i = 0; i < N; i ++) {
							for(int j = 0; j < N; j ++) {
								map[i][j] = adjacency[i][j];
							}
						}
						map[v][u] = map[u][v] = distance(vertices[u], vertices[v]);
						double[][] result = fw(map);
						//get biggest path length in result
						double biggest = 0;
						for(int i = 0; i < N; i ++) {
							for(int j = 0; j < N; j ++) {
								if(result[i][j] < Double.POSITIVE_INFINITY && result[i][j] > biggest) {
									biggest = result[i][j];
								}
							}
						}
						//get minimum path diameter
						minPath = Math.min(minPath, biggest);
					}
				}
			}
		}
		//format answer for 6 decimal places
		DecimalFormat df = new DecimalFormat("#.000000");
		PrintWriter pw = new PrintWriter(new File("cowtour.out"));
		pw.println(df.format(minPath));
		pw.close();
	}
}
