%{
#include <stdio.h>
int charCount = 0, wordCount = 0, lineCount = 0;
%}

%%
[^\n\t ]+  { wordCount++; charCount += yyleng; }
[\n]       { lineCount++; charCount++; }
[ \t]      { charCount++; }
%%

int main() {
    yylex();
    printf("Characters : %d\n", charCount);
    printf("Words : %d\n", wordCount);
    printf("Lines : %d\n", lineCount);
    return 0;
}

int yywrap() {
    return 1;
}
