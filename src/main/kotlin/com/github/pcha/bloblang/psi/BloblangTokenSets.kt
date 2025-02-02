package com.github.pcha.bloblang.psi

import com.intellij.psi.TokenType
import com.intellij.psi.tree.TokenSet

object BloblangTokenSets {
    val WHITE_SPACES: TokenSet = TokenSet.create(TokenType.WHITE_SPACE)

    val COMMENTS: TokenSet = TokenSet.create(BloblangTypes.COMMENT)

    val STRING_LITERALS: TokenSet = TokenSet.create(
        BloblangTypes.QUOTED_STRING,
        BloblangTypes.TRIPLE_QUOTE_STRING
    )

    val IDENTIFIERS: TokenSet = TokenSet.create(
        BloblangTypes.VAR_NAME,
        BloblangTypes.SNAKE_CASE, // TODO: Rename to FUNCTION_NAME
        BloblangTypes.ROOT,
        BloblangTypes.THIS
    )
}
