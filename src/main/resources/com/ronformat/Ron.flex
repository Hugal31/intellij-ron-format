package com.ronformat.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.TokenType;
import com.ronformat.psi.RonTypes;

%%

%public
%class RonLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{ return;
%eof}

// White spaces
CRLF=\R
WHITE_SPACE=[\ \n\t\f]
// Comments
END_OF_LINE_COMMENT="//"[^\r\n]*
BLOCK_COMMENT="/*" ~"*/"
// Identifiers
IDENTIFIER=[:jletter:][:jletterdigit:]*
// Strings
STRING_QUOTE = "\""
STRING_CHAR = [^\"]
CHAR_QUOTE = "'"
CHAR_CHAR = [^']
// Numbers
DECIMAL_DIGIT_NO_UNDERSCORE = [0-9]
DECIMA_DIGIT = ({DECIMAL_DIGIT_NO_UNDERSCORE}|"_")
BINARY_DIGIT_NO_UNDERSCORE = [01]
BINARY_DIGIT = ({BINARY_DIGIT_NO_UNDERSCORE} | "_")
OCTAL_DIGIT_NO_UNDERSCORE = [0-7]
OCTAL_DIGIT = ({OCTAL_DIGIT_NO_UNDERSCORE} | "_")
HEXA_DIGIT_NO_UNDERSCORE = [0-9A-Fa-f]
HEXA_DIGIT = ({HEXA_DIGIT_NO_UNDERSCORE} | "_")
OCTAL_INTEGER = "0o" {OCTAL_DIGIT_NO_UNDERSCORE}{OCTAL_DIGIT}*
BINARY_INTEGER = "0b" {BINARY_DIGIT_NO_UNDERSCORE}{BINARY_DIGIT}*
HEXA_INTEGER = "0x" {HEXA_DIGIT_NO_UNDERSCORE}{HEXA_DIGIT}*
DECIMAL_INTEGER = {DECIMAL_DIGIT_NO_UNDERSCORE}{DECIMA_DIGIT}*
INTEGER = ("+" | "-")? ({BINARY_INTEGER}|{OCTAL_INTEGER}|{HEXA_INTEGER}|{DECIMAL_INTEGER})
FLOAT = ("+" | "-")? {DECIMAL_INTEGER}? "." {DECIMAL_INTEGER}?
// Separators
ASSIGN = ":"
SEPARATOR = ","

%%

<YYINITIAL> {
    {END_OF_LINE_COMMENT}                                     { yybegin(YYINITIAL); return RonTypes.COMMENT; }
    {BLOCK_COMMENT}                                           { yybegin(YYINITIAL); return RonTypes.BLOCK_COMMENT; }

    {IDENTIFIER}                                              { yybegin(YYINITIAL); return RonTypes.IDENTIFIER; }

    "#!"                                                      { yybegin(YYINITIAL); return RonTypes.FILE_ATTRIBUTES_BEGIN; }

    "("                                                       { yybegin(YYINITIAL); return RonTypes.STRUCT_BEGIN; }
    ")"                                                       { yybegin(YYINITIAL); return RonTypes.STRUCT_END; }

    "["                                                       { yybegin(YYINITIAL); return RonTypes.ARRAY_BEGIN; }
    "]"                                                       { yybegin(YYINITIAL); return RonTypes.ARRAY_END; }

    "{"                                                       { yybegin(YYINITIAL); return RonTypes.DICTIONARY_BEGIN; }
    "}"                                                       { yybegin(YYINITIAL); return RonTypes.DICTIONARY_END; }

    // TODO Handle escape
    {STRING_QUOTE}{STRING_CHAR}*{STRING_QUOTE}                { yybegin(YYINITIAL); return RonTypes.STRING_LITERAL; }
    {CHAR_QUOTE}{CHAR_CHAR}*{CHAR_QUOTE}                      { yybegin(YYINITIAL); return RonTypes.CHAR; }
    {FLOAT}|{INTEGER}                                         { yybegin(YYINITIAL); return RonTypes.NUMBER; }
    {SEPARATOR}                                               { yybegin(YYINITIAL); return RonTypes.SEPARATOR; }
    {ASSIGN}                                                  { yybegin(YYINITIAL); return RonTypes.ASSIGN; }
}

({CRLF}|{WHITE_SPACE})+                                       { yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }

[^]                                                         { return TokenType.BAD_CHARACTER; }
