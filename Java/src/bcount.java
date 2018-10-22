import java.io.*;
import java.util.*;
/*
 * December 2015 Silver: Problem #3
ID: sijju
LANG: JAVA
TASK: bcount
*/
public class bcount {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new File("bcount.in"));
		int N = sc.nextInt(); 
		int Q = sc.nextInt();
		int[] freq_1 = new int[N + 1];
		int[] freq_2 = new int[N + 1];
		int[] freq_3 = new int[N + 1];
		int[] nums = new int[N];
		for(int i = 0; i < N; i ++){
			nums[i] = sc.nextInt();
		}
		int[][] queries = new int[Q][2];
		for(int i = 0; i < Q; i ++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			queries[i][0] = x;
			queries[i][1] = y;
		}
		for(int i = 0; i < N; i ++) {
			if(nums[i] == 1) {freq_1[i + 1] ++;}
			if(nums[i] == 2) {freq_2[i + 1] ++;}
			if(nums[i] == 3) {freq_3[i + 1] ++;}
		}
		for(int i = 1; i < N + 1; i ++){
			freq_1[i] += freq_1[i - 1];
			freq_2[i] += freq_2[i - 1];
			freq_3[i] += freq_3[i - 1];
		}
		PrintWriter pw = new PrintWriter(new File("bcount.out"));
		for(int[] q: queries) { 
			pw.println(Integer.toString((freq_1[q[1]] - freq_1[q[0] - 1])) + " " + Integer.toString((freq_2[q[1]] - freq_2[q[0] - 1])) + " " + Integer.toString((freq_3[q[1]] - freq_3[q[0] - 1]))  );
		}
		sc.close();
		pw.close();
	}
}