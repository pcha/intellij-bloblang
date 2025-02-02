package com.github.pcha.bloblang.psi

import com.github.pcha.bloblang.BloblangLanguage
import com.intellij.psi.tree.IElementType
import org.jetbrains.annotations.NonNls

class BloblangTokenType(debugName: @NonNls String) :
    IElementType(debugName, BloblangLanguage.INSTANCE) {
    override fun toString(): String {
        return "BloblangTokenType." + super.toString()
    }
}
