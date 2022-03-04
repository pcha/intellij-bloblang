package org.pcha.benthos.language.psi;

import com.intellij.psi.tree.IElementType;
import org.pcha.benthos.language.BloblangLanguage;

public class BloblangElementType extends IElementType {
    public BloblangElementType(String debugName) {
        super(debugName, BloblangLanguage.INSTANCE);
    }
}
