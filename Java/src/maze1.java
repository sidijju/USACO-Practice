import java.io.*;
import java.util.*;

/*
ID: dulluij1
LANG: JAVA
TASK: ttwo
 */
/* USACO Training
 * Overfencing
 * Type: Longest Path
 * Solution: BFS through each node
 */
public class maze1 {
	static int[][] map;
	static boolean[][] visited;
	static int[][] cost;
	static int[][] grid;
	static int W;
	static int H;
	static int depth;
	static int maxDistance = 0;
	static Queue<int[]> q = new LinkedList<int[]>();

	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new File("maze1.in"));
		W = sc.nextInt();
		H = sc.nextInt();
		sc.nextLine();
		//get map
		map = new int[2*H + 1][2*W + 1];
		visited = new boolean[H*2 + 1][2*W + 1];
		cost = new int[H*2 + 1][2*W + 1];
		for(int j = 0; j <= 2*H; j ++) {
			String next = sc.nextLine();
			String[] nextArr = next.split("");
			for(int i = 0; i <= 2*W; i ++) {
				map[j][i] = nextArr[i].equals(" ") ? 0: 1;
			}
		}
		//get entrypoints
		ArrayList<int[]> points = new ArrayList<int[]>(2);
		for(int i = 0; i < 2*W; i ++) {
			if(map[0][i] == 0) points.add(new int[] {0, i});
			if(map[2*H][i] == 0) points.add(new int[] {2*H, i});
		}
		for(int i = 0; i < 2*H; i ++) {
			if(map[i][0] == 0) points.add(new int[] {i, 0});
			if(map[i][2*W] == 0) points.add(new int[] {i, 2*W});
		}
		for(int[] point: points) {
			for(int[] row: cost) {
				for(int i = 0; i < row.length; i ++) {
					row[i] = Integer.MAX_VALUE;
				}
			}
			cost[point[0]][point[1]] = 0;
			q.add(point);
			bfs();
			for(int[] row: cost) {
				for(int c: row) {
					if(c < Integer.MAX_VALUE) {
						maxDistance = Math.max(maxDistance, (c / 2));
					}
				}
			}
			PrintWriter pw = new PrintWriter(new File("maze1.out"));
			pw.write(maxDistance);
			pw.close();
		}
	}
	
	public static void bfs() {
		for(boolean[] row: visited) {
			for(int i = 0; i < row.length; i ++) {
				row[i] = false;
			}
		}
		while(!q.isEmpty()) {
			int[] p = q.poll();
			visited[p[0]][p[1]] = true;
			if(p[0] < H*2 && !visited[p[0] + 1][p[1]] && map[p[0] + 1][p[1]] != 1) {
				q.add(new int[]{p[0] + 1, p[1]});
				cost[p[0] + 1][p[1]] = Math.min(cost[p[0] + 1][p[1]], cost[p[0]][p[1]] + 1);
			}
			if(p[0] > 0 && !visited[p[0] - 1][p[1]] && map[p[0] - 1][p[1]] != 1) {
				q.add(new int[]{p[0] - 1, p[1]});
				cost[p[0] - 1][p[1]] = Math.min(cost[p[0] - 1][p[1]], cost[p[0]][p[1]] + 1);
			}
			if(p[1] > 0 && !visited[p[0]][p[1] - 1] && map[p[0]][p[1] - 1] != 1) {
				q.add(new int[]{p[0], p[1] - 1});
				cost[p[0]][p[1] - 1] = Math.min(cost[p[0]][p[1] - 1], cost[p[0]][p[1]] + 1);
			}
			if(p[1] < 2*W && !visited[p[0]][p[1] + 1] && map[p[0]][p[1] + 1] != 1) {
				q.add(new int[]{p[0], p[1] + 1});
				cost[p[0]][p[1] + 1] = Math.min(cost[p[0]][p[1] + 1], cost[p[0]][p[1]] + 1);
			}
		}
	}
}