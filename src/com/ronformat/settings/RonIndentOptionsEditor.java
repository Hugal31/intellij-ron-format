package com.ronformat.settings;

import com.intellij.application.options.IndentOptionsEditor;
import com.intellij.psi.codeStyle.LanguageCodeStyleSettingsProvider;
import org.jetbrains.annotations.Nullable;

class RonIndentOptionsEditor extends IndentOptionsEditor {

    RonIndentOptionsEditor(@Nullable LanguageCodeStyleSettingsProvider provider) {
        super(provider);
    }

}
