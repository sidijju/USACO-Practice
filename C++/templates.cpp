#include <stdio.h>
#include <stdlib.h>
#include <string>
#include <cmath>
#include <algorithm>
#include <iostream>
#include <vector>
#include <set>
#include <map>
#include <math>

using namespace std;

int main() {
  FILE *fin, *fout;
  fin = fopen(".in", "r");
  fout = fopen(".out", "w");

}

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
