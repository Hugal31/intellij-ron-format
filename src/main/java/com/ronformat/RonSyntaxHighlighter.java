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

import java.util.HashMap;
import java.util.Map;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class RonSyntaxHighlighter extends SyntaxHighlighterBase  {

    public static final TextAttributesKey COMMA =
            createTextAttributesKey("RON_COMMA", DefaultLanguageHighlighterColors.COMMA);

    public static final TextAttributesKey BLOCK_COMMENT =
            createTextAttributesKey("RON_BLOCK_COMMENT", DefaultLanguageHighlighterColors.BLOCK_COMMENT);

    public static final TextAttributesKey LINE_COMMENT =
            createTextAttributesKey("RON_LINE_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);

    public static final TextAttributesKey IDENTIFIER =
            createTextAttributesKey("RON_IDENTIFIER", DefaultLanguageHighlighterColors.IDENTIFIER);

    public static final TextAttributesKey NUMBER =
            createTextAttributesKey("RON_NUMBER", DefaultLanguageHighlighterColors.NUMBER);

    public static final TextAttributesKey STRING =
            createTextAttributesKey("RON_STRING", DefaultLanguageHighlighterColors.STRING);

    public static final TextAttributesKey CHAR =
            createTextAttributesKey("RON_CHAR", DefaultLanguageHighlighterColors.STRING);

    public static final TextAttributesKey BAD_CHARACTER =
            createTextAttributesKey("RON_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER);

    private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];

    private static final Map<IElementType, TextAttributesKey[]> elementToKeys = new HashMap<>();
    static {
        elementToKeys.put(TokenType.BAD_CHARACTER, new TextAttributesKey[]{BAD_CHARACTER});
        elementToKeys.put(RonTypes.BLOCK_COMMENT, new TextAttributesKey[]{BLOCK_COMMENT});
        elementToKeys.put(RonTypes.CHAR, new TextAttributesKey[]{CHAR});
        elementToKeys.put(RonTypes.IDENTIFIER, new TextAttributesKey[]{IDENTIFIER});
        elementToKeys.put(RonTypes.NUMBER, new TextAttributesKey[]{NUMBER});
        elementToKeys.put(RonTypes.STRING_LITERAL, new TextAttributesKey[]{STRING});
        elementToKeys.put(RonTypes.END_OF_LINE_COMMENT, new TextAttributesKey[]{LINE_COMMENT});
        elementToKeys.put(RonTypes.SEPARATOR, new TextAttributesKey[]{COMMA});
    }

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new RonLexerAdapter();
    }

    @NotNull
    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType iElementType) {
        return elementToKeys.getOrDefault(iElementType, EMPTY_KEYS);
    }
}
