package com.github.pcha.intellij.bloblang.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider
import com.github.pcha.intellij.bloblang.BloblangFileType
import com.github.pcha.intellij.bloblang.BloblangLanguage

class BloblangFile(viewProvider: FileViewProvider) :
    PsiFileBase(viewProvider, BloblangLanguage.INSTANCE) {
    override fun getFileType(): FileType {
        return BloblangFileType.INSTANCE
    }

    override fun toString(): String {
        return "Bloblang File"
    }
}
