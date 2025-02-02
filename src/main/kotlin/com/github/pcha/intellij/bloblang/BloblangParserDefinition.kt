package com.github.pcha.intellij.bloblang

import com.github.pcha.intellij.bloblang.parser.BloblangParser
import com.github.pcha.intellij.bloblang.psi.BloblangFile
import com.github.pcha.intellij.bloblang.psi.BloblangTokenSets
import com.github.pcha.intellij.bloblang.psi.BloblangTypes
import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.lang.PsiParser
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet
import org.jetbrains.annotations.NotNull

internal class BloblangParserDefinition : ParserDefinition {
    @NotNull
    override fun createLexer(project: Project?): Lexer {
        return BloblangLexerAdapter()
    }

    @NotNull
    override fun getCommentTokens(): TokenSet {
        return BloblangTokenSets.COMMENTS
    }

    @NotNull
    override fun getStringLiteralElements(): TokenSet {
        return BloblangTokenSets.STRING_LITERALS
    }

    @NotNull
    override fun createParser(project: Project?): PsiParser {
        return BloblangParser()
    }

    @NotNull
    override fun getFileNodeType(): IFileElementType {
        return FILE
    }

    @NotNull
    override fun createFile(@NotNull viewProvider: FileViewProvider): PsiFile {
        return BloblangFile(viewProvider)
    }

    @NotNull
    override fun createElement(node: ASTNode?): PsiElement {
        return BloblangTypes.Factory.createElement(node)
    }

    companion object {
        val FILE: IFileElementType = IFileElementType(BloblangLanguage.INSTANCE)
    }
}