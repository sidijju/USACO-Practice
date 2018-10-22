/* @JUDGE_ID:   918643   10189   C++    "Implementation" */
//Programming Challenges
//Minesweeper Problem
//Siddarth Ijju


/* @BEGIN_OF_SOURCE_CODE */

#include <iostream>

using namespace std;

//number of rows
int m = 1;

//number of cols
int n = 1;

//array to store input in for every field
string input[100][100][100];

int mn[100][2];

//field number
int field = 0;

void printField(int f){
    cout << "Field #" << f << ":\n";
    m = mn[f][0];
    n = mn[f][1];
    for(int i = 0; i < m; i ++){
        for(int j = 0; j < n; j ++){
            string curr = input[f][i][j]; //contents of cell
            if(curr == "*") cout << "*"; //mine
            else {
                int count = 0;
                //check every possible neigbor
                //neighbors below
                if(i + 1 < m && input[f][i + 1][j] == "*") count ++;
                if(i + 1 < m && j + 1 < n && input[f][i + 1][j + 1] == "*") count ++;
                if(i + 1 < m && j - 1 >= 0 && input[f][i + 1][j - 1] == "*") count ++;
                //neighbors above
                if(i - 1 >= 0 && input[f][i - 1][j] == "*") count ++;
                if(i - 1 >= 0 && j + 1 < n && input[f][i - 1][j + 1] == "*") count ++;
                if(i - 1 >= 0 && j - 1 >= 0 && input[f][i - 1][j - 1] == "*") count ++;
                //neighbors on same row
                if(j - 1 >= 0 && input[f][i][j - 1] == "*") count ++;
                if(j + 1 < n && input[f][i][j + 1] == "*") count ++;
                cout << count;
            }
        }
        //new row
        cout << "\n";
    }
}

int main(){
    while(cin >> m >> n && m != 0 && n != 0){
        field ++;
        mn[field][0] = m;
        mn[field][1] = n;
        //gets input
        for(int i = 0; i < m; i ++){
            string line;
            cin >> line;
            for(int j = 0; j < n; j ++){
                input[field][i][j] = line[j];
            }
        }
    }
    for(int i = 1; i <= field; i ++){
        printField(i);
        if(i != field) cout << "\n";
    }
    return 0;
}

/* @END_OF_SOURCE_CODE */
