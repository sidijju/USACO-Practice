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
  fin = fopen("cowsignal.in", "r");
  fout = fopen("cowsignal.out", "w");

  int M, N, K;
  fscanf(fin, "%d %d %d\n", &M, &N, &K);

  char line[11] = {'\0'};
  char modified[101] = {'\0'};

  while (M > 0) {
    fscanf(fin, "%s\n", line);
    for (int j = 0; j < N; j ++) {
      for (int k = 0; k < K; k++) {
        modified[K * j + k] = line[j];
      }
    }
    for (int k = 0; k < K; k++) {
      fprintf(fout, "%s\n", modified);
    }
    M--;
  }
}
