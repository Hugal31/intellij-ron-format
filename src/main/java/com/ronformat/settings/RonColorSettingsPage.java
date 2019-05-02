package com.ronformat.settings;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import com.ronformat.RonIcons;
import com.ronformat.RonSyntaxHighlighter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Map;

public class RonColorSettingsPage implements ColorSettingsPage {

    private static final AttributesDescriptor[] DESCRIPTORS = new AttributesDescriptor[]{
            new AttributesDescriptor("Block comment", RonSyntaxHighlighter.BLOCK_COMMENT),
            new AttributesDescriptor("Line comment", RonSyntaxHighlighter.LINE_COMMENT),
            new AttributesDescriptor("Char", RonSyntaxHighlighter.CHAR),
            new AttributesDescriptor("Identifier", RonSyntaxHighlighter.IDENTIFIER),
            new AttributesDescriptor("Number", RonSyntaxHighlighter.NUMBER),
            new AttributesDescriptor("String", RonSyntaxHighlighter.STRING),
    };

    @Nullable
    @Override
    public Icon getIcon() {
        return RonIcons.FILE;
    }

    @NotNull
    @Override
    public SyntaxHighlighter getHighlighter() {
        return new RonSyntaxHighlighter();
    }

    @NotNull
    @Override
    public String getDemoText() {
        return "MyStruct(\n" +
                "    an_int: 42,\n" +
                "    a_float: 42434.24,\n" +
                "    a_string: \"Hello world!\",\n" +
                "    an_array: [\n" +
                "        A,\n" +
                "        B(4),\n" +
                "        C(\n" +
                "            value: 8,\n" +
                "        ),\n" +
                "    ],\n" +
                "    a_tuple: (3, 4),\n" +
                "    an_option: Some(1),\n" +
                "    a_dictionary: {\n" +
                "        'a': \"The letter A\",\n" +
                "    },\n" +
                "    unit: (),\n" +
                ")";
    }

    @Nullable
    @Override
    public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
        return null;
    }

    @NotNull
    @Override
    public AttributesDescriptor[] getAttributeDescriptors() {
        return DESCRIPTORS;
    }

    @NotNull
    @Override
    public ColorDescriptor[] getColorDescriptors() {
        return ColorDescriptor.EMPTY_ARRAY;
    }

    @NotNull
    @Override
    public String getDisplayName() {
        return "RON";
    }

}
