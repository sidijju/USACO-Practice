import java.io.*;
import java.util.*;
/*
 * ID: sijju
 * LANG: JAVA
 * TASK: measurement
 */
class Entry implements Comparable<Entry>{
	public  String name;
	public int day;
	public int change;
	
	@Override
	public int compareTo(Entry o) {
		return this.day > o.day? 1: this.day == o.day? 0: -1;
	}
	
	public Entry(int d, String n, int c) {
		day = d;
		name = n;
		change = c;
	}
}
public class measurement {
	public static void main(String[] args) throws IOException{
		int[] output = {7, 7, 7}; //all cows start at 7;
		int[] values = {1, 10, 100}; //001 is Bessie, 010 is Elsie, and 100 is Mildred
		
		//create scanner input and get N
		Scanner sc = new Scanner(new File("measurement.in"));
		int N = sc.nextInt();
		sc.nextLine();
		
		//get input of each Entry
		Entry[] input = new Entry[N];
		for(int i = 0; i < N; i ++) {
			String[] in = sc.nextLine().split(" ");
			input[i] = new Entry(Integer.parseInt(in[0]),
								in[1],
								in[2].substring(0, 1) == "+"? 
								Integer.parseInt(in[2].substring(1, 2)):Integer.parseInt(in[2]));
		}
		
		//sort array by day of Entry (chronological order)
		Arrays.sort(input);
		sc.close();	
		//iterate through changes, figure out how many display changes there are
		//current cows at top determined by unique sums (111, 110, 101, 100, 11, 10, 1)
		//also guaranteed only 1 measurement per day so no need to deal with multiple Entries per day
		int top = 7;
		int numchanges = 0;
		for(int i = 0; i < N; i ++) {
			String n = input[i].name;
			//check which output to actually change
			output[n.equals("Bessie")? 0: n.equals("Elsie")? 1: 2] += input[i].change;
			//get maximum among outputs
			int max = Math.max(Math.max(output[0], output[1]), output[2]);
			//store calculation for current top
			int ntop = 0;
			for(int j = 0; j < 3; j ++) {
				ntop += output[j] == max? values[j]: 0;
			}
			//update top & change if ntop is different
			if(top != ntop) {
				top = ntop;
				numchanges ++;
			}
		}
		PrintWriter pw = new PrintWriter(new File("measurement.out"));
		pw.println(numchanges);
		pw.close();
	}
}