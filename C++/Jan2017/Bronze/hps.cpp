#include <stdio.h>
#include <stdlib.h>
#include <string>
#include <cmath>
#include <algorithm>
#include <iostream>
#include <vector>
#include <set>
#include <map>
#include <math.h>
#include <queue>
using namespace std;
#define FOR(i, a, b) for(int i = a; i < b; i++)
#define SORT(vec) sort(vec.begin(), vec.end())
#define A first
#define B second
#define mp make_pair
#define pb push_back
#define ll long long
#define INF 1000000010
typedef pair<int, int> ii;
typedef vector<int> vi;
typedef vector<ii> vii;



int main() {
  FILE *fin, *fout;
  fin = fopen("hps.in", "r");
  fout = fopen("hps.out", "w");

  //matches[i][j] = how many matches where i is for first cow and j is for second
  int matches[4][4] = {0};
  int N;
  fscanf(fin, "%d\n", &N);
  FOR(i, 0, N) {
    int a, b;
    fscanf(fin, "%d %d\n", &a, &b);
    matches[a][b]++;
  }

  int ans = max(matches[1][2] + matches[2][3] + matches[3][1], matches[1][3] + matches[3][2] + matches[2][1]);
  fprintf(fout, "%d\n", ans);
  // 1 > 2 > 3 > 1 (123, 231, 312)
  // 1 > 3 > 2 > 1 (132, 321, 213)
}
