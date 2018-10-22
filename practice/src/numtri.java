import java.io.*;
import java.util.*;

/*
ID: dulluij1
LANG: JAVA
TASK: numtri
 */
public class numtri {
	
	static int[][] m = new int[1005][1005];  

	public static void main(String[] args)throws IOException
	{
		Scanner sc = new Scanner(new File("numtri.in"));
		int r = sc.nextInt();
		for(int i = 0; i < r; i ++){
			for(int j = 0; j <= i; j ++){
				m[i][j] = sc.nextInt();
			}
		}
		sc.close();
		
		PrintWriter pw = new PrintWriter(new File("numtri.out"));
		pw.println(solve(r,m));
		pw.close();
	}
	
	public static int solve(int n, int[][] triangle) 
	{
		for (int i = n - 2; i >= 0; i--) {
			for (int j = 0; j <= i; j++) {
				triangle[i][j] += max(triangle[i + 1][j], triangle[i + 1][j + 1]);
			}
		}
		return triangle[0][0];
	}

	
	public static int max(int a, int b)
	{
		return a > b? a: b;
	}
}
