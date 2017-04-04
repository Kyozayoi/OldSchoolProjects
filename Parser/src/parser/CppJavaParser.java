import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.util.Stack;

public class CppJavaParser {

    public static void main(String[] args) throws Exception {
        File sourceCode = new File(args[0]);
        CppJavaParser(sourceCode);
    }

    public static void CppJavaParser(File FileName) throws Exception {

        Stack<Character> parenthesisStack = new Stack<>();
        Stack<Integer> parenthesisPosition = new Stack<>();
        Stack<String> parenthesisLine = new Stack<>();
        Stack<Character> bracketStack = new Stack<>();
        Stack<Integer> bracketPosition = new Stack<>();
        Stack<String> bracketLine = new Stack<>();
        Stack<Character> curlyBracesStack = new Stack<>();
        Stack<Integer> curlyBracesPosition = new Stack<>();
        Stack<String> curlyBracesLine = new Stack<>();
        Stack<Character> blockStack = new Stack<>();
        Stack<Integer> blockPosition = new Stack<>();
        Stack<String> blockLine = new Stack<>();
        Stack<Character> doubleQuoteStack = new Stack<>();
        Stack<Integer> doubleQuotePosition = new Stack<>();
        Stack<String> doubleQuoteLine = new Stack<>();
        Stack<Character> singleQuoteStack = new Stack<>();
        Stack<Integer> singleQuotePosition = new Stack<>();
        Stack<String> singleQuoteLine = new Stack<>();
        Stack<Integer> ifdefPosition = new Stack<>();
        Stack<String> ifdefLine = new Stack<>();

        char p;
        String q;
        boolean error = false;
        boolean dQuote = false;
        boolean sQuote = false;
        boolean blockComment = false;
        boolean singleComment = false;
        int ifs = 0;

        FileReader Iread = new FileReader(FileName);
        BufferedReader IRead = new BufferedReader(Iread);

        int errorLine = 1;
        int errorChar = 0;
        String ErrorMessage = null;
        String theString = IRead.readLine();

        while (theString != null) {
            theString = theString.trim();
            for (int w = 0; w < theString.length(); w++) {
                p = theString.charAt(w);
                if (p == '/' || p == '"' || p == '\'') {
    if (p == '/') {
        if (w < theString.length() - 1 && theString.charAt(w + 1) == '*') {
            if (!dQuote && !sQuote && !singleComment) {
                blockComment = true;
                blockStack.push(p);
                blockPosition.push(errorLine);
                blockPosition.push(w);
                blockLine.push(theString);
            }
        } else if (w > 0 && theString.charAt(w - 1) == '*') {
            if (!dQuote && !sQuote && !singleComment) {
                blockComment = false;
                blockStack.pop();
                blockPosition.pop();
                blockPosition.pop();
                blockLine.pop();
            }
        } else if (w < theString.length() - 1 && theString.charAt(w + 1) == '/') {
            if (!dQuote && !sQuote && !blockComment) {
                singleComment = true;
            }
        }
    } else if (p == '\'') {
        if (!blockComment && !dQuote && !singleComment) {
            if (sQuote) {
                if (theString.charAt(w + 1) == '\'' && theString.charAt(w - 1) == '\\') {
    sQuote = true;
                } else {
    sQuote = false;
    singleQuoteStack.pop();
    singleQuotePosition.pop();
    singleQuotePosition.pop();
    singleQuoteLine.pop();
                }
            } else {
                sQuote = true;
                singleQuoteStack.push(p);
                singleQuotePosition.push(errorLine);
                singleQuotePosition.push(w);
                singleQuoteLine.push(theString);
            }
        }
    } else if (p == '"') {
        if (!blockComment && !sQuote && !singleComment) {
            if (dQuote) {
                dQuote = false;
                doubleQuoteStack.pop();
                doubleQuotePosition.pop();
                doubleQuotePosition.pop();
                doubleQuoteLine.pop();
            } else {
                dQuote = true;
                doubleQuoteStack.push(p);
                doubleQuotePosition.push(errorLine);
                doubleQuotePosition.push(w);
                doubleQuoteLine.push(theString);
            }
        }
    }
                }
                if (!blockComment && !dQuote && !sQuote && !singleComment) {
    if (p == '(') {
        parenthesisLine.push(theString);
        parenthesisPosition.push(errorLine);
        parenthesisPosition.push(w);
        parenthesisStack.push(p);
    }
    if (p == ')') {
        if (parenthesisStack.empty()) {
            ErrorMessage = "Extra Closing Parenthesis";
            System.out.println(errorLine + ":" + w + ": " + '"' + theString + '"' + " ERROR: " + ErrorMessage);
            error = true;
        } else {
            parenthesisStack.pop();
            parenthesisPosition.pop();
            parenthesisPosition.pop();
            parenthesisLine.pop();
        }
    }
    if (p == '[') {
        bracketLine.push(theString);
        bracketPosition.push(errorLine);
        bracketPosition.push(w);
        bracketStack.push(p);
    }
    if (p == ']') {
        if (bracketStack.empty()) {
            ErrorMessage = "Extra Closing Bracket";
            System.out.println(errorLine + ":" + w + ": " + '"' + theString + '"' + " ERROR: " + ErrorMessage);
            error = true;
        } else {
            bracketStack.pop();
            bracketPosition.pop();
            bracketPosition.pop();
            bracketLine.pop();
        }
    }
    if (p == '{') {
        curlyBracesLine.push(theString);
        curlyBracesPosition.push(errorLine);
        curlyBracesPosition.push(w);
        curlyBracesStack.push(p);
    }
    if (p == '}') {
        if (curlyBracesStack.empty()) {
            ErrorMessage = "Extra Closing Curly Brace";
            System.out.println(errorLine + ":" + w + ": " + '"' + theString + '"' + " ERROR: " + ErrorMessage);
            error = true;
        } else {
            curlyBracesStack.pop();
            curlyBracesPosition.pop();
            curlyBracesPosition.pop();
            curlyBracesLine.pop();
        }
    }
    if (p == '#') {
        if (theString.contains("#ifdef")) {
            ifs++;
            ifdefLine.push(theString);
            ifdefPosition.push(errorLine);
            ifdefPosition.push(w);
        }
        if (theString.contains("#ifndef")) {
            ifs++;
            ifdefLine.push(theString);
            ifdefPosition.push(errorLine);
            ifdefPosition.push(w);
        }
        if (theString.contains("#if defined")) {
            ifs++;
            ifdefLine.push(theString);
            ifdefPosition.push(errorLine);
            ifdefPosition.push(w);
        }
        if (theString.contains("#if ! defined")) {
            ifs++;
            ifdefLine.push(theString);
            ifdefPosition.push(errorLine);
            ifdefPosition.push(w);
        }
        if (theString.contains("#else")) {
            if (ifs == 0) {
                ErrorMessage = "Extra #else call";
                System.out.println(errorLine + ":" + w + ": " + '"' + theString + '"' + " ERROR: " + ErrorMessage);
                error = true;
            }
        }
        if (theString.contains("#endif")) {
            if (ifs == 0) {
                ErrorMessage = "Extra #endif call";
                System.out.println(errorLine + ":" + w + ": " + '"' + theString + '"' + " ERROR: " + ErrorMessage);
                error = true;
            } else {
                ifs--;
                ifdefLine.pop();
                ifdefPosition.pop();
                ifdefPosition.pop();
            }
        }
    }
                }
            }
            singleComment = false;
            sQuote = false;
            errorLine++;
            theString = IRead.readLine();
        }
        while (!ifdefLine.isEmpty()) {
            errorChar = ifdefPosition.pop();
            errorLine = ifdefPosition.pop();
            theString = ifdefLine.pop();
            ErrorMessage = "Extra #if statement";
            System.out.println(errorLine + ":" + errorChar + ": " + '"' + theString + '"' + " ERROR: " + ErrorMessage);
            error = true;
        }
        while (!parenthesisStack.isEmpty()) {
            errorChar = parenthesisPosition.pop();
            errorLine = parenthesisPosition.pop();
            theString = parenthesisLine.pop();
            parenthesisStack.pop();
            ErrorMessage = "Extra Opening Parenthesis";
            System.out.println(errorLine + ":" + errorChar + ": " + '"' + theString + '"' + " ERROR: " + ErrorMessage);
            error = true;
        }
        while (!bracketStack.isEmpty()) {
            errorChar = bracketPosition.pop();
            errorLine = bracketPosition.pop();
            theString = bracketLine.pop();
            bracketStack.pop();
            ErrorMessage = "Extra Opening Bracket";
            System.out.println(errorLine + ":" + errorChar + ": " + '"' + theString + '"' + " ERROR: " + ErrorMessage);
            error = true;
        }
        while (!curlyBracesStack.isEmpty()) {
            errorChar = curlyBracesPosition.pop();
            errorLine = curlyBracesPosition.pop();
            theString = curlyBracesLine.pop();
            curlyBracesStack.pop();
            ErrorMessage = "Extra Opening Curly Brace";
            System.out.println(errorLine + ":" + errorChar + ": " + '"' + theString + '"' + " ERROR: " + ErrorMessage);
            error = true;
        }
        if (!doubleQuoteStack.isEmpty()) {
            ErrorMessage = "Extra Double Quote";
            errorChar = doubleQuotePosition.pop();
            errorLine = doubleQuotePosition.pop();
            theString = doubleQuoteLine.pop();
            doubleQuoteStack.pop();
            System.out.println(errorLine + ":" + errorChar + ": " + '"' + theString + '"' + " ERROR: " + ErrorMessage);
            error = true;
        }
        while (!singleQuoteStack.isEmpty()) {
            ErrorMessage = "Extra Single Quote";
            errorChar = singleQuotePosition.pop();
            errorLine = singleQuotePosition.pop();
            theString = singleQuoteLine.pop();
            singleQuoteStack.pop();
            System.out.println(errorLine + ":" + errorChar + ": " + '"' + theString + '"' + " ERROR: " + ErrorMessage);
            error = true;
        }
        while (!blockStack.isEmpty()) {
            ErrorMessage = "Missing closing Block Comment";
            errorChar = blockPosition.pop();
            errorLine = blockPosition.pop();
            theString = blockLine.pop();
            blockStack.pop();
            System.out.println(errorLine + ":" + errorChar + ": " + '"' + theString + '"' + " ERROR: " + ErrorMessage);
            error = true;
        }
        if (error) {
            System.exit(-1);
        }
        System.out.println("Success, there are no errors in your code");
        System.exit(0);
    }
}
