package org.pcha.benthos.language;

import com.intellij.testFramework.ParsingTestCase;
import org.junit.Test;

public class BloblangParsingTest extends ParsingTestCase{
    public BloblangParsingTest() {
        super("", "blobl", new BloblangParserDefinition());
    }

    @Test
    public void testParsingBadVariableName() {
        doTest(true);
    }

    @Test
    public void testParsingBadMetaName() {
        doTest(true);
    }

    @Test
    public void testParsingNoMappings() {
        doTest(true);
    }

    @Test
    public void testDoubleMapping() {
        doTest(true);
    }

    @Test
    public void testLetStatement() {
        doTest(true, true);
    }



    /**
     * @return path to test data file directory relative to root of this module.
     */
    @Override
    protected String getTestDataPath() {
        return "src/test/testData";
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
