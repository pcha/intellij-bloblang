package org.pcha.benthos.language.psi;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class BloblangTokenType extends com.intellij.psi.tree.IElementType {
    public BloblangTokenType(@NotNull @NonNls String debugName) {
        super(debugName, org.pcha.benthos.language.BloblangLanguage.INSTANCE);
    }

    public String toString() {
        return "SimpleTokenType." + super.toString();
    }
}
