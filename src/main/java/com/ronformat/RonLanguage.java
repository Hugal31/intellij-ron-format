package com.ronformat;

import com.intellij.lang.Language;

public class RonLanguage extends Language {

    public static final RonLanguage INSTANCE = new RonLanguage();

    private RonLanguage() {
        super("RON");
    }
}
