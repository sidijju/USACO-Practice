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
  fin = fopen(".in", "r");
  fout = fopen(".out", "w");

}

//////// UTILITY /////////

//point struct plus distance funct
struct Point {
  int x;
  int y;

  Point(int a, int b) {
    x = a;
    y = b;
  }

  int dist(Point b) {
    return (x - b.x) * (x - b.x) + (y - b.y) * (y - b.y);
  }
};

/////// ALGORITHMS ///////

//logN
int binsearch(int target, vector<int> arr) {
  int low = 0;
  int high = arr.size() - 1;
  int middle;

  while (high >= low) {
    middle = low + (high - low)/2;
    if (target > arr[middle]) {
      low = middle + 1;
    } else if (target == arr[middle]) {
      return middle;
    } else {
      high = middle - 1;
    }
  }
  return middle;
}


/////// DATA STRUCTURES ////////
class union_find {
  private:
    vector<int> parent;
    vector<int> size;


  public:
    void add(int a) {
      parent.push_back(a);
      size.push_back(1);
    }

    void union(int a, int b) {
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
