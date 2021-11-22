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

#define INF 1000000010


using namespace std;

struct Point {
  int x;
  int y;

  Point(int a, int b) {
    x = a;
    y = b;
  };
};

int cost(Point a, Point b) {
  return (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y);
}

int H, G;
vector<Point> h, g;

int dp[1001][1001][2];

int solve(int i, int j, int k) {
    if (i == H && j == G && k != 0) return INF;
    if (i == H && j == G) return 0;
    if (dp[i][j][k] != -1) return dp[i][j][k];
    Point cur = k == 0 ? h[i-1] : g[j-1];
    return dp[i][j][k] = min(
            i != H ? (cost(cur, h[i]) + solve(i+1, j, 0)) : INF,
            j != G ? (cost(cur, g[j]) + solve(i, j+1, 1)) : INF
    );
}

int main() {
  FILE *fin, *fout;
  fin = fopen("checklist.in", "r");
  fout = fopen("checklist.out", "w");

  fscanf(fin, "%d %d\n", &H, &G);

  for (int i = 0; i < H; i ++) {
    int a, b;
    fscanf(fin, "%d %d\n", &a, &b);
    h.push_back(Point(a, b));
  }
  for (int i = 0; i < G; i ++) {
    int a, b;
    fscanf(fin, "%d %d\n", &a, &b);
    g.push_back(Point(a, b));
  }

  for (int i = 0; i < 1001; i ++) {
    for (int j = 0; j < 1001; j++) {
      for (int k = 0; k < 2; k++) {
        dp[i][j][k] = -1;
      }
    }
  }

  fprintf(fout, "%d\n", solve(1, 0, 0));
}
