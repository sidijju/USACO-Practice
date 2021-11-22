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
#define SORT(vec) sort(vec.begin(), vec.end())
#define ll long long

using namespace std;

int N, xL, yL, xB, yB;
map<int, vector<int> > xtoy;
map<int, vector<int> > ytox;

struct Line {
  int val;
  bool horizontal;

  Line(int v, bool h) {
    val = v;
    horizontal = h;
  };

  bool operator == (const Line &o) const {
    return val == o.val && horizontal == o.horizontal;
  };

  bool operator < (const Line &o) const {
    if (horizontal == o.horizontal) {
      return val < o.val? -1: 1;
    }
    return 0;
  }
};

int main() {
  FILE *fin, *fout;
  fin = fopen("lasers.in", "r");
  fout = fopen("lasers.out", "w");

  fscanf(fin, "%d %d %d %d %d\n", &N, &xL, &yL, &xB, &yB);
  int a, b;
  FOR(i, 0, N) {
    fscanf(fin, "%d %d\n", &a, &b);
    if (xtoy.find(a) != xtoy.end()) {
      xtoy.find(a)->second.push_back(b);
    } else {
      vector<int> v;
      v.push_back(b);
      xtoy.insert(pair<int,vector<int> >(a, v));
    }

    if (ytox.find(b) != ytox.end()) {
      ytox.find(b)->second.push_back(a);
    } else {
      vector<int> v;
      v.push_back(a);
      ytox.insert(pair<int,vector<int> >(b, v));
    }
  }

  queue<Line> q;
  map<Line, int> dist;
  q.push(Line(xL, 1));
  dist.insert(pair<Line, int> (Line(xL, 1), 0));
  q.push(Line(yL, 0));
  dist.insert(pair<Line, int> (Line(yL, 0), 0));

  int ret = -1;
  while(!q.empty()) {
    Line curr = q.front();
    q.pop();
    //check if it gets to endpoint
    if ((curr.val == xB && curr.horizontal == 0) || (curr.val == yB && curr.horizontal == 1)) {
      ret = dist.find(curr)->second;
      cout << ret << endl;
      break;
    }

    map<int, vector<int> > source = curr.horizontal? ytox: xtoy;
    if (source.find(curr.val) != source.end()) {
      vector<int> v = source.find(curr.val)->second;
      FOR(i, 0, v.size()) {
        int d = v[i];
        Line next = Line(d, !curr.horizontal);
        if (dist.find(next) == dist.end()) {
          dist.insert(pair<Line, int> (next, (dist.find(curr)->second) + 1));
          q.push(next);
        }
      }
    }
  }
  fprintf(fout, "%d\n", ret);
}
