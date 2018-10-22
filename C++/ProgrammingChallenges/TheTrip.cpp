/* @JUDGE_ID:   918643   10137   C++    "Implementation" */
//Programming Challenges
//The Trip Problem
//Siddarth Ijju


/* @BEGIN_OF_SOURCE_CODE */

#include <iostream>
#include <cstdio>

using namespace std;

//number of students
int N;

//answers array
double ans[100];

//array of costs
double costs[1001];

int main(){
    while(cin >> N && N != 0){
        double pos = 0, neg = 0, avg = 0;
        for(int i = 0; i < N; i ++){
            cin >> costs[i];
            avg += costs[i];
        }
        avg /= N;
        for(int i = 0; i < N; i ++){
            double v = (long) ((costs[i] - avg) * 100.0) / 100.0;
            if(v > 0) pos += v;
            else neg -= v;
        }
        printf("$%.2f\n", neg > pos? neg: pos);
    }
    return 0;
}

/* @END_OF_SOURCE_CODE */



