import java.io.*;
import java.util.*;
/*
 * ID: sijju
 * LANG: JAVA
 * TASK: shuffle
 */
public class shuffle {
	
	static int N;
	static int[] shuffle;
	static String[] ids;

	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new File("shuffle.in"));
		PrintWriter pw = new PrintWriter(new File("shuffle.out"));
		N = sc.nextInt();
		shuffle = new int[N];
		ids = new String[N];
		for(int i = 0; i < N; i ++) {
			shuffle[i] = sc.nextInt() - 1;
		}
		sc.nextLine();
		ids = sc.nextLine().split(" ");
		sc.close();		
		
		for(int i = 0; i < N; i ++) {
			pw.println(ids[shuffle[shuffle[shuffle[i]]]]);
		}
		
		pw.close();
	}

}
