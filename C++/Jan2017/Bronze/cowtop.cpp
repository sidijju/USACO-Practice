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
#define FORr(i, a, b) for(int i = a; i > b; i--)
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
  fin = fopen("cowtip.in", "r");
  fout = fopen("cowtip.out", "w");

  int N;
  char grid[10][10] = {{0}};

  fscanf(fin, "%d\n", &N);

  FOR(i, 0, N) {
    char row[N+1];
    fscanf(fin, "%s\n", row);
    FOR(j, 0, N) {
      grid[i][j] = row[j];
    }
  }

  int count = 0;
  FORr(i, N-1, -1) {
    FORr(j, N-1, -1) {
      if(grid[i][j] == '1') {
        count ++;
        FOR(a, 0, i + 1) {
          FOR(b, 0, j + 1) {
            if (grid[a][b] == '1') {
              grid[a][b] = '0';
            } else {
              grid[a][b] = '1';
            }
          }
        }
      }
    }
  }
  fprintf(fout, "%d\n", count);
  return 0;
}
