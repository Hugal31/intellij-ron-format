package com.ronformat.psi;

import com.ronformat.RonLanguage;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class RonElementType extends IElementType {

    public RonElementType(@NotNull @NonNls String debugName) {
        super(debugName, RonLanguage.INSTANCE);
    }

}
