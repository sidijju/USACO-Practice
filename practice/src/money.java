/*
ID: dulluij1
LANG: JAVA
TASK: money
 * Problem Type: Coin Change / Build up from previous 
 */
import java.util.*;
import java.io.*;
public class money {
	static int V;
	static int N;
	static long[] cost;
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new File("money.in"));
		V = sc.nextInt();
		N = sc.nextInt();
		cost = new long[N + 1];
		//1 way to write a sum of 0
		cost[0] = 1;
		for(int i = 0; i < V; i ++) {
			
			int coin = sc.nextInt();
			for(int j = coin; j <= N; j ++) {
				//you can build up from the one m before it
				cost[j] += cost[j - coin];
			}
		}
		PrintWriter pw = new PrintWriter(new File("money.out"));
		//System.out.println(cost[N]);
		pw.println(cost[N]);
		pw.close();
	}
}
