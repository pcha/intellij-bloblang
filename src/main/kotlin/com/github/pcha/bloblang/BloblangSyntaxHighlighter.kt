package com.github.pcha.bloblang

import com.github.pcha.bloblang.psi.BloblangTypes
import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType

class BloblangSyntaxHighlighter : SyntaxHighlighterBase() {
    override fun getHighlightingLexer(): Lexer {
        return BloblangLexerAdapter()
    }

    override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey> {
        return when (tokenType) {
            // Espacios en blanco (normalmente no se resaltan)
            TokenType.WHITE_SPACE -> WHITE_SPACE_KEYS

            // Comentarios
            BloblangTypes.COMMENT -> COMMENT_KEYS

            // Literales de cadena
            BloblangTypes.QUOTED_STRING,
            BloblangTypes.TRIPLE_QUOTE_STRING -> STRING_KEYS

            // Números
            BloblangTypes.NUMBER -> NUMBER_KEYS

            // Palabras reservadas (keywords)
            BloblangTypes.IMPORT_TERM,
            BloblangTypes.MAP_TERM,
            BloblangTypes.LET_TERM,
            BloblangTypes.IF_TERM,
            BloblangTypes.ELSE_TERM,
            BloblangTypes.MATCH_TERM -> KEYWORD_KEYS

            // Operadores y símbolos
            BloblangTypes.EQ,
            BloblangTypes.COLON,
            BloblangTypes.DOT,
            BloblangTypes.PLUS,
            BloblangTypes.MINUS,
            BloblangTypes.DIVIDE,
            BloblangTypes.MULTIPLY,
            BloblangTypes.AND,
            BloblangTypes.OR,
            BloblangTypes.NOT,
            BloblangTypes.GREATER,
            BloblangTypes.LESS,
            BloblangTypes.GREATER_OR_EQUAL,
            BloblangTypes.LESS_OR_EQUAL,
            BloblangTypes.PIPE,
            BloblangTypes.DOUBLE_ARROW,
            BloblangTypes.ARROW -> OPERATOR_KEYS

            // Identificadores (por ejemplo, VAR_NAME y SNAKE_CASE)
            BloblangTypes.VAR_NAME,
            BloblangTypes.SNAKE_CASE,
            BloblangTypes.ROOT,
            BloblangTypes.THIS -> IDENTIFIER_KEYS

            // Otros tokens o tokens no reconocidos
            else -> EMPTY_KEYS
        }
    }

    companion object {
        // Definición de las claves de atributos de texto para cada tipo
        val WHITE_SPACE = TextAttributesKey.createTextAttributesKey("BLOBLANG_WHITE_SPACE")
        val COMMENT = TextAttributesKey.createTextAttributesKey(
            "BLOBLANG_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT
        )
        val STRING = TextAttributesKey.createTextAttributesKey(
            "BLOBLANG_STRING", DefaultLanguageHighlighterColors.STRING
        )
        val NUMBER = TextAttributesKey.createTextAttributesKey(
            "BLOBLANG_NUMBER", DefaultLanguageHighlighterColors.NUMBER
        )
        val KEYWORD = TextAttributesKey.createTextAttributesKey(
            "BLOBLANG_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD
        )
        val OPERATOR = TextAttributesKey.createTextAttributesKey(
            "BLOBLANG_OPERATOR", DefaultLanguageHighlighterColors.OPERATION_SIGN
        )
        val IDENTIFIER = TextAttributesKey.createTextAttributesKey(
            "BLOBLANG_IDENTIFIER", DefaultLanguageHighlighterColors.IDENTIFIER
        )

        private val WHITE_SPACE_KEYS = arrayOf(WHITE_SPACE)
        private val COMMENT_KEYS = arrayOf(COMMENT)
        private val STRING_KEYS = arrayOf(STRING)
        private val NUMBER_KEYS = arrayOf(NUMBER)
        private val KEYWORD_KEYS = arrayOf(KEYWORD)
        private val OPERATOR_KEYS = arrayOf(OPERATOR)
        private val IDENTIFIER_KEYS = arrayOf(IDENTIFIER)
        private val EMPTY_KEYS = emptyArray<TextAttributesKey>()
    }
}