package com.ronformat;

import com.intellij.lang.ASTNode;
import com.intellij.lang.folding.FoldingBuilderEx;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.ronformat.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class RonFoldingBuilder extends FoldingBuilderEx {

    private class FoldingBuilderVisitor extends RonVisitor {

        private List<FoldingDescriptor> descriptors = new ArrayList<>();

        List<FoldingDescriptor> getDescriptors() {
            return descriptors;
        }

        @Override
        public void visitArray(@NotNull RonArray array) {
            ASTNode begin = array.getNode().findChildByType(RonTypes.ARRAY_BEGIN);
            ASTNode end = array.getNode().findChildByType(RonTypes.ARRAY_END);

            RonArrayBody body = array.getArrayBody();
            if (begin != null && end != null)
                descriptors.add(new FoldingDescriptor(body,
                        new TextRange(begin.getTextRange().getEndOffset(), end.getTextRange().getStartOffset())));

            body.acceptChildren(this);
        }

        @Override
        public void visitArrayBody(@NotNull RonArrayBody body) {
            body.acceptChildren(this);
        }

        @Override
        public void visitDictionary(@NotNull RonDictionary dictionary) {
            ASTNode begin = dictionary.getNode().findChildByType(RonTypes.DICTIONARY_BEGIN);
            ASTNode end = dictionary.getNode().findChildByType(RonTypes.DICTIONARY_END);

            RonDictionaryBody body = dictionary.getDictionaryBody();
            if (begin != null && end != null)
                descriptors.add(new FoldingDescriptor(body,
                        new TextRange(begin.getTextRange().getEndOffset(), end.getTextRange().getStartOffset())));

            body.acceptChildren(this);
        }

        @Override
        public void visitDictionaryBody(@NotNull RonDictionaryBody body) {
            body.acceptChildren(this);
        }

        @Override
        public void visitDictionaryField(@NotNull RonDictionaryField field) {
            for (final RonValue value: field.getValueList())
                visitValue(value);
        }

        @Override
        public void visitEnum(@NotNull RonEnum enu) {
            RonTuple tuple = enu.getTuple();

            if (tuple != null)
                visitTuple(tuple);
        }

        @Override
        public void visitField(@NotNull RonField field) {
            visitValue(field.getValue());
        }

        @Override
        public void visitValue(@NotNull RonValue value) {
            value.acceptChildren(this);
        }

        @Override
        public void visitStruct(@NotNull RonStruct struct) {
            ASTNode begin = struct.getNode().findChildByType(RonTypes.STRUCT_BEGIN);
            ASTNode end = struct.getNode().findChildByType(RonTypes.STRUCT_END);

            RonStructBody body = struct.getStructBody();

            if (begin != null && end != null)
                descriptors.add(new FoldingDescriptor(body,
                        new TextRange(begin.getTextRange().getEndOffset(), end.getTextRange().getStartOffset())));

            body.acceptChildren(this);
        }

        @Override
        public void visitStructBody(@NotNull RonStructBody body) {
            body.acceptChildren(this);
        }

        @Override
        public void visitTuple(@NotNull RonTuple tuple) {
            ASTNode begin = tuple.getNode().findChildByType(RonTypes.STRUCT_BEGIN);
            ASTNode end = tuple.getNode().findChildByType(RonTypes.STRUCT_END);

            RonTupleBody body = tuple.getTupleBody();

            if (begin != null && end != null)
                descriptors.add(new FoldingDescriptor(body,
                        new TextRange(begin.getTextRange().getEndOffset(), end.getTextRange().getStartOffset())));

            body.acceptChildren(this);
        }

        @Override
        public void visitTupleBody(@NotNull RonTupleBody body) {
            body.acceptChildren(this);
        }
    }

    @Override
    public boolean isCollapsedByDefault(@NotNull ASTNode astNode) {
        return false;
    }

    @Nullable
    @Override
    public String getPlaceholderText(@NotNull ASTNode astNode) {
        return "...";
    }

    @NotNull
    @Override
    public FoldingDescriptor[] buildFoldRegions(@NotNull PsiElement root, @NotNull Document document, boolean quick) {

        RonValue value = PsiTreeUtil.getChildOfType(root, RonValue.class);

        if (value == null)
            return FoldingDescriptor.EMPTY;

        FoldingBuilderVisitor visitor = new FoldingBuilderVisitor();

        visitor.visitValue(value);

        return visitor.getDescriptors().toArray(new FoldingDescriptor[0]);
    }
}
