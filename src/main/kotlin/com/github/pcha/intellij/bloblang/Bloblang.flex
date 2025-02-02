package com.github.pcha.intellij.bloblang;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.github.pcha.intellij.bloblang.psi.BloblangTypes;
import com.intellij.psi.TokenType;

%%

%class BloblangLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

WHITE_SPACE=[\ \t\f]
VAR_NAME = [a-zA-Z_] [a-zA-Z_0-9]*
QUOTED_STRING = "\"" ("\\\"" | [^"\"\n" ])* "\""
BOOLEAN = true | false
NUMBER = "-"? [0-9]+ ([.] [0-9]+)?
TRIPLE_QUOTE_STRING = "\"\"\"" [^"\"\"\""]* "\"\"\""
NULL = null

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
IF_KEYWORD = "if"
ELSE_KEYWORD = "else"
NOT_OPERATOR = "!"

MINUS_OPERATOR = "-"
PLUS_OPERATOR = "+"
DIVIDE_OPERATOR = "/"
MULTIPLY_OPERATOR = "*"
REST_OPERATOR = "%"
AND_OPERATOR = "&&"
OR_OPERATOR = "||"
EQUALS_OPERATOR = "=="
NOT_EQUALS_OPERATOR = "!="
GREATER_OR_EQUAL_OPERATOR = ">="
LESS_OR_EQUAL_OPERATOR = "<="
GREATER_OPERATOR = ">"
LESS_OPERATOR = "<"
PIPE_OPERATOR = "|"

IMPORT_KEYWORD = "import"
MAP_KEYWORD = "map"
MATCH_KEYWORD = "match"
SNAKE_CASE = [a-z0-9]+(_[a-z0-9]+)*
END_OF_LINE_COMMENT=#[^\n]*

ROOT_KEYWORD = "root"
THIS_KEYWORD = "this"

LET_KEYWORD = "let"

%state WAITING_QUOTED_STRING
%state WAITING_MAP_NAME
%state WAITING_MAPPING
%state WAITING_DELIMITEDPATTERN
%state WAITING_VAR_NAME
%state WAITING_ASSIGNMENT
%state WAITING_QUERY

%%

<YYINITIAL> {END_OF_LINE_COMMENT}                           { yybegin(YYINITIAL); return BloblangTypes.COMMENT; }

{IMPORT_KEYWORD}{WHITE_SPACE}+                              { return BloblangTypes.IMPORT_TERM; }
{MAP_KEYWORD}{WHITE_SPACE}+                                 { return BloblangTypes.MAP_TERM; }
{LET_KEYWORD}{WHITE_SPACE}+                                 { return BloblangTypes.LET_TERM; }
{IF_KEYWORD}{WHITE_SPACE}+                                  { return BloblangTypes.IF_TERM; }
{ELSE_KEYWORD}{WHITE_SPACE}+                                { return BloblangTypes.ELSE_TERM; }
{MATCH_KEYWORD}{WHITE_SPACE}+                               { return BloblangTypes.MATCH_TERM; }

{ROOT_KEYWORD}                                              { return BloblangTypes.ROOT; }
{THIS_KEYWORD}                                              { return BloblangTypes.THIS; }

{OPEN_PARENTHESIS}                                          { return BloblangTypes.OPEN_PARENTHESIS; }
{CLOSE_PARENTHESIS}                                         { return BloblangTypes.CLOSE_PARENTHESIS; }
{OPEN_BRACKET}                                              { return BloblangTypes.OPEN_BRACKET; }
{CLOSE_BRACKET}                                             { return BloblangTypes.CLOSE_BRACKET; }
{OPEN_BRACE}                                                { return BloblangTypes.OPEN_BRACE; }
{CLOSE_BRACE}                                               { return BloblangTypes.CLOSE_BRACE; }

{EQ}                                                        { return BloblangTypes.EQ; }
{NEWLINE}                                                   { return BloblangTypes.NEWLINE; }
{COMMA}                                                     { return BloblangTypes.COMMA; }
{DOT}                                                       { return BloblangTypes.DOT; }
{UNDERSCORE}                                                { return BloblangTypes.UNDERSCORE; }
{COLON}                                                     { return BloblangTypes.COLON; }

{NOT_OPERATOR}                                              { return BloblangTypes.NOT; }
{MINUS_OPERATOR}                                            { return BloblangTypes.MINUS; }
{PLUS_OPERATOR}                                             { return BloblangTypes.PLUS; }
{DIVIDE_OPERATOR}                                           { return BloblangTypes.DIVIDE; }
{MULTIPLY_OPERATOR}                                         { return BloblangTypes.MULTIPLY; }
{REST_OPERATOR}                                             { return BloblangTypes.REST; }
{AND_OPERATOR}                                              { return BloblangTypes.AND; }
{OR_OPERATOR}                                               { return BloblangTypes.OR; }
{EQUALS_OPERATOR}                                            { return BloblangTypes.EQUALS; }
{NOT_EQUALS_OPERATOR}                                       { return BloblangTypes.NOT_EQUALS; }
{GREATER_OR_EQUAL_OPERATOR}                                 { return BloblangTypes.GREATER_OR_EQUAL; }
{LESS_OR_EQUAL_OPERATOR}                                    { return BloblangTypes.LESS_OR_EQUAL; }
{GREATER_OPERATOR}                                          { return BloblangTypes.GREATER; }
{LESS_OPERATOR}                                             { return BloblangTypes.LESS; }
{PIPE_OPERATOR}                                             { return BloblangTypes.PIPE; }

{DOUBLE_ARROW}                                              { return BloblangTypes.DOUBLE_ARROW; }
{ARROW}                                                     { return BloblangTypes.ARROW; }

{DOLLAR}{VAR_NAME}                                          { return BloblangTypes.VARIABLE_LITERAL; }
{QUOTED_STRING}                                             { return BloblangTypes.QUOTED_STRING; }
{BOOLEAN}                                                   { return BloblangTypes.BOOLEAN; }
{NUMBER}                                                    { return BloblangTypes.NUMBER; }
{TRIPLE_QUOTE_STRING}                                       { return BloblangTypes.TRIPLE_QUOTE_STRING; }
{NULL}                                                      { return BloblangTypes.NULL; }
{SNAKE_CASE}{OPEN_PARENTHESIS}                              { return BloblangTypes.FUNCTION_CALL_OPEN; }
{VAR_NAME}                                                  { return BloblangTypes.VAR_NAME; }

({WHITE_SPACE})+                                            { yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }
[^]                                                         { return TokenType.BAD_CHARACTER; }