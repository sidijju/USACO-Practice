/*
ID: dulluij1
LANG: JAVA
TASK: inflate
 * Problem Type: Standard Knapsack
 */
import java.util.*;
import java.io.*;
public class inflate {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new File("inflate.in"));
		int M = sc.nextInt();
		int N = sc.nextInt();
		int[] score = new int[10001];
		//for every time, score pair
		for(int i = 0; i < N; i ++) {
			int currScore = sc.nextInt();
			int currTime = sc.nextInt();
			//for every time from the current one
			for(int j = currTime; j <= M; j ++) {
				//max I can get at time j is either my score 
				//or the previous score + my own
				score[j] = Math.max(score[j], score[j - currTime] + currScore);
			}
		}
		PrintWriter pw = new PrintWriter(new File("inflate.out"));
		pw.println(score[M]);
		pw.close();
	}
}
