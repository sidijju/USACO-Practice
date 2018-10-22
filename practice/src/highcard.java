import java.io.*;
import java.util.*;
/*
 * December 2015 Silver: Problem #2
ID: sijju
LANG: JAVA
TASK: highcard
*/
public class highcard {
	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner(new File("highcard.in"));
		int N = sc.nextInt();
		boolean[] nums = new boolean[2 * N];
		ArrayList<Integer> elsie = new ArrayList<Integer>();
		ArrayList<Integer> bessie = new ArrayList<Integer>();
		for(int i = 0; i < N; i ++){
			nums[sc.nextInt() - 1] = true;
		}
		for(int i = 1; i <= 2 * N; i ++) {
			if(nums[i - 1]) elsie.add(i);
			else bessie.add(i);
		}
		int index_b = 0;
		int index_e = 0;
		int max = 0;
		int b_size = bessie.size();
		while(index_b < b_size) {
			if(bessie.get(index_b) > elsie.get(index_e)) {
				max ++;
				index_b ++;
				index_e ++;
			}
			else {index_b ++;}
		}
		PrintWriter pw = new PrintWriter(new File("highcard.out"));
		pw.println(max);
		pw.close();
	}
}
