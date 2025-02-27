import java.io.*;
import java.util.*;
import java.util.regex.*;

class Practical3 {
    private static final Set<String> keywords = new HashSet<>(Arrays.asList(
            "int", "char", "void", "return", "if", "else", "while", "for", "struct",
            "long", "float", "double", "do", "switch", "case", "default", "break", "continue"
    ));
    

    private static final Set<String> operators = new HashSet<>(Arrays.asList(
            "+", "-", "*", "/", "%", "=", "==", "!=", ">", "<", ">=", "<=", "&&", "||", "&", "|", "^", "<<", ">>"
    ));


    private static final Set<Character> punctuations = new HashSet<>(Arrays.asList(
            '(', ')', '{', '}', '[', ']', ',', ';'
    ));


    private static final List<String> symbolTable = new ArrayList<>();


    private static final List<String> lexicalErrors = new ArrayList<>();


    private static final Pattern identifierPattern = Pattern.compile("^[a-zA-Z_][a-zA-Z0-9_]*$");
    private static final Pattern numberPattern = Pattern.compile("^\\d+(\\.\\d+)?$");
    private static final Pattern stringPattern = Pattern.compile("^\".*\"$|^'.'$");

    public static void processFile(String filename) {
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            StringBuilder modifiedSource = new StringBuilder();
            boolean inMultilineComment = false;

            System.out.println("TOKENS:");

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();

                
                if (line.contains("//")) {
                    line = line.substring(0, line.indexOf("//")).trim();
                }

        
                if (line.contains("/*")) {
                    inMultilineComment = true;
                }
                if (inMultilineComment) {
                    if (line.contains("*/")) {
                        inMultilineComment = false;
                        continue;
                    }
                    continue;
                }

                if (line.isEmpty()) continue; 

                modifiedSource.append(line).append("\n");

                // Tokenize the line
                StringTokenizer tokenizer = new StringTokenizer(line, " +-*/%=;(),{}[]&|<>!\"", true);
                while (tokenizer.hasMoreTokens()) {
                    String token = tokenizer.nextToken().trim();
                    if (token.isEmpty()) continue;

                    classifyToken(token);
                }
            }

            scanner.close();
            
            System.out.println("\nSYMBOL TABLE ENTRIES:");
            for (int i = 0; i < symbolTable.size(); i++) {
                System.out.println((i + 1) + ") " + symbolTable.get(i));
            }

            System.out.println("\nLEXICAL ERRORS:");
            for (String error : lexicalErrors) {
                System.out.println(error);
            }

            System.out.println("\nMODIFIED SOURCE CODE:");
            System.out.println(modifiedSource);

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        }
    }

    private static void classifyToken(String token) {
        if (keywords.contains(token)) {
            System.out.println("Keyword: " + token);
        } else if (operators.contains(token)) {
            System.out.println("Operator: " + token);
        } else if (punctuations.contains(token.charAt(0))) {
            System.out.println("Punctuation: " + token);
        } else if (stringPattern.matcher(token).matches()) {
            System.out.println("String: " + token);
        } else if (numberPattern.matcher(token).matches()) {
            System.out.println("Constant: " + token);
        } else if (identifierPattern.matcher(token).matches()) {
            System.out.println("Identifier: " + token);
            if (!symbolTable.contains(token)) {
                symbolTable.add(token);
            }
        } else {
            System.out.println("Invalid Token: " + token);
            lexicalErrors.add(token + " invalid lexeme");
        }
    }

    public static void main(String[] args) {
        processFile("test.c");
    }
}
