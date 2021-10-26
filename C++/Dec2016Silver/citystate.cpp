#include <stdio.h>
#include <stdlib.h>
#include <string>
#include <cmath>
#include <algorithm>
#include <iostream>
#include <vector>
#include <set>
#include <map>

using namespace std;

int main() {
  FILE *fin, *fout;
  fin = fopen("citystate.in", "r");
  fout = fopen("citystate.out", "w");

  int N;
  char city[3] = {'\0'}, state[3] = {'\0'};
  fscanf(fin, "%d\n", &N);

  vector< pair<string, string> > queries;
  map< string, vector<string> > hashmap;

  //O(N)
  for (int i = 0; i < N; i ++) {
    char a[11] = {'\0'}, b[11] = {'\0'};
    fscanf(fin, "%s %s\n", a, b);

    for (int j = 0; j < 2; j ++) {
      city[j] = a[j];
      state[j] = b[j];
    }

    queries.push_back(make_pair(city, state));

    //O(1) amortized
    if (!(city[0] == state[0] && city[1] == state[1])) {
      map< string, vector<string> >::iterator el = hashmap.find(state);
      if (el != hashmap.end()) {
        el->second.push_back(city);
      } else {
        vector<string> n;
        n.push_back(city);
        hashmap.insert(make_pair(state, n));
      }
    }
  }

  //O(N)
  int ans = 0;
  for (int i = 0; i < N; i ++) {
    string city = queries[i].first;
    string state = queries[i].second;
    vector<string> matches = hashmap[city];
    for (int j = 0; j < matches.size(); j++) {
      if (matches[j][0] == state[0] && matches[j][1] == state[1]) {
        ans++;
      }
    }
  }

  fprintf(fout, "%d\n", ans/2);
}
