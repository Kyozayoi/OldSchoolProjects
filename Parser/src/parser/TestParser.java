import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.util.Stack;
import java.util.Scanner;
public class TestParser
{
       /*Scanner player = new Scanner(System.in);
        String sourceCode = player.nextLine();*/
    public static void main(String[] Args) throws Exception
    {
        int lparenthesis = 0;
        int rparenthesis = 0;
        int brackets = 0;
        int curlybraces = 0;
        int doublequote = 0;
        int singlequote = 0;
        int comment = 0;
        boolean blockComment = false;
        boolean singleComment = false;
        boolean hasParenthesis = false;
        boolean hasBrackets = false;
        boolean hasCurlyBraces = false;
        boolean hasDoubleQuotes = false;
        boolean hasSingleQuotes = false;
        int errorLine = 0;
        int errorChar = 0;
        String ErrorMessage = "No Error";
        //Might need to add a Scanner to read FilePaths.
        //String pathname = scanner(user input)
        //FileREad = new FileReader(pathname)
        FileReader Iread = new FileReader("C:/Users/Anthony/Documents/NetBeansProjects/Parser/src/parser/hw5test.txt");
        BufferedReader IRead = new BufferedReader(Iread);
        Stack MyFile = new Stack();
        Stack ReFile = new Stack();
        String line = IRead.readLine();
        while (line != null)
        {
            MyFile.push(line);
            line = IRead.readLine();
        }
        while(!MyFile.empty())
        {
            ReFile.push(MyFile.pop());
        }    
        while(!ReFile.empty())
        {
            String theString = (String)ReFile.pop();
            theString = theString.trim();
            if(theString.contains("/*"))
            {
                blockComment = true;
            }
            if(theString.contains("*/"))
            {
                blockComment = false;
            }
            if(theString.contains("//"))//Need to fix this for things like if(x,y) //(bs)
            {
                singleComment = true;
            }
            for(int i = 0; i < theString.length() - 1; i++)
            {
                if(!blockComment || !singleComment)
                {
    if(theString.charAt(i) == '(')
    {
        /*if(!hasParenthesis)
        {
            ErrorMessage = "Missing Closing Parenthesis";
            System.out.println(errorLine + ":" + errorChar + ":" + '"' + theString + '"' + " Error: " + ErrorMessage);
        }
        else
        {*/
            lparenthesis++;
            hasParenthesis = true;
    }
    if(theString.charAt(i) == ')')
    {
        rparenthesis++;
        if(!hasParenthesis && (rparenthesis > lparenthesis))
        {
            ErrorMessage = "Missing Opening Parenthesis";
            System.out.println(errorLine + ":" + errorChar + ":" + '"' + theString + '"' + " Error: " + ErrorMessage);
            //System.exit(1);
        }
        else
        {
            hasParenthesis = false;
        }
    }
                }
                errorChar++;
            }
            if(lparenthesis > rparenthesis)
            {
                ErrorMessage = "Missing Closing Parenthesis";
                System.out.println(errorLine + ":" + errorChar + ":" + '"' + theString + '"' + " Error: " + ErrorMessage);
            }
            errorLine++;
            singleComment = false;
            errorChar = 0;
        }
        System.out.println(errorLine + ":" + errorChar + ":" + errorLine + " Error: " + ErrorMessage);
        //System.exit(0);
    }
}

        
        


    
