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
