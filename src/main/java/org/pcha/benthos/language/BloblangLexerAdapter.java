package org.pcha.benthos.language;

import com.intellij.lexer.FlexAdapter;

public class BloblangLexerAdapter extends FlexAdapter {
    public BloblangLexerAdapter() {
        super(new BloblangLexer(null));
    }
}
