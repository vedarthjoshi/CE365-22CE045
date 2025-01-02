import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

// INPUT FILE test.c
// int main() {
//     int a = 5 , 7H;
//     // assign value
//     char b = 'x';
//     /* return
//     value */
//     return a + b;
//     }

public class Compiler {
    public static void main(String[] args) {
        // DEBUG
        // char []checkingSymbols = {};

        String[] dataTypeList = { "void", "int", "char", "float", "double" };
        String cSourceFileName = "test.c";
        ArrayList<String> cTokenList = new ArrayList<String>();
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader(cSourceFileName));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(" ");

                for (String token : tokens) {
                    if (!token.isEmpty()) {
                        if (token.charAt(0) == '#') {
                            System.out.println("Include detcted");
                            for (String includeTokens : tokens) {
                                cTokenList.add(includeTokens.trim());
                            }
                        }
                        else{
                            // for (int i = 0; i < dataTypeList.length; i++) {
                            //     // System.out.println(i);
                            //     if (tokens[0].trim().equals(dataTypeList[i])) {
                            //         for (int j = 0; j < tokens.length; j++) {
                            //             if (tokens[j].contains("(") && tokens[j].contains(")")) {
                            //                 System.out.println(tokens[j]);
                            //                 System.out.println("is function");
                            //             }
                            //         }
                            //         // switch (i) {
                            //         //     case 0:
                            //         //         System.out.println("void Detected");
                            //         //         break;
                            //         //     case 1:
                            //         //         System.out.println("int Detected");
                            //         //         break;
                            //         //     case 2:
                            //         //         System.out.println("char Detected");
                            //         //         break;
                            //         //     case 3:
                            //         //         System.out.println("float Detected");
                            //         //         break;
                            //         //     case 4:
                            //         //         System.out.println("double Detected");
                            //         //         break;
                            //         //     default:
                            //         //         System.out.println("wtf detected");
                            //         // }
                            //     }
                            // }
                        }
                    }
                }

                System.out.println(cTokenList);

                // for (int i = 0; i < dataTypeList.length; i++) {
                // if (line.contains(dataTypeList[i])) {
                // switch (i) {
                // case 0:
                // System.out.println("void Detected");
                // break;
                // case 1:
                // System.out.println("int Detected");
                // break;
                // case 2:
                // System.out.println("char Detected");
                // break;
                // case 3:
                // System.out.println("float Detected");
                // break;
                // case 4:
                // System.out.println("double Detected");

                // case 5:
                // System.out.println("Include Detected");
                // break;
                // default :
                // System.out.println("wtf detected");
                // }
                // }
                // }
                // for (String token : tokens) {
                    // System.out.println(line);

                    // if (line.equals("\n") || line.equals("\t")) {
                    //     continue;
                    // } else {
                    //     token = token.trim();
                    //     cTokenList.add(token);
                    // }
                    // System.out.println(cTokenList);
                // }
                for (int i = 0; i < dataTypeList.length; i++) {
                    // System.out.println(i);
                    if (tokens[0].trim().equals(dataTypeList[i])) {
                        for (int j = 0; j < tokens.length; j++) {
                            if (tokens[j].contains("(") && tokens[j].contains(")")) {
                                System.out.println(tokens[j]);
                                System.out.println("is function");
                            }
                        }
                    }
                }
            }
            
            reader.close();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
