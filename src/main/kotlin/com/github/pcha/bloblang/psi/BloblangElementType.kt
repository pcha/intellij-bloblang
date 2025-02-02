package com.github.pcha.bloblang.psi

import com.intellij.psi.tree.IElementType
import com.github.pcha.bloblang.BloblangLanguage

class BloblangElementType(debugName: String) : IElementType(debugName, BloblangLanguage.INSTANCE)
