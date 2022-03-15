plugins {
    id("org.jetbrains.intellij") version "1.4.0"
    kotlin("jvm") version "1.5.10"
    java
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
    version.set("2021.3.2")
}
tasks {
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
