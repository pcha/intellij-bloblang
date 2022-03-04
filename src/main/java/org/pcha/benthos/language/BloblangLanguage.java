package org.pcha.benthos.language;

import com.intellij.lang.Language;

public class BloblangLanguage extends Language {
    public static final BloblangLanguage INSTANCE = new BloblangLanguage();

    private BloblangLanguage() {
        super("Bloblang");
    }
}