import java.io.*;
import java.util.*;
/*
 * Problem 5 - Section 2.3
 * Controlling Companies
 * Solution: DFS
ID: sijju
LANG: JAVA
TASK: concom
*/
public class concom {
	//maximum number of companies possible
	static final int C = 101;
	
	//2-D array showing which companies own which percent of which
	static int[][] owns = new int[C][C];
	
	//2-D array showing which companies control which
	static boolean[][] controls = new boolean[C][C];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new File("concom.in"));
		int N = sc.nextInt();
		
		//initialize base cases (company a owns company a)
		for(int i = 0; i < C; i ++) {
			controls[i][i] = true;
		}
		for(int i = 0; i < N; i ++) {
			addOwner(sc.nextInt(), sc.nextInt(), sc.nextInt());
		}

		PrintWriter pw = new PrintWriter(new File("concom.out"));
		for(int i = 0; i < C; i ++) {
			for(int j = 0; j < C; j ++) {
				if(controls[i][j] && i != j) {
					//System.out.println(i + " " + j);
					pw.println(i + " " + j);
				}
			}
		}
		pw.close();
	}

	private static void addOwner(int a, int b, int p) {
		
		//add percentage controlled of company b to 
		//each controller of company a
		for(int k = 0; k < C; k ++) {
			if(controls[k][a]) {
				owns[k][b] += p;
			}
		}
		
		//check if any new controls have been
		//added for company b
		for(int k = 0; k < C; k ++) {
			if(owns[k][b] > 50) {
				addController(k, b);
			}
		}
	}

	private static void addController(int a, int b) {
		//if we already know that we control this company, return out of dfs
		if(controls[a][b]) return;
		
		//a now controls b
		controls[a][b] = true;
		
		//since a now controls b, 
		//everything b owns must now be 
		//owned by a

		for(int k = 0; k < C; k ++) {
			owns[a][k] += owns[b][k];
		}
		
		//add b to the controllers of a
		for(int k = 0; k < C; k ++) {
			if(controls[k][a]) {
				addController(k, b);
			}
		}
		
		//check if a now controls anything new
		for(int k = 0; k < C; k ++) {
			if(owns[a][k] > 50) {
				addController(a, k);
			}
		}
	}
}
