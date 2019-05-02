package com.ronformat.settings;

import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CustomCodeStyleSettings;

class RonCodeStyleSettings extends CustomCodeStyleSettings {

    RonCodeStyleSettings(CodeStyleSettings container) {
        super(RonCodeStyleSettings.class.getSimpleName(), container);
    }

}
