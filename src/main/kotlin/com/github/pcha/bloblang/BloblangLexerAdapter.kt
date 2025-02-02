package com.github.pcha.bloblang

import com.intellij.lexer.FlexAdapter

class BloblangLexerAdapter : FlexAdapter(BloblangLexer(null))