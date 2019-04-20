package com.ronformat.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import com.ronformat.RonFileType;
import com.ronformat.RonLanguage;
import org.jetbrains.annotations.NotNull;

public class RonFile extends PsiFileBase {

    public RonFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, RonLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return RonFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "Ron File";
    }
}
