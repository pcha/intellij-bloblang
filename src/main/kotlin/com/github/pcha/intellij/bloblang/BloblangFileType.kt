package com.github.pcha.intellij.bloblang

import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon

class BloblangFileType private constructor() : LanguageFileType(BloblangLanguage.INSTANCE) {
    companion object {
        val INSTANCE = BloblangFileType()
    }

    override fun getName(): String {
        return "Bloblang File"
    }

    override fun getDescription(): String {
        return "Bloblang file"
    }

    override fun getDefaultExtension(): String {
        return "blobl"
    }

    override fun getIcon(): Icon {
        return BloblangIcons.Icon
    }
}