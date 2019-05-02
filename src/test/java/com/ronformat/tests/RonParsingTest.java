package com.ronformat.tests;

import com.intellij.testFramework.ParsingTestCase;
import com.ronformat.RonParserDefinition;

public class RonParsingTest extends ParsingTestCase {

    public RonParsingTest() {
        super("parsingTests", "ron", new RonParserDefinition());
    }

    @Override
    protected String getTestDataPath() {
        return "src/test/resources";
    }

    public void testParsingTestData() {
        doTest(true);
    }

    @Override
    protected boolean skipSpaces() {
        return true;
    }

    @Override
    protected boolean includeRanges() {
        return false;
    }
}
