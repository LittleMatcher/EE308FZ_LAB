#define _CRT_SECURE_NO_DEPRECATE

#include <iostream>
#include <string>
#include <cstdio>
#include <cstdlib>
#include <string.h>
#include<regex>
#include<stack>
using namespace std;
#define N 100

int ifelse_num = 0;
int ifelseif_num = 0;
int switchNum = 0;
int caseNum[100];
int countKeyNum = 0;
class File {
public:
    void LodeFile(const string& path);
};
bool isIf(string s) {
    regex pattern("if");
    smatch result;
    bool ismatch = regex_search(s, result, pattern);
    return ismatch;
}
bool isElse(string s) {
    regex pattern("else");
    smatch result;
    bool ismatch = regex_search(s, result, pattern);
    return ismatch;
}
bool isEI(string s) {
    regex pattern("else if");
    smatch result;
    bool ismatch = regex_search(s, result, pattern);
    return ismatch;
}
bool isSwitch(string s) {
    regex pattern("switch");
    smatch result;
    bool ismatch = regex_search(s, result, pattern);
    return ismatch;
}
bool isCase(string s) {
    regex pattern("case");
    smatch result;
    bool ismatch = regex_search(s, result, pattern);
    return ismatch;
}
void countFunSwitch(stack <string> switchCase) {
    while (!switchCase.empty()) {
        while (switchCase.top() == "case") {
            caseNum[switchNum]++;
            switchCase.pop();
        }
        if (switchCase.top() == "switch") {
            switchNum++;
            switchCase.pop();
        }
    }
 
}
void countFunIf(stack <string> str_stack) {
    int count = 0;
    while (!str_stack.empty()&&str_stack.top() != "if") {
        if (str_stack.top() == "else if") {         
            count++;
        }
        
        str_stack.pop();
       
    }
    if (count == 0) {
        ifelse_num++;
    }
    else {
        ifelseif_num++;
    }
    if(!str_stack.empty()) {
        str_stack.pop();
    }
    

}

void countKey(string s) {

    string keyword[32] = { "auto", "break", "case", "char", "const", "continue", "default", "do", "double", "else", "enum", "extern",
                "float", "for", "goto", "if", "int", "long", "register", "return", "short", "signed", "sizeof", "static",
                "struct", "switch", "typedef", "unsigned", "union", "void", "volatile", "while" };
    s = "   " + s;
    for (int i = 0; i < 32; i++) {
        regex pattern("[^a-zA-Z0-9_]" + keyword[i] + "[^a-zA-Z0-9_]");
        smatch result;
        bool ismatch = regex_search(s, result, pattern);
        if (ismatch) {
            countKeyNum++;
            //cout << keyword[i] << endl;
        }
    }
}
void File::LodeFile(const string& path)
{
    FILE* p = fopen(path.c_str(), "rb");
    if (!p)
        exit(0);
    char str[N + 1];
    strcpy_s(str, path.c_str());
    stack <string> str_stack;
    stack <string> switchCase;
    while (fgets(str, N, p) != NULL)
    {
        //printf("%s", str);
        //cout << str ;
        countKey(str);
        if (isEI(str)) {
           // cout << "ELSE IF" << endl;
            str_stack.push("else if");
        }
        else if (isElse(str)) {
           // cout << "ELSE" << endl;
            str_stack.push("else");
            countFunIf(str_stack);
        }
        else if (isIf(str)) {
           // cout << "IF" << endl;
            str_stack.push("if");
        }
        else if (isSwitch(str)) {
            //cout << "SWITCH" << endl;
            switchCase.push("switch");
        }
        else if (isCase(str)) {
           // cout << "CASE" << endl;
            switchCase.push("case");
        }
        else {
        }
    }
    countFunSwitch(switchCase);
    fclose(p);

}

int main()
{
    //cout << "C:\Users\csip\Desktop\testdemo_1.cpp" << endl;

    File file;
    string pathStr;
    cin >> pathStr;
    int level;
    cin >> level;
    file.LodeFile(pathStr);
    if (level > 0) {
        cout << "total num:" << countKeyNum << endl;
        if (level > 1) {
            cout << "switch num:" << switchNum << endl;
            cout << "case num:";
            for (int i = switchNum - 1; i >= 0; i--) {
                cout << caseNum[i] << " ";
          
                }
            
            if (level > 2) {
                cout << endl;
                cout << "if-else num: " << ifelse_num << endl;
                if (level > 3) {
                    cout << "if-else if-else num: " << ifelseif_num << endl;
                }
            }
        }

    }
    return 0;
}

