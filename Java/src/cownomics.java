import java.io.*;
import java.util.*;

public class cownomics {
	static int N;
	static int M;
	static String[][] cows;
	
	public static void main(String[] args) throws IOException	{
		Scanner sc = new Scanner(new File("cownomics.in"));
		N = sc.nextInt();
		M = sc.nextInt();
		sc.nextLine();
		cows = new String[2*N][M];
		for(int i = 0; i < 2*N; i ++) {
			String[] arr = sc.nextLine().split("");
			for(int j = 0; j < M; j ++) {
				cows[i][j] = arr[j];
			}
		}
		//iterate over all M^3 possible positions
		String[] spotty = new String[N];
		int count = 0;
		for(int i = 0; i < M; i ++) {
			for(int j = i + 1; j < M; j++) {
				for(int k = j + 1; k < M; k ++) {
					boolean works = true;
					for(int l = 0; l < N; l ++) {
						spotty[l] = cows[l][i] + cows[l][j] + cows[l][k];
					}
					for(int l = N; l < 2*N; l ++) {
						String plain = cows[l][i] + cows[l][j] + cows[l][k];
						for(int m = 0; m < N; m ++) {
							if(plain.equals(spotty[m])) {
								works = false;
								break;
							}
						}
					}
					if(works == true) {
						count ++;
					}
				}
			}
		}
		PrintWriter pw = new PrintWriter(new File("cownomics.out"));
		pw.println(count);
		pw.close();
	}
}
