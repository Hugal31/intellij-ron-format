package com.ronformat.psi.impl;

import com.intellij.lang.ASTNode;
import com.ronformat.psi.*;

/**
 * Utility class providing helpful methods for Ron PsiElements
 */
public class RonPsiImplUtil {
    public static String getField(RonField element) {
        ASTNode identifierNode = element.getNode().findChildByType(RonTypes.IDENTIFIER);

        if (identifierNode == null) {
            return null;
        }

        return identifierNode.getText();
    }
}
