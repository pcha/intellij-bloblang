plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.9.25"
    id("org.jetbrains.intellij.platform") version "2.2.1"
    id("org.jetbrains.grammarkit") version "2022.3.2.2"
}

group = "com.github.pcha"
version = System.getenv("VERSION")


repositories {
    mavenCentral()

    intellijPlatform {
        defaultRepositories()
    }
}

dependencies {
    intellijPlatform {
        intellijIdeaCommunity("2023.3")
    }
}

intellijPlatform {
    publishing {
        token = providers.gradleProperty("intellijPlatformPublishingToken")
        channels = listOf("eap")
    }
}


grammarKit {
    jflexRelease.set("1.7.0-1")
    grammarKitRelease.set("2021.1.2")
}


tasks {
    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "17"
    }

    patchPluginXml {
        sinceBuild.set("233")
//        untilBuild.set("243.*")
        changeNotes.set(System.getenv("CHANGELOG"))
    }

    generateLexer {
        // source flex file
        sourceFile.set(file("src/main/kotlin/com/github/pcha/intellij/bloblang/Bloblang.flex"))

        // target directory for lexer
        targetOutputDir.set(file("src/main/gen/com/github/pcha/intellij/bloblang"))

        // if set, plugin will remove a lexer output file before generating new one. Default: false
//        purgeOldFiles.set(true)

        dependsOn(generateParser)
    }

    generateParser {
        // source bnf file
        sourceFile.set(file("src/main/kotlin/com/github/pcha/intellij/bloblang/Bloblang.bnf"))

        // optional, task-specific root for the generated files. Default: none
        targetRootOutputDir.set(file("src/main/gen"))

        // path to a parser file, relative to the targetRoot
        pathToParser.set("com/github/pcha/intellij/bloblang/parser/BloblangParser.java")

        // path to a directory with generated psi files, relative to the targetRoot
        pathToPsiRoot.set("com/github/pcha/intellij/bloblang/psi")

        // if set, the plugin will remove a parser output file and psi output directory before generating new ones. Default: false
        purgeOldFiles.set(true)
    }

    compileKotlin {
        dependsOn(generateLexer)
    }



//    signPlugin {
//        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
//        privateKey.set(System.getenv("PRIVATE_KEY"))
//        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
//    }
}
tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

sourceSets["main"].java.srcDirs("src/main/gen")
