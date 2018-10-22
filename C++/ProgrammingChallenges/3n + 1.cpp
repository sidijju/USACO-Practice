/* @JUDGE_ID:   918643   100   C++    "Implementation" */
//Programming Challenges
//3n + 1 Problem
//Siddarth Ijju


/* @BEGIN_OF_SOURCE_CODE */

#include <iostream>

using namespace std;

int a;
int b;
int biggest;
int counter;

void func(int start, int end){
    for(int i = start; i <= end; i ++){
        int j = i;
        while(j != 1){
            if(j % 2 == 0){
                j /= 2;
            }else {
                j = 3*j + 1;
            }
            counter ++;
        }
        biggest = max(biggest, counter);
        counter = 1;
    }
}

int main(){
    //for every single (a, b) input pair
    while( cin >> a >> b ){
        int ina = a;
        int inb = b;
        if (a > b) swap(a, b);
        func(a, b);
        cout << ina << " " << inb << " " << biggest << "\n";
        biggest = 1;
        counter = 1;
    }
    return 0;
}

/* @END_OF_SOURCE_CODE */



