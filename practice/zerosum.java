/*
ID: dulluij1
LANG: JAVA
TASK: zerosum
 * Problem Type: DFS
 */
import java.io.*;
import java.util.*;

public class zerosum {
	static int N;
	static int[] e;
	static PrintWriter pw;
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new File("zerosum.in"));
		N = sc.nextInt();
		e = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
		pw = new PrintWriter(new File("zerosum.out"));
		dfs(1, 1, 0, 1, "1");
		pw.close();
	}
	
	public static void dfs(int i, int sign, int sum, int num, String expression) {
		if(i == N) {
			if(sum + sign * num == 0)
			pw.println(expression);
			//System.out.println(expression);
			return; 
		}
		//try blank
		dfs((i + 1), (sign), sum, (e[i] + num * 10), (expression + " " + String.valueOf(e[i])));
		//try minus
		dfs((i + 1), 1, (sum + sign*num), e[i], (expression + "+" + String.valueOf(e[i])));
		//try plus
		dfs((i + 1), -1, (sum + sign*num), e[i], (expression + "-" + String.valueOf(e[i])));
	}
}
