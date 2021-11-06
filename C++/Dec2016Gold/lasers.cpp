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
#define FOR(i, a, b) for(int i = a; i < b; i++)
#define FOR(i, a) for(int i = 0; i < a; i++)
#define ll long long

using namespace std;

int N, xL, yL, xB, yB;
int map<int, vector<int> > hmap;
int map<int, vector<int> > vmap;

int bfs(){
  vector< pair<int, int> > queue;
  queue.push_back(pos[xB][yB]);


}

int main() {
  FILE *fin, *fout;
  fin = fopen("lasers.in", "r");
  fout = fopen("lasers.out", "w");

  fscanf(fin, "%d %d %d %d %d\n", &N, &xL, &yL, &xB, &yB);
  int a, b;
  FOR(i, N) {
    fscanf(fin, "%d %d\n", &a, &b);
    hmap
  }

  int ans = 0;
  fprintf(fout, "%d\n", ans);
  return 0;
}
