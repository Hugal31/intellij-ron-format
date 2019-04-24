package com.ronformat;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.ronformat.psi.RonTypes;
import org.jetbrains.annotations.NotNull;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class RonSyntaxHighlighter extends SyntaxHighlighterBase  {

    private static final TextAttributesKey COMMA =
            createTextAttributesKey("RON_COMMA", DefaultLanguageHighlighterColors.COMMA);

    private static final TextAttributesKey BLOCK_COMMENT =
            createTextAttributesKey("RON_BLOCK_COMMENT", DefaultLanguageHighlighterColors.BLOCK_COMMENT);

    private static final TextAttributesKey LINE_COMMENT =
            createTextAttributesKey("RON_LINE_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);

    private static final TextAttributesKey IDENTIFIER =
            createTextAttributesKey("RON_IDENTIFIER", DefaultLanguageHighlighterColors.IDENTIFIER);

    private static final TextAttributesKey NUMBER =
            createTextAttributesKey("RON_NUMBER", DefaultLanguageHighlighterColors.NUMBER);

    private static final TextAttributesKey STRING =
            createTextAttributesKey("RON_STRING", DefaultLanguageHighlighterColors.STRING);

    private static final TextAttributesKey CHAR =
            createTextAttributesKey("RON_CHAR", DefaultLanguageHighlighterColors.STRING);

    private static final TextAttributesKey BAD_CHARACTER =
            createTextAttributesKey("RON_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER);

    private static final TextAttributesKey[] BAD_CHAR_KEYS = new TextAttributesKey[]{BAD_CHARACTER};
    private static final TextAttributesKey[] BLOCK_COMMENT_KEYS = new TextAttributesKey[]{BLOCK_COMMENT};
    private static final TextAttributesKey[] COMMA_KEYS = new TextAttributesKey[]{COMMA};
    private static final TextAttributesKey[] IDENTIFIERS_KEYS = new TextAttributesKey[]{IDENTIFIER};
    private static final TextAttributesKey[] LINE_COMMENT_KEYS = new TextAttributesKey[]{LINE_COMMENT};
    private static final TextAttributesKey[] NUMBER_KEYS = new TextAttributesKey[]{NUMBER};
    private static final TextAttributesKey[] STRING_KEYS = new TextAttributesKey[]{STRING};
    private static final TextAttributesKey[] CHAR_KEYS = new TextAttributesKey[]{CHAR};
    private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new RonLexerAdapter();
    }

    @NotNull
    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType iElementType) {
        if (iElementType.equals(RonTypes.SEPARATOR)) {
            return COMMA_KEYS;
        } else if (iElementType.equals(RonTypes.BLOCK_COMMENT)) {
            return BLOCK_COMMENT_KEYS;
        } else if (iElementType.equals(RonTypes.COMMENT)) {
            return LINE_COMMENT_KEYS;
        } else if (iElementType.equals(RonTypes.IDENTIFIER)) {
            return IDENTIFIERS_KEYS;
        } else if (iElementType.equals(RonTypes.NUMBER)) {
            return NUMBER_KEYS;
        } else if (iElementType.equals(RonTypes.CHAR)) {
            return CHAR_KEYS;
        } else  if (iElementType.equals(RonTypes.STRING_LITERAL)) {
            return STRING_KEYS;
        } else if (iElementType.equals(TokenType.BAD_CHARACTER)) {
            return BAD_CHAR_KEYS;
        } else {
            return EMPTY_KEYS;
        }
    }
}
