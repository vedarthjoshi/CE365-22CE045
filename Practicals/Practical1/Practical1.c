#include <stdio.h>
#include <string.h>
#include <stdbool.h>

bool isValidString(const char *str) {
    int i = 0;
    
    while (str[i] == 'a') {
        i++;
    }

    if (str[i] == 'b' && str[i + 1] == 'b' && str[i + 2] == '\0') {
        return true;
    }
    
    return false;
}

int main() {
    char input[100];

    
    printf("Enter a string: ");
    fgets(input, sizeof(input), stdin);

    
    size_t len = strlen(input);
    if (len > 0 && input[len - 1] == '\n') {
        input[len - 1] = '\0';
    }

    if (isValidString(input)) {
        printf("Valid String\n");
    } else {
        printf("Invalid String\n");
    }

    return 0;
}
