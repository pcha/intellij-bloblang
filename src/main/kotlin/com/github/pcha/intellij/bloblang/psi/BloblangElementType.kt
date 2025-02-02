package com.github.pcha.intellij.bloblang.psi

import com.intellij.psi.tree.IElementType
import com.github.pcha.intellij.bloblang.BloblangLanguage

class BloblangElementType(debugName: String) : IElementType(debugName, BloblangLanguage.INSTANCE)
