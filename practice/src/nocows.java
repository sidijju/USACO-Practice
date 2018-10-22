/*
ID: dulluij1
LANG: JAVA
PROB: nocows
 */
import java.util.*;
import java.io.*;
public class nocows{
static int [][] dp;
static boolean [][] calculated;
public static void main(String[] args)throws IOException {
BufferedReader b = new BufferedReader(new FileReader("nocows.in"));
PrintWriter out = new PrintWriter(new FileWriter("nocows.out"));
// Input
StringTokenizer input = new StringTokenizer(b.readLine());
int N = Integer.parseInt(input.nextToken());
int K = Integer.parseInt(input.nextToken());
// Positition n, k represents number of trees with n nodes and k height
dp = new int[N+1][K+1];
calculated = new boolean[N+1][K+1];
for(int i = 0; i < dp.length; i++){
for(int j = 0; j < dp[0].length; j++){
dp[i][j] = -1;
}
}
// for(int i = 0; i < dp.length; i++){
// for(int j = 0; j < dp[0].length; j++){
// dp[i][j] = solve(i,j);
// }
// }
for(int i = 0; i < N + 1; i++){
for(int j = 0; j < K + 1; j++){
dp[i][j] = dps(i, j);
}
}
// for(int [] arr : dp){
// System.out.println(Arrays.toString(arr));
// }
System.out.println(dp[N][K] % 9901);
out.println(dp[N][K] % 9901);
out.close();
}
public static int dps(int n, int k){
if(!inBounds(n, k)){
return 0;
}
else if(n % 2 == 0 || 2 * k - 1 > n){
calculated[n][k] = true;
return 0;
}
// // If already calculated return calculated
else if(calculated[n][k]){
return dp[n][k];
}
// Base case - 1 node height 1
else if(k == 1 && n == 1){
return 1;
}
else if(k == 1 && n == 0){
return 0;
}
else{
int combinations = 0;
// Reduce to case n - 1, k-1 among two subtrees
for(int lTreeN = 1; lTreeN <= n - 1; lTreeN ++){
// Left and right node numbers
int lNodes = lTreeN;
int rNodes = n - 1 - lNodes;
// Height of each subtree
for(int h = 0; h <= k - 1; h++){
// Same node and height - Count only once
if(h == k-1){
combinations += dps(lNodes, h) * dps(rNodes, h);
combinations %= 9901;
}
// If heights are not equal, add refleciton as well
else{
combinations += dps(lNodes, k - 1) * dps(rNodes, h);
combinations += dps(rNodes, k - 1) * dps(lNodes, h);
combinations %= 9901;
}
}
}
calculated[n][k] = true;
return combinations;
// return combinations %= 9901;
}
}
public static boolean inBounds(int a, int b){
return(a >= 0 && a < dp.length && b >= 0 && b < dp[0].length);
}
}