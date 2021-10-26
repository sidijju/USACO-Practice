#include <stdio.h>
#include <stdlib.h>
#include <string>
#include <cmath>
#include <algorithm>
#include <iostream>
#include <vector>
#include <set>

using namespace std;

int N, Q;
vector<int> bales;

//logN
int binsearch(int target) {
  int low = 0;
  int high = N-1;
  int middle;

  while (high >= low) {
    middle = (high + low)/2;
    if (target > bales[middle]) {
      low = middle + 1;
    } else if (target == bales[middle]) {
      return middle;
    } else {
      high = middle - 1;
    }
  }
  return middle;
}

int handle_query(int lower, int higher) {
  int h, l;
  h = binsearch(higher);
  l = binsearch(lower);

  while (bales[h] > higher && h > 0) {
    h--;
  }
  while (bales[l] < lower && l < N) {
    l++;
  }
  return h - l + 1;
}

//2 3 5 7


int main() {
  FILE *fin, *fout;
  fin = fopen("haybales.in", "r");
  fout = fopen("haybales.out", "w");

  fscanf(fin, "%d %d\n", &N, &Q);
  int n, q1, q2;

  //N
  for (int i = 0; i < N; i ++) {
    fscanf(fin, "%d", &n);
    bales.push_back(n);
  }

  //NlogN
  sort(bales.begin(), bales.end());

  //QlogN
  for (int i = 0; i < Q; i++) {
    fscanf(fin, "%d %d\n", &q1, &q2);
    fprintf(fout, "%d\n", handle_query(q1, q2));
  }
}
