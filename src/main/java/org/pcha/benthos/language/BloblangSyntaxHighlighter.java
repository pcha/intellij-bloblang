package org.pcha.benthos.language;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

import static org.pcha.benthos.language.psi.BloblangTypes.*;

public class BloblangSyntaxHighlighter extends SyntaxHighlighterBase {
    public static final TextAttributesKey BLOBLANG_KEYWORD = TextAttributesKey.createTextAttributesKey("BLOBLANG_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey BLOBLANG_PARENTHESIS = TextAttributesKey.createTextAttributesKey("BLOBLANG_PARENTHESIS", DefaultLanguageHighlighterColors.PARENTHESES);
    public static final TextAttributesKey BLOBLANG_BRACKET = TextAttributesKey.createTextAttributesKey("BLOBLANG_BRACKET", DefaultLanguageHighlighterColors.BRACKETS);
    public static final TextAttributesKey BLOBLANG_BRACE = TextAttributesKey.createTextAttributesKey("BLOBLANG_BRACE", DefaultLanguageHighlighterColors.BRACES);
    public static final TextAttributesKey BLOBLANG_COMMA = TextAttributesKey.createTextAttributesKey("BLOBLANG_COMMA", DefaultLanguageHighlighterColors.COMMA);
    public static final TextAttributesKey BLOBLANG_DOT = TextAttributesKey.createTextAttributesKey("BLOBLANG_DOT", DefaultLanguageHighlighterColors.DOT);
    public static final TextAttributesKey BLOBLANG_COLON = TextAttributesKey.createTextAttributesKey("BLOBLANG_COLON", DefaultLanguageHighlighterColors.OPERATION_SIGN);
    public static final TextAttributesKey BLOBLANG_STRING = TextAttributesKey.createTextAttributesKey("BLOBLANG_STRING", DefaultLanguageHighlighterColors.STRING);
    public static final TextAttributesKey BLOBLANG_NUMBER = TextAttributesKey.createTextAttributesKey("BLOBLANG_NUMBER", DefaultLanguageHighlighterColors.NUMBER);
    public static final TextAttributesKey BLOBLANG_OPERATOR = TextAttributesKey.createTextAttributesKey("BLOBLANG_OPERATOR", DefaultLanguageHighlighterColors.OPERATION_SIGN);
    public static final TextAttributesKey BLOBLANG_IDENTIFIER = TextAttributesKey.createTextAttributesKey("BLOBLANG_IDENTIFIER", DefaultLanguageHighlighterColors.IDENTIFIER);
    public static final TextAttributesKey BLOBLANG_COMMENT = TextAttributesKey.createTextAttributesKey("BLOBLANG_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
    public static final TextAttributesKey BLOBLANG_FUNCTION_CALL = TextAttributesKey.createTextAttributesKey("BLOBLANG_FUNCTION_CALL", DefaultLanguageHighlighterColors.FUNCTION_CALL);
    public static final TextAttributesKey BLOBLANG_BAD_CHARACTER = TextAttributesKey.createTextAttributesKey("BLOBLANG_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER);

    @Override
    public @NotNull Lexer getHighlightingLexer() {
        return new BloblangLexerAdapter();
    }

    @Override
    public TextAttributesKey @NotNull [] getTokenHighlights(IElementType tokenType) {
        Map<String, TextAttributesKey> tokenTypeMaps = Map.ofEntries(
                Map.entry(IMPORT_TERM.toString(), BLOBLANG_KEYWORD),
                Map.entry(MAP_NAME.toString(), BLOBLANG_KEYWORD),
                Map.entry(LET_TERM.toString(), BLOBLANG_KEYWORD),
                Map.entry(IF_TERM.toString(), BLOBLANG_KEYWORD),
                Map.entry(ELSE_TERM.toString(), BLOBLANG_KEYWORD),
                Map.entry(MATCH_TERM.toString(), BLOBLANG_KEYWORD),
                Map.entry(ROOT.toString(), BLOBLANG_KEYWORD),
                Map.entry(THIS.toString(), BLOBLANG_KEYWORD),
                Map.entry(OPEN_PARENTHESIS.toString(), BLOBLANG_PARENTHESIS),
                Map.entry(CLOSE_PARENTHESIS.toString(), BLOBLANG_PARENTHESIS),
                Map.entry(OPEN_BRACKET.toString(), BLOBLANG_BRACKET),
                Map.entry(CLOSE_BRACKET.toString(), BLOBLANG_BRACKET),
                Map.entry(OPEN_BRACE.toString(), BLOBLANG_BRACE),
                Map.entry(CLOSE_BRACE.toString(), BLOBLANG_BRACE),
                Map.entry(EQ.toString(), BLOBLANG_OPERATOR),
                Map.entry(COMMA.toString(), BLOBLANG_COMMA),
                Map.entry(DOT.toString(), BLOBLANG_DOT),
                Map.entry(UNDERSCORE.toString(), BLOBLANG_KEYWORD),
                Map.entry(COLON.toString(), BLOBLANG_COLON),
                Map.entry(NOT.toString(), BLOBLANG_OPERATOR),
                Map.entry(MINUS.toString(), BLOBLANG_OPERATOR),
                Map.entry(PLUS.toString(), BLOBLANG_OPERATOR),
                Map.entry(DIVIDE.toString(), BLOBLANG_OPERATOR),
                Map.entry(MULTIPLY.toString(), BLOBLANG_OPERATOR),
                Map.entry(REST.toString(), BLOBLANG_OPERATOR),
                Map.entry(AND.toString(), BLOBLANG_OPERATOR),
                Map.entry(OR.toString(), BLOBLANG_OPERATOR),
                Map.entry(EQUALS.toString(), BLOBLANG_OPERATOR),
                Map.entry(NOT_EQUALS.toString(), BLOBLANG_OPERATOR),
                Map.entry(GREATER.toString(), BLOBLANG_OPERATOR),
                Map.entry(GREATER_OR_EQUAL.toString(), BLOBLANG_OPERATOR),
                Map.entry(LESS.toString(), BLOBLANG_OPERATOR),
                Map.entry(LESS_OR_EQUAL.toString(), BLOBLANG_OPERATOR),
                Map.entry(PIPE.toString(), BLOBLANG_OPERATOR),
                Map.entry(DOUBLE_ARROW.toString(), BLOBLANG_OPERATOR),
                Map.entry(ARROW.toString(), BLOBLANG_OPERATOR),
                Map.entry(VARIABLE_LITERAL.toString(), BLOBLANG_IDENTIFIER),
                Map.entry(QUOTED_STRING.toString(), BLOBLANG_STRING),
                Map.entry(BOOLEAN.toString(), BLOBLANG_KEYWORD),
                Map.entry(NUMBER.toString(), BLOBLANG_NUMBER),
                Map.entry(TRIPLE_QUOTE_STRING.toString(), BLOBLANG_STRING),
                Map.entry(NULL.toString(), BLOBLANG_KEYWORD),
                Map.entry(FUNCTION_CALL_OPEN.toString(), BLOBLANG_FUNCTION_CALL),
                Map.entry(FUNCTION_CALL_CLOSE.toString(), BLOBLANG_FUNCTION_CALL),
                Map.entry(VAR_NAME.toString(), BLOBLANG_IDENTIFIER),
                Map.entry(COMMENT.toString(), BLOBLANG_COMMENT),
                Map.entry(TokenType.BAD_CHARACTER.toString(), BLOBLANG_BAD_CHARACTER)
        );
        TextAttributesKey key = tokenTypeMaps.get(tokenType.toString());
        return key != null ? new TextAttributesKey[]{key} : new TextAttributesKey[0];
    }
}
