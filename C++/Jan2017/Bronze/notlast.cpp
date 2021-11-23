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
  fin = fopen("notlast.in", "r");
  fout = fopen("notlast.out", "w");

  int N;
  fscanf(fin, "%d\n", &N);

  map<string, int> index;
  index.insert(mp("Bessie", 0));
  index.insert(mp("Elsie", 0));
  index.insert(mp("Daisy", 0));
  index.insert(mp("Gertie", 0));
  index.insert(mp("Annabelle", 0));
  index.insert(mp("Maggie", 0));
  index.insert(mp("Henrietta", 0));

  char name[256];
  FOR(i, 0, N) {
    int p;
    fscanf(fin, "%s %d\n", name, &p);
    index[name] = index[name] + p;
  }

  int minimum = INF, secmin = INF;
  int counter = 0;

  for (map<string, int>::iterator it = index.begin(); it != index.end(); it ++) {
    minimum = min(it->second, minimum);
  }

  for (map<string, int>::iterator it = index.begin(); it != index.end(); it ++) {
    if (it->second > minimum) {
      secmin = min(it->second, secmin);
    }
  }

  for (map<string, int>::iterator it = index.begin(); it != index.end(); it ++) {
    if (it->second == secmin) {
      counter ++;
    }
  }

  if (counter != 1) {
    fprintf(fout, "Tie\n");
  } else {
    for (map<string, int>::iterator it = index.begin(); it != index.end(); it ++) {
      if (it->second == secmin) {
        fprintf(fout, "%s\n", it->first.c_str());
      }
    }
  }
}
