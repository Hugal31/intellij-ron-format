package com.ronformat.tests;

import com.intellij.testFramework.ParsingTestCase;
import com.ronformat.RonParserDefinition;

public class RonParsingTest extends ParsingTestCase {

    public RonParsingTest() {
        super("testData", "ron", new RonParserDefinition());
    }

    public void testParsingTestData() {
        doTest(true);
    }

    @Override
    protected boolean skipSpaces() {
        return false;
    }

    @Override
    protected boolean includeRanges() {
        return true;
    }
}
