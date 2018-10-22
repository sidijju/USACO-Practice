/*
ID: dwumath1
LANG: JAVA
PROB: maze1
 */
import java.util.*;
import java.io.*;

public class maze1dwu {
	static ArrayList<Point>[][] points;
	static int [][] minDistance;
	static Point [] exit = new Point[2];
	
	public static void main(String [] args)throws IOException{
		BufferedReader br = new BufferedReader(new FileReader("maze1.in"));
		PrintWriter out = new PrintWriter(new FileWriter("maze1.out"));
		// Input
		StringTokenizer s = new StringTokenizer(br.readLine());
		int W = Integer.parseInt(s.nextToken());
		int H = Integer.parseInt(s.nextToken());
		
		points = new ArrayList[H + 2][W + 2];
		minDistance = new int[H + 2][W + 2];
		
		for(int [] row : minDistance){
			Arrays.fill(row, Integer.MAX_VALUE);
		}
		
		for(int a = 0; a < points.length; a++){
			for(int b = 0; b < points[0].length; b++){
				points[a][b] = new ArrayList<Point>();
			}
		}
		
		for(int r = 0; r < 2 * H + 1; r++){
			String line = br.readLine();
			for(int c = 0; c < 2 * W + 1; c++){
				char c1 = line.charAt(c);
				if(c1 == ' '){
					// Top - bottom point
					if(c % 2 == 1 && r % 2 == 0){
						Point bottom = new Point(r / 2 + 1, c / 2 + 1);
						Point top = new Point(r / 2, c / 2 + 1);
						points[r / 2][c / 2 + 1].add(bottom);
						points[r / 2 + 1][c / 2 + 1].add(top);
					}
					// Left right point
					else if(r % 2 == 1 && c % 2 == 0){
						Point left = new Point(r / 2 + 1, c / 2);
						Point right = new Point(r / 2 + 1, c / 2 + 1);
						points[r / 2 + 1][c / 2].add(right);
						points[r / 2 + 1][c / 2 + 1].add(left);
					}
				}
			}
		}
		// Find the exit points
		findExit();
		for(int i = 0; i < 2; i++){
			boolean [][] visited = new boolean[H + 2][W + 2];
			LinkedList<Point> q = new LinkedList<Point>();
			Point p = exit[i];
			minDistance[p.xCoor][p.yCoor] = 0;
			BFS(p.xCoor, p.yCoor, visited, q);
		}
		int ans = 0;
		for(int i = 1; i < points.length - 1; i++){
			for(int j = 1; j < points[0].length - 1; j++){
				ans = Math.max(minDistance[i][j], ans);
			}
		}
		out.println(ans);
		out.close();
	}
	// Check if point is inbounds
	public static boolean inBounds(int a, int b){
		return a >= 0 && a < points.length && b >= 0 && b < points[0].length;
	}
	
	public static void BFS(int x, int y, boolean [][] visited, LinkedList<Point> q){
		// Point is already visited: break
		if(visited[x][y]){
			return;
		}
		// Visit point
		visited[x][y] = true;
		
		// Load all adjacent states to this point
		ArrayList<Point> adj = points[x][y];
		for(int i = 0; i < adj.size(); i++){
			Point p = adj.get(i);
			if(!visited[p.xCoor][p.yCoor]){
				q.add(p);
				// Change the minimum distance
				minDistance[p.xCoor][p.yCoor] = Math.min(minDistance[p.xCoor][p.yCoor], minDistance[x][y] + 1);	
			}
		}
		while(!q.isEmpty()){
			Point nextPoint = q.remove();
			BFS(nextPoint.xCoor, nextPoint.yCoor, visited, q);
		}
	}
	static class Point{
		int xCoor;
		int yCoor;
		
		Point(int x, int y){
			xCoor = x;
			yCoor = y;
		}
		
		public String toString(){
			return "(" + xCoor + ", " + yCoor + ")";
		}
	}
	// Sets the two exit points
	public static void findExit(){
		int index = 0;
		for(int r = 0; r < points.length && index != 2; r++){
			for(int c = 0; c < points[0].length && index != 2; c++){
				if(r == 0 || c == 0 || r == points.length - 1 || c == points[0].length - 1){
					if(points[r][c].size() != 0){
						exit[index] = new Point(r, c);
						index ++;
					}
				}
			}
		}
	}
}