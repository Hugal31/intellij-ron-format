package com.ronformat;

import com.intellij.lexer.FlexAdapter;
import com.ronformat.lexer.RonLexer;

class RonLexerAdapter extends FlexAdapter {

    RonLexerAdapter() {
        super(new RonLexer(null));
    }

}
