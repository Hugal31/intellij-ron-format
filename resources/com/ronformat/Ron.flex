package com.ronformat;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.TokenType;
import com.ronformat.psi.RonTypes;

%%

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
// Numbers
NUMBER_CHAR = [0-9]
// Separators
ASSIGN = ":"
SEPARATOR = ","

%%

<YYINITIAL> {
    {END_OF_LINE_COMMENT}                                     { yybegin(YYINITIAL); return RonTypes.COMMENT; }
    {BLOCK_COMMENT}                                           { yybegin(YYINITIAL); return RonTypes.BLOCK_COMMENT; }

    {IDENTIFIER}                                              { yybegin(YYINITIAL); return RonTypes.IDENTIFIER; }

    "("                                                       { yybegin(YYINITIAL); return RonTypes.STRUCT_BEGIN; }
    ")"                                                       { yybegin(YYINITIAL); return RonTypes.STRUCT_END; }

    // TODO Handle escape
    {STRING_QUOTE}{STRING_CHAR}*{STRING_QUOTE}                { yybegin(YYINITIAL); return RonTypes.STRING_LITERAL; }
    {NUMBER_CHAR}+                                            { yybegin(YYINITIAL); return RonTypes.NUMBER; }
    {SEPARATOR}                                               { yybegin(YYINITIAL); return RonTypes.SEPARATOR; }
    {ASSIGN}                                                  { yybegin(YYINITIAL); return RonTypes.ASSIGN; }
}

({CRLF}|{WHITE_SPACE})+                                       { yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }

[^]                                                         { return TokenType.BAD_CHARACTER; }
