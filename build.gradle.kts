plugins {
    id("org.jetbrains.intellij") version "1.4.0"
    kotlin("jvm") version "1.5.10"
    java
    id("org.jetbrains.grammarkit") version "2021.2.2"
}

group = "org.pcha"
version = System.getenv("VERSION")

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

// See https://github.com/JetBrains/gradle-intellij-plugin/
intellij {
    version.set("2022.1.2")
}
tasks {

    generateLexer {
        // source flex file
        source.set("src/main/java/org/pcha/benthos/language/Bloblang.flex")

        // target directory for lexer
        targetDir.set("src/main/gen/org/pcha/benthos/language")

        // target classname, target file will be targetDir/targetClass.java
        targetClass.set("BloblangLexer")

        // if set, plugin will remove a lexer output file before generating new one. Default: false
        purgeOldFiles.set(true)
    }

    generateParser {
        // source bnf file
        source.set("src/main/java/org/pcha/benthos/language/Bloblang.bnf")

        // optional, task-specific root for the generated files. Default: none
        targetRoot.set("src/main/gen")

        // path to a parser file, relative to the targetRoot
        pathToParser.set("org/pcha/benthos/language/parser/BloblangParser.java")

        // path to a directory with generated psi files, relative to the targetRoot
        pathToPsiRoot.set("org/pcha/benthos/language/psi")

        // if set, the plugin will remove a parser output file and psi output directory before generating new ones. Default: false
        purgeOldFiles.set(true)
    }

    compileJava {
        dependsOn(generateLexer, generateParser)
    }

    patchPluginXml {
        sinceBuild.set("203")
        changeNotes.set(System.getenv("CHANGELOG"))
    }

    publishPlugin {
        token.set(System.getenv("INTELLIJ_MARKETPLACE_TOKEN"))
        channels.set(listOf(System.getenv("MARKETPLACE_CHANNEL")))
    }
}
tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

sourceSets["main"].java.srcDirs("src/main/gen")
