package com.ronformat;

import com.intellij.lexer.FlexAdapter;

public class RonLexerAdapter extends FlexAdapter {

    public RonLexerAdapter() {
        super(new RonLexer(null));
    }

}
