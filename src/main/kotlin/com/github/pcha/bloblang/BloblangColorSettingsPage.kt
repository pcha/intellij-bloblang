package com.github.pcha.bloblang

import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.options.colors.ColorSettingsPage
import javax.swing.Icon

class BloblangColorSettingsPage : ColorSettingsPage {

    override fun getDisplayName(): String = "Bloblang"

    // Opcional: Si tienes un ícono para tu lenguaje, retórnalo; de lo contrario, retorna null.
    override fun getIcon(): Icon? = BloblangIcons.Icon

    // Retorna el SyntaxHighlighter que definiste para tu lenguaje.
    override fun getHighlighter(): SyntaxHighlighter = BloblangSyntaxHighlighter()

    // Este es el texto de demostración que se mostrará en la vista de colores.
    // Puedes incluir comentarios, keywords, strings, etc., para que el usuario vea los estilos aplicados.
    override fun getDemoText(): String {
        return """
            // Comentario de ejemplo
            import "lib"
            
            let x = 10
            map "clave" {
                // Otra línea de comentario
                mi_funcion("argumento", 42)
            }
        """.trimIndent()
    }

    // Este mapa asocia etiquetas HTML (en el demo text) a claves de atributos de texto, para poder usarlas en el demo.
    // Es opcional y se usa si quieres marcar partes específicas en el demo text.
    override fun getAdditionalHighlightingTagToDescriptorMap(): Map<String, TextAttributesKey>? {
        return mapOf(
            "keyword" to BloblangSyntaxHighlighter.KEYWORD,
            "comment" to BloblangSyntaxHighlighter.COMMENT,
            "string" to BloblangSyntaxHighlighter.STRING,
            "number" to BloblangSyntaxHighlighter.NUMBER,
            "operator" to BloblangSyntaxHighlighter.OPERATOR,
            "identifier" to BloblangSyntaxHighlighter.IDENTIFIER
        )
    }

    // Define los descriptors que se mostrarán en la página de colores, cada uno con su nombre descriptivo y su TextAttributesKey.
    override fun getAttributeDescriptors(): Array<AttributesDescriptor> = DESCRIPTORS

    // En este ejemplo, no estamos definiendo colores globales (sólo atributos), por lo que se retorna un arreglo vacío.
    override fun getColorDescriptors(): Array<ColorDescriptor> = ColorDescriptor.EMPTY_ARRAY

    companion object {
        private val DESCRIPTORS = arrayOf(
            AttributesDescriptor("Keyword", BloblangSyntaxHighlighter.KEYWORD),
            AttributesDescriptor("Comment", BloblangSyntaxHighlighter.COMMENT),
            AttributesDescriptor("String", BloblangSyntaxHighlighter.STRING),
            AttributesDescriptor("Number", BloblangSyntaxHighlighter.NUMBER),
            AttributesDescriptor("Operator", BloblangSyntaxHighlighter.OPERATOR),
            AttributesDescriptor("Identifier", BloblangSyntaxHighlighter.IDENTIFIER)
        )
    }
}
