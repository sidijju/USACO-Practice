import java.io.*;
import java.util.*;
/*
ID: dulluij1
LANG: JAVA
TASK: milk3
*/

public class milk3 {
	
	static int[] cap = new int[3];
	static boolean[][][] searched = new boolean[21][21][21];
	static int a;
	static int b;
	static int c;

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new File("milk3.in"));
		a = sc.nextInt();
		b = sc.nextInt();
		c = sc.nextInt();
		
		sc.close();
		dfs(0, 0, c);
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int j = 0; j < 21; j ++){
			for(int k = 0; k < 21; k ++){
				if(searched[0][j][k] == true && !list.contains(k)){list.add(k);}
			}
		}
		
		Collections.sort(list);
		String s = "";
		for(int i = 0; i < list.size(); i ++){
			s += list.get(i);
			if(i != list.size() - 1) s += " ";
		}
		PrintWriter pw = new PrintWriter(new File("milk3.out"));
		pw.println(s);
		pw.close();
	}
	
	public static void dfs(int i, int j, int k)
	{
	    if(searched[i][j][k]) return;
	    searched[i][j][k] = true;
	    for(int ii = 0; ii < 6; ++ii){
	        int n;
	        int x = 0, y = 0, z = 0;
	        if(ii == 0){
	            n = min(i, b - j);
	            x = i - n;
	            y = j + n;
	            z = k;
	        }
	        if(ii == 1){
	            n = min(i, c - k);
	            x = i - n;
	            y = j;
	            z = k + n;
	        }
	        if(ii == 2){
	            n = min(j, a - i);
	            x = i + n;
	            y = j - n;
	            z = k;
	        }
	        if(ii == 3){
	            n = min(j, c - k);
	            z = k + n;
	            y = j - n;
	            x = i;
	        }
	        if(ii == 4){
	            n = min(k, a - i);
	            x = i + n;
	            y = j;
	            z = k - n;
	        }
	        if(ii == 5){
	            n = min(k, b - j);
	            x = i;
	            y = j + n;
	            z = k - n;
	        }
	        dfs(x, y, z);
	    }
	}
	
	public static int min(int a, int b){
		int ret = a < b? a: b;
		return ret;
	}
}
