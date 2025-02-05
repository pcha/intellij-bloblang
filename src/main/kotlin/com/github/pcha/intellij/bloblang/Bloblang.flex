package com.github.pcha.intellij.bloblang;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.github.pcha.intellij.bloblang.psi.BloblangTypes;
import com.intellij.psi.TokenType;

%%

// Declaración de estado para manejo de strings con triple comillas
%state STRING_CONTENT_STATE
%state STRING_ESCAPE_STATE
%state SINGLE_LINE_STRING_STATE
%state MULTILINE_STRING_STATE

%class BloblangLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{ return;
%eof}

// Definiciones de macros
WHITE_SPACE = [ \t\f]
VAR_NAME = [a-zA-Z_][a-zA-Z_0-9]*
BOOLEAN = true | false
NUMBER = "-"?[0-9]+(\\.[0-9]+)?
QUOTE = \"
TRIPLE_QUOTE = \"\"\"
ESCAPE = \\
VALID_STRING_CONTENT = [^\"\\\n]+
VALID_ESCAPED_CHAR = [nt\\\"]
ALL_EXCEPT_TRIPLE_QUOTE = ([^\"]|\"[^\"]|\"\"[^\"])*

// Palabras clave
NULL = "null"
IMPORT_KEYWORD = "import"
MAP_KEYWORD = "map"
MATCH_KEYWORD = "match"
ROOT_KEYWORD = "root"
THIS_KEYWORD = "this"
LET_KEYWORD = "let"
IF_KEYWORD = "if"
ELSE_KEYWORD = "else"

// Operadores
NOT_EQUALS_OPERATOR = "!="
NOT_OPERATOR = "!"
MINUS_OPERATOR = "-"
PLUS_OPERATOR = "+"
DIVIDE_OPERATOR = "/"
MULTIPLY_OPERATOR = "*"
REST_OPERATOR = "%"
AND_OPERATOR = "&&"
OR_OPERATOR = "||"
EQUALS_OPERATOR = "=="
GREATER_OR_EQUAL_OPERATOR = ">="
LESS_OR_EQUAL_OPERATOR = "<="
GREATER_OPERATOR = ">"
LESS_OPERATOR = "<"
PIPE_OPERATOR = "|"

// Símbolos
OPEN_PARENTHESIS = "("
CLOSE_PARENTHESIS = ")"
OPEN_BRACKET = "["
CLOSE_BRACKET = "]"
OPEN_BRACE = "{"
CLOSE_BRACE = "}"
DOLLAR = "$"
EQ = "="
COLON = ":"
NEWLINE = \n
COMMA = ","
DOT = "."
UNDERSCORE = "_"
DOUBLE_ARROW = "=>"
ARROW = "->"

// Identificadores especiales
SNAKE_CASE = [a-z0-9]+(_[a-z0-9]+)*
END_OF_LINE_COMMENT = #[^\n]*

%%

// Reglas del lexer

<YYINITIAL> {END_OF_LINE_COMMENT} { yybegin(YYINITIAL); return BloblangTypes.COMMENT; }

<YYINITIAL> {
    // Comillas de apertura para un string de una línea
    {QUOTE} { yybegin(SINGLE_LINE_STRING_STATE); return BloblangTypes.SINGLE_LINE_STRING_QUOTE; }

    // Comillas de apertura para un string multi-línea
    {TRIPLE_QUOTE} { yybegin(MULTILINE_STRING_STATE); return BloblangTypes.MULTILINE_STRING_QUOTE; }
}
// String de una línea que soporta escapes
<SINGLE_LINE_STRING_STATE> {
    {ESCAPE} { yybegin(STRING_ESCAPE_STATE); return BloblangTypes.STRING_ESCAPE; }
    {VALID_STRING_CONTENT} { return BloblangTypes.STRING_CONTENT; }
    {QUOTE} { yybegin(YYINITIAL); return BloblangTypes.SINGLE_LINE_STRING_QUOTE; }
    {NEWLINE} { yybegin(YYINITIAL); return TokenType.BAD_CHARACTER; }
}

// String multi-línea sin escapes
<MULTILINE_STRING_STATE> {
    {TRIPLE_QUOTE} { yybegin(YYINITIAL); return BloblangTypes.MULTILINE_STRING_QUOTE; }
    {ALL_EXCEPT_TRIPLE_QUOTE} { return BloblangTypes.STRING_CONTENT; }
}

// Manejo de escapes dentro del string de una línea
<STRING_ESCAPE_STATE> {
    // Captura solo los caracteres válidos después de una barra invertida
    {VALID_ESCAPED_CHAR} { yybegin(SINGLE_LINE_STRING_STATE); return BloblangTypes.ESCAPED_CHAR; }
    [^] { yybegin(YYINITIAL); return TokenType.BAD_CHARACTER; }  // Error si el escape es inválido
}

<YYINITIAL> {
    // Palabras clave seguidas de espacios
    {IMPORT_KEYWORD}{WHITE_SPACE}+ { return BloblangTypes.IMPORT_TERM; }
    {MAP_KEYWORD}{WHITE_SPACE}+    { return BloblangTypes.MAP_TERM; }
    {LET_KEYWORD}{WHITE_SPACE}+    { return BloblangTypes.LET_TERM; }
    {IF_KEYWORD}{WHITE_SPACE}+     { return BloblangTypes.IF_TERM; }
    {ELSE_KEYWORD}{WHITE_SPACE}+   { return BloblangTypes.ELSE_TERM; }
    {MATCH_KEYWORD}{WHITE_SPACE}+  { return BloblangTypes.MATCH_TERM; }

    // Keywords sin espacios
    {ROOT_KEYWORD} { return BloblangTypes.ROOT; }
    {THIS_KEYWORD} { return BloblangTypes.THIS; }
    {NULL} { return BloblangTypes.NULL; }


    // Símbolos
    {OPEN_PARENTHESIS}  { return BloblangTypes.OPEN_PARENTHESIS; }
    {CLOSE_PARENTHESIS} { return BloblangTypes.CLOSE_PARENTHESIS; }
    {OPEN_BRACKET}      { return BloblangTypes.OPEN_BRACKET; }
    {CLOSE_BRACKET}     { return BloblangTypes.CLOSE_BRACKET; }
    {OPEN_BRACE}        { return BloblangTypes.OPEN_BRACE; }
    {CLOSE_BRACE}       { return BloblangTypes.CLOSE_BRACE; }

    {EQ}                { return BloblangTypes.EQ; }
    {NEWLINE}           { return BloblangTypes.NEWLINE; }
    {COMMA}             { return BloblangTypes.COMMA; }
    {DOT}               { return BloblangTypes.DOT; }
    {UNDERSCORE}        { return BloblangTypes.UNDERSCORE; }
    {COLON}             { return BloblangTypes.COLON; }

    // Operadores
    {NOT_EQUALS_OPERATOR}       { return BloblangTypes.NOT_EQUALS; }
    {NOT_OPERATOR}              { return BloblangTypes.NOT; }
    {MINUS_OPERATOR}            { return BloblangTypes.MINUS; }
    {PLUS_OPERATOR}             { return BloblangTypes.PLUS; }
    {DIVIDE_OPERATOR}           { return BloblangTypes.DIVIDE; }
    {MULTIPLY_OPERATOR}         { return BloblangTypes.MULTIPLY; }
    {REST_OPERATOR}             { return BloblangTypes.REST; }
    {AND_OPERATOR}              { return BloblangTypes.AND; }
    {OR_OPERATOR}               { return BloblangTypes.OR; }
    {EQUALS_OPERATOR}           { return BloblangTypes.EQUALS; }
    {GREATER_OR_EQUAL_OPERATOR} { return BloblangTypes.GREATER_OR_EQUAL; }
    {LESS_OR_EQUAL_OPERATOR}    { return BloblangTypes.LESS_OR_EQUAL; }
    {GREATER_OPERATOR}          { return BloblangTypes.GREATER; }
    {LESS_OPERATOR}             { return BloblangTypes.LESS; }
    {PIPE_OPERATOR}             { return BloblangTypes.PIPE; }

    {DOUBLE_ARROW}              { return BloblangTypes.DOUBLE_ARROW; }
    {ARROW}                     { return BloblangTypes.ARROW; }

    {DOLLAR}{VAR_NAME}          { return BloblangTypes.VARIABLE_LITERAL; }
    {BOOLEAN}                   { return BloblangTypes.BOOLEAN; }
    {NUMBER}                    { return BloblangTypes.NUMBER; }


    // Función: Si un SNAKE_CASE seguido de un OPEN_PARENTHESIS es una llamada de función
    {SNAKE_CASE}{OPEN_PARENTHESIS} { return BloblangTypes.FUNCTION_CALL_OPEN; }
    {VAR_NAME}                     { return BloblangTypes.VAR_NAME; }

    // Manejo de espacios en blanco
    ({WHITE_SPACE})+               { yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }
    [^]                            { return TokenType.BAD_CHARACTER; }
}
