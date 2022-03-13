package org.pcha.benthos.language.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.lang.Language;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.jetbrains.annotations.NotNull;
import org.pcha.benthos.language.BloblangFileType;
import org.pcha.benthos.language.BloblangLanguage;

public class BloblangFile extends PsiFileBase {

    public BloblangFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, BloblangLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return BloblangFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "Bloblang File";
    }
}
