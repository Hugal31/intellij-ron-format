package com.ronformat;

import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.TokenType;
import com.intellij.psi.formatter.common.AbstractBlock;
import com.intellij.psi.tree.IElementType;
import com.ronformat.psi.RonTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

class RonBlock extends AbstractBlock {

    /// Types with indent
    private static final Set<IElementType> PARENT_TYPES = new HashSet<>(Arrays.asList(
            RonTypes.ARRAY_BODY,
            RonTypes.DICTIONARY_BODY,
            RonTypes.TUPLE_BODY,
            RonTypes.STRUCT_BODY
    ));

    private static final Set<IElementType> NO_ALIGN_TYPES = new HashSet<>(Collections.singletonList(RonTypes.SEPARATOR));

    private static final Map<IElementType, Wrap> WRAPS;
    static {
        WRAPS = new HashMap<>();
        WRAPS.put(RonTypes.SEPARATOR, Wrap.createWrap(WrapType.NONE, false));
    }

    private static final Map<IElementType, Indent> INDENTS;
    static {
        INDENTS = new HashMap<>();
        INDENTS.put(RonTypes.SEPARATOR, Indent.getNoneIndent());
    }

    private final Indent myIndent;

    RonBlock(@NotNull ASTNode node, @Nullable Wrap wrap, @Nullable Alignment alignment) {
        super(node, wrap, alignment);
        myIndent = Indent.getNoneIndent();
    }

    private RonBlock(@NotNull ASTNode node, @Nullable Wrap wrap, @Nullable Alignment alignment, Indent indent) {
        super(node, wrap, alignment);
        myIndent = indent;
    }

    @Override
    public Indent getIndent() {
        return myIndent;
    }

    @Nullable
    @Override
    public Spacing getSpacing(@Nullable Block block, @NotNull Block block1) {
        return null;
    }

    @Override
    public boolean isLeaf() {
        return myNode.getFirstChildNode() == null;
    }

    @Override
    protected List<Block> buildChildren() {
        List<Block> blocks = new ArrayList<>();

        buildChildrenNode(myNode, blocks);

        return blocks;
    }

    private static void buildChildrenNode(@NotNull ASTNode node, List<Block> blocks) {
        buildChildrenNode(node, blocks, Indent.getNoneIndent(), null);
    }

    private static void buildChildrenNode(@NotNull ASTNode node, List<Block> blocks, Indent indent, @Nullable Alignment alignment) {
        for (ASTNode child = node.getFirstChildNode(); child != null; child = child.getTreeNext()) {
            if (child.getElementType() == TokenType.WHITE_SPACE)
                continue;

            if (child.getElementType() == RonTypes.VALUE) { // Skip value node itself, 
                buildChildrenNode(child, blocks, indent, alignment);
            } else if (PARENT_TYPES.contains(child.getElementType())) { // Skip parent nodes, so we flatten the struct
                buildChildrenNode(child, blocks, Indent.getNormalIndent(), Alignment.createAlignment());
            } else {
                Wrap wrap = WRAPS.getOrDefault(child.getElementType(), null);
                Alignment a = NO_ALIGN_TYPES.contains(child.getElementType()) ? null : alignment;
                Indent i = INDENTS.getOrDefault(child.getElementType(), indent);
                blocks.add(new RonBlock(child, wrap, a, i));
            }
        }

    }
}
