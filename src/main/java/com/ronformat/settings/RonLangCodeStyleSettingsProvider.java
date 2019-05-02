package com.ronformat.settings;

import com.intellij.application.options.IndentOptionsEditor;
import com.intellij.lang.Language;
import com.intellij.psi.codeStyle.*;
import com.ronformat.RonLanguage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RonLangCodeStyleSettingsProvider extends LanguageCodeStyleSettingsProvider {

    @NotNull
    @Override
    public Language getLanguage() {
        return RonLanguage.INSTANCE;
    }

    @Nullable
    @Override
    public IndentOptionsEditor getIndentOptionsEditor() {
        return new RonIndentOptionsEditor(this);
    }

    @Nullable
    @Override
    public String getCodeSample(@NotNull SettingsType settingsType) {
        return "#![enable(implicit_some)]\n" +
                "MyStruct( // This is a comment\n" +
                "  an_int: 32,\n" +
                "  a_string: \"A string\"," +
                "  a_dictionarray: { /* A block comment*/\n" +
                "    MyEnumA: (1, 2),\n" +
                "    MyEnumB(4): (3, 4)," +
                "  },\n" +
                ")\n";
    }
}
