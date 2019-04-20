package com.ronformat;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class RonFileType extends LanguageFileType {

    public static final RonFileType INSTANCE = new RonFileType();

    private RonFileType() {
        super(RonLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "RON";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Rust Object Notation file";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "ron";
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return null;
    }
}
