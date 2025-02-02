package com.github.pcha.intellij.bloblang

import com.intellij.lexer.FlexAdapter

class BloblangLexerAdapter : FlexAdapter(BloblangLexer(null))