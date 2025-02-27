%option noyywrap
%{
#include <stdio.h>

int v = 0;
int c = 0;		

int yylex(); 
%}
%%
[aeiouAEIOU] { v++; }  
[a-zA-Z] { c++; }  
[\t ] ;
\n { return 0; }
. ;
%%
int main() {
    printf("Enter String: ");
    yylex();
    printf("No of Vowels: %d\n", v);
    printf("No of Consonants: %d\n", c);
    return 0;
}
