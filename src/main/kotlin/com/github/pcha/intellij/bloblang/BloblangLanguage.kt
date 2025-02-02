package com.github.pcha.intellij.bloblang

import com.intellij.lang.Language

class BloblangLanguage private constructor() : Language("Bloblang") {
 companion object {
  val INSTANCE: BloblangLanguage by lazy { BloblangLanguage() }
 }
}