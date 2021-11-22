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

struct Cow {
  int x;
  int y;
  int p;

  Cow(int ix, int iy, int ip) {
    x = ix;
    y = iy;
    p = ip;
  }
};

double square_dist(Cow c1, Cow c2) {
  return pow(c2.x - c1.x, 2) + pow(c2.y - c1.y, 2);
}

int main() {
  FILE *fin, *fout;
  fin = fopen("moocast.in", "r");
  fout = fopen("moocast.out", "w");

  int N;
  vector<Cow> cows;
  fscanf(fin, "%d\n", &N);

  //get cows
  int x, y, p;
  for (int i = 0; i < N; i ++) {
    fscanf(fin, "%d %d %d\n", &x, &y, &p);
    cows.push_back(Cow(x, y, p));
  }

  //construct adj matrix
  //O(N^2)
  int adj[200][200] = {0};
  for (int i = 0; i < N; i ++) {
    Cow c1 = cows[i];
    for (int j = i + 1; j < N; j ++) {
      Cow c2 = cows[j];
      if (square_dist(c1, c2) <= pow(c1.p, 2)) {
        adj[i][j] = 1;
      }
      if (square_dist(c1, c2) <= pow(c2.p, 2)) {
        adj[j][i] = 1;
      }
    }
  }

  //for each cow run BFS
  //O(N^3)
  int ans = 1;
  for (int i = 0; i < N; i ++) {

    queue<int> q;
    int visited[200] = {0};
    int count = 0;
    q.push(i);
    while (!q.empty()) {
      int cow = q.front();
      q.pop();
      if (visited[cow] == 1) {
        continue;
      }
      count ++;
      visited[cow] = 1;
      for (int j = 0; j < N; j ++) {
        if(adj[cow][j] == 1) {
          q.push(j);
        }
      }
    }
    ans = max(ans, count);

  }

  fprintf(fout, "%d\n", ans);
}
