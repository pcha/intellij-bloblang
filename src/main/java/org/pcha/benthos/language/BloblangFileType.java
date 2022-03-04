package org.pcha.benthos.language;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class BloblangFileType extends LanguageFileType {
    public static final BloblangFileType INSTANCE = new BloblangFileType();

    private BloblangFileType() {
        super(BloblangLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "Bloblang";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Bloblang mapping file";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "blobl";
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return BloblangIcons.FILE;
    }
}

