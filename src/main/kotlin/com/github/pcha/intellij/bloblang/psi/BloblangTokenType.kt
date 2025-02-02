package com.github.pcha.intellij.bloblang.psi

import com.github.pcha.intellij.bloblang.BloblangLanguage
import com.intellij.psi.tree.IElementType
import org.jetbrains.annotations.NonNls

class BloblangTokenType(debugName: @NonNls String) :
    IElementType(debugName, BloblangLanguage.INSTANCE) {
    override fun toString(): String {
        return "BloblangTokenType." + super.toString()
    }
}
