/* @JUDGE_ID:   918643   706   C++    "Implementation" */
//Programming Challenges
//LC-Display Problem
//Siddarth Ijju


/* @BEGIN_OF_SOURCE_CODE */

#include <iostream>
#include <vector>

using namespace std;

vector<string> numbers[30];

int s;

void printNumber(){
    for(int i = 0; i < 2*s + 3; i ++){
        for(int j = 0; j < numbers[i].size(); j ++){
            cout << numbers[i][j];
        }
        cout << "\n";
    }
}

void addFlatMiddle(int row){
    numbers[row].push_back(" ");
    for(int i = 0; i < s; i ++){
        numbers[row].push_back("-");
    }
    numbers[row].push_back(" ");
}

void addBarLeft(int start){
    for(int i = start; i < start + s; i ++){
        numbers[i].push_back("|");
        for(int j = 0; j < s + 1; j ++){
            numbers[i].push_back(" ");
        }
    }
}

void addBarRight(int start){
    for(int i = start; i < start + s; i ++){
        for(int j = 0; j < s + 1; j ++){
            numbers[i].push_back(" ");
        }
        numbers[i].push_back("|");
    }
}

void addBarLeftRight(int start){
    for(int i = start; i < start + s; i ++){
        numbers[i].push_back("|");
        for(int j = 0; j < s; j ++){
            numbers[i].push_back(" ");
        }
        numbers[i].push_back("|");
    }
}

void addEmptyRow(int row){
    for(int i = 0; i < s + 2; i ++){
        numbers[row].push_back(" ");
    }
}


int main(){
    vector<pair<string, int>> nums;
    while(cin >> s && s != 0){
        string n;
        cin >> n;
        //special case
        nums.push_back(make_pair(n, s));
    }
    while(nums.size() > 0){
        string num = nums.front().first;
        s = nums.front().second;
        nums.erase(nums.begin());
        vector<int> n;
        for(char& c: num){
            int digit = c - '0';
            switch(digit){
                case 1:
                    addEmptyRow(0);
                    addBarRight(1);
                    addEmptyRow(s + 1);
                    addBarRight(s + 2);
                    addEmptyRow(2*s + 2);
                    break;
                case 2:
                    addFlatMiddle(0);
                    addBarRight(1);
                    addFlatMiddle(s + 1);
                    addBarLeft(s + 2);
                    addFlatMiddle(2*s + 2);
                    break;
                case 3:
                    addFlatMiddle(0);
                    addBarRight(1);
                    addFlatMiddle(s + 1);
                    addBarRight(s + 2);
                    addFlatMiddle(2*s + 2);
                    break;
                case 4:
                    addEmptyRow(0);
                    addBarLeftRight(1);
                    addFlatMiddle(s + 1);
                    addBarRight(s + 2);
                    addEmptyRow(2*s + 2);
                    break;
                case 5:
                    addFlatMiddle(0);
                    addBarLeft(1);
                    addFlatMiddle(s + 1);
                    addBarRight(s + 2);
                    addFlatMiddle(2*s + 2);
                    break;
                case 6:
                    addFlatMiddle(0);
                    addBarLeft(1);
                    addFlatMiddle(s + 1);
                    addBarLeftRight(s + 2);
                    addFlatMiddle(2*s + 2);
                    break;
                case 7:
                    addFlatMiddle(0);
                    addBarRight(1);
                    addEmptyRow(s + 1);
                    addBarRight(s + 2);
                    addEmptyRow(2*s + 2);
                    break;
                case 8:
                    addFlatMiddle(0);
                    addBarLeftRight(1);
                    addFlatMiddle(s + 1);
                    addBarLeftRight(s + 2);
                    addFlatMiddle(2*s + 2);
                    break;
                case 9:
                    addFlatMiddle(0);
                    addBarLeftRight(1);
                    addFlatMiddle(s + 1);
                    addBarRight(s + 2);
                    addFlatMiddle(2*s + 2);
                    break;
                case 0:
                    addFlatMiddle(0);
                    addBarLeftRight(1);
                    addEmptyRow(s + 1);
                    addBarLeftRight(s + 2);
                    addFlatMiddle(2*s + 2);
                    break;
            }
            if(c != num.back()){
                for(int i = 0; i < 2*s + 3; i ++){
                    numbers[i].push_back(" ");
                }
            }
        }
        printNumber();
        cout << "\n";
        for(int i=0; i < 2 * s + 3; i++){
            numbers[i].clear();
        }
    }
    return 0;
}

/* @END_OF_SOURCE_CODE */


