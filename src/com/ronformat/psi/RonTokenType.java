package com.ronformat.psi;

import com.ronformat.RonLanguage;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class RonTokenType extends IElementType {

    public RonTokenType(@NotNull @NonNls String debugName) {
        super(debugName, RonLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "RonTokenType." + super.toString();
    }
}
