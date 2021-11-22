#include <stdio.h>
#include <stdlib.h>
#include <string>
#include <cmath>
#include <algorithm>
#include <iostream>
#include <vector>
#include <set>

using namespace std;

int main() {
  FILE *fin, *fout;
  fin = fopen("blocks.in", "r");
  fout = fopen("blocks.out", "w");

  int N;
  int nums[26] = {0};

  fscanf(fin, "%d\n", &N);
  for (int i = 0; i < N; i ++) {
    char a[11], b[11];
    fscanf(fin, "%s %s\n", a, b);

    int counta[26] = {0}, countb[26] = {0};

    for(int i = 0; a[i] != '\0'; i ++) {
      counta[int(a[i])-97]++;
    }
    for(int i = 0; b[i] != '\0'; i ++) {
      countb[int(b[i])-97]++;
    }

    for(int i = 0; i < 26; i++) {
      nums[i] += max(counta[i], countb[i]);
    }
  }

  for (int i = 0; i < 26; i++) {
    fprintf(fout, "%d\n", nums[i]);
  }
}
