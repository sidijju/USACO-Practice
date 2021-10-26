#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <cmath>
#include <algorithm>

using namespace std;

int main() {
  FILE *fin, *fout;
  int maxx, minx, maxy, miny;
  int x1, y1, x2, y2;
  fin = fopen("square.in", "r");
  fout = fopen("square.out", "w");

  fscanf(fin, "%d %d %d %d\n", &x1, &y1, &x2, &y2);
  maxx = max(x1, x2);
  maxy = max(y1, y2);
  miny = min(y1, y2);
  minx = min(x1, x2);
  fscanf(fin, "%d %d %d %d\n", &x1, &y1, &x2, &y2);
  maxx = max(x2, max(x1, maxx));
  maxy = max(y2, max(y1, maxy));
  miny = min(y2, min(y1, miny));
  minx = min(x2, min(x1, minx));

  int square = pow(max(maxx - minx, maxy - miny), 2);
  fprintf(fout, "%d\n", square);
}
