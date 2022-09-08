public class Assignment2 {
   
   /*
    * 
   Write a Java program that implements a lexical analyzer, lex, and a recursive-descent parser, parse, 
   and an error handling program, error, for the following EBNF description of a simple arithmetic expression language:

   <program> -> BEGIN <body> END
   <body> -> {<stmt>}+
   <stmt> -> COMPUTE <expr>
   <expr> -> <term> { (+ | -) <term>}*
   <term> -> <factor> { (* | /) <factor>}*
   <factor> -> <id> | integer-value | ( <expr> ) | <function>
   <id> -> A1 | A2 | A3
   <function> -> SQUARE ( <expr> ) | SQRT ( <expr> ) | ABS ( <expr>)

      Josh Brawner
    */

    static char BEGIN_CODE = 'B';
    static char END_CODE = 'E';
    static char END_OF_FILE = 'Z';
    static char COMPUTE_CODE = 'C';
    static char EXPR = 'E';
    
    static char FACTOR = 'F';
    static char FUNCTION = 'A';
    static char ID = 'I';

    static char PLUS = '+';
    static char MINUS = '-';
    static char DIVIDE =  '/';
    static char MULTIPLY = '*';
    static char RPAR = ')';
    static char LPAR = '(';



    static String inString = "BEGIN COMPUTE A1 + A2 * ABS ( A3 * A2 + A1 ) COMPUTE A1 + A1 END EOF";
    static String remainingString = inString;
    static char nextToken;
 
     public static void main (String args []) {
    
       lex();
       parse(); 
    
    }
    
    
     public static void lex() {
    
       System.out.print("Enter <lex> - lexeme = ");  
       String lexeme = "";
    
       int start = 0;
    
       while (start < remainingString.length() && remainingString.charAt(start)==' ')
          start++;
    
       int end = start+1;
        
       while (end < remainingString.length() && remainingString.charAt(end)!=' ')
          end++;
    
       if (start >= remainingString.length()) {
          lexeme = "";
          remainingString = "";
          System.out.print("EOF");
       }  
       else {
          lexeme = remainingString.substring(start,end);
          remainingString = remainingString.substring(end, remainingString.length());
       }
               
       if (lexeme.compareTo("BEGIN")==0) nextToken = BEGIN_CODE;
       else if (lexeme.compareTo("END")==0) nextToken = END_CODE;
       else if (lexeme.compareTo("EOF")==0) nextToken = END_OF_FILE;
       else if (lexeme.compareTo("COMPUTE")==0) nextToken = COMPUTE_CODE;
       else if (lexeme.compareTo("A1")==0 || lexeme.compareTo("A2")==0 || lexeme.compareTo("A3")==0) nextToken = ID;
       else if (lexeme.compareTo("+")==0) nextToken = PLUS;
       else if (lexeme.compareTo("-")==0) nextToken = MINUS;
       else if (lexeme.compareTo("/")==0) nextToken = DIVIDE;
       else if (lexeme.compareTo("*")==0) nextToken = MULTIPLY;
       else if (lexeme.compareTo("(")==0) nextToken = LPAR;
       else if (lexeme.compareTo(")")==0) nextToken = RPAR;
       else if (lexeme.compareTo("ABS")==0 || lexeme.compareTo("SQRT")==0 || lexeme.compareTo("SQUARE")==0) nextToken = FUNCTION;

    
       System.out.print(lexeme+"  token = ");
       System.out.println(nextToken);
    
    }
 
     public static void parse() {
    
       
       System.out.println("Enter <parse>");  
       program();
       System.out.println("Exit <parse>");  
    
    
    } 
     
     public static void program() {
    
       System.out.println("Enter <program>");  
       if (nextToken == BEGIN_CODE)
       {
          lex(); 
          body();
       }
       else error();
       if (nextToken == END_CODE)
          lex();
       else error();
       System.out.println("Exit <program>");  
    
    
    } 
     
     public static void body() {

       System.out.println("Enter <body>");
       stmt();
       while(nextToken == COMPUTE_CODE)
       {
            stmt();
       }
       
         
       System.out.println("Exit <body>");  
    
    
    } 
     
     public static void error() {
       System.out.println("Enter <error>"); 
       System.out.println("Exit <error>"); 
     
    }
     
    public static void stmt()
    {
      System.out.println("Enter <stmt>");
        //System.out.println(nextToken);
        lex();
        while(nextToken == ID)
        {  
            expr();
        }

        System.out.println("Exit <stmt>");
    }

    public static void expr()
    {
        System.out.println("Enter <expr>");

        term();
        while(nextToken == '+' || nextToken == '-')
        {
           lex();
           term();
         }
         

        System.out.println("Exit <expr>");
    }

    public static void term()
    {
        System.out.println("Enter <term>");
        factor();
        
        while(nextToken == '*' || nextToken == '/')
        {
            lex();
            factor();
            
         }
      
        System.out.println("Exit <term>");
    }

    public static void factor()
    {
        System.out.println("Enter <factor>");

        if (nextToken == ID)
        {
           lex(); 
        }
        else
        {
            if (nextToken == FUNCTION)
            {
               function();
            }
            if (nextToken == LPAR) 
            {
               lex();
               expr();
            }
            if (nextToken == RPAR)
            {
               lex();
            } 
            
         }
         

         
        System.out.println("Exit <factor>");
    }

    public static void function()
    {
        System.out.println("Enter <function>");

        if (nextToken == FUNCTION)
        {
         lex();
        }
        
         if (nextToken == LPAR) 
            {
               lex();
               expr();
            }
            if (nextToken == RPAR)
            {
               lex();
            }

            else
            {
            error();
            } 
         
        System.out.println("Exit <function>");
    }
 }
