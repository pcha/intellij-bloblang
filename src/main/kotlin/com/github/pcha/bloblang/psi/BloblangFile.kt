package com.github.pcha.bloblang.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider
import com.github.pcha.bloblang.BloblangFileType
import com.github.pcha.bloblang.BloblangLanguage
import org.jetbrains.annotations.NotNull

class BloblangFile(viewProvider: FileViewProvider) :
    PsiFileBase(viewProvider, BloblangLanguage.INSTANCE) {
    override fun getFileType(): FileType {
        return BloblangFileType.INSTANCE
    }

    override fun toString(): String {
        return "Bloblang File"
    }
}
