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

class union_find {
  private:
    vector<int> parent;
    vector<int> size;


  public:
    void add(int a) {
      parent.push_back(a);
      size.push_back(1);
    }

    void merge(int a, int b) {
      a = find(a);
      b = find(b);

      if (a != b) {
        if (size[a] < size[b]) {
          parent[a] = b;
          size[a] += size[b];
        } else {
          parent[b] = a;
          size[b] += size[a];
        }
      }

    }

    int find(int a) {
      if (parent[a] != a) {
        parent[a] = find(parent[a]);
        return parent[a];
      }
      return a;
    }
};

struct Cow {
  int x;
  int y;

  Cow(int a, int b) {
    x = a;
    y = b;
  }
};

int N;
vector<Cow> cows;

struct Edge {
  int a;
  int b;
  int dist;

  Edge(int x, int y) {
    a = x;
    b = y;
    int xd = (cows[a].x - cows[b].x);
    int yd = (cows[a].y - cows[b].y);
    dist = xd * xd + yd * yd;
  }

  bool operator<(const Edge&a) const{
      return dist < a.dist;
  }
};

vector<Edge> edges;
union_find ds;

int main() {
  FILE *fin, *fout;
  fin = fopen("moocast.in", "r");
  fout = fopen("moocast.out", "w");

  fscanf(fin, "%d\n", &N);

  int x, y;
  for (int i = 0; i < N; i ++) {
    fscanf(fin, "%d %d\n", &x, &y);
    cows.push_back(Cow(x, y));
    ds.add(i);
  }

  for (int i = 0; i < N; i ++) {
    for (int j = i + 1; j < N; j ++) {
      edges.push_back(Edge(i, j));
    }
  }

  sort(edges.begin(), edges.end());

  int ans = 0;
  for (int i = 0; i < edges.size(); i ++) {
    Edge e = edges[i];
    if (ds.find(e.a) != ds.find(e.b)) {
      ds.merge(e.a, e.b);
      ans = e.dist;
    }
  }

  fprintf(fout, "%d\n", ans);
}
