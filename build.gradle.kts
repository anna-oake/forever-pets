import gg.meza.stonecraft.mod
import org.gradle.api.tasks.bundling.AbstractArchiveTask

plugins {
    id("gg.meza.stonecraft")
}

val compatibilityTest = providers.gradleProperty("compatibilityTest").isPresent
val minecraftVersion = property("minecraft_version").toString()
val loaderName = name.substringAfterLast("-")
val minecraftRangeName = name.removeSuffix("-$loaderName")
val releaseJarName =
    "forever-pets-${property("mod.version")}-$loaderName-$minecraftRangeName.jar"

sourceSets.main {
    java.srcDir(rootProject.file("src/$loaderName/java"))
    resources.srcDir(rootProject.file("src/$loaderName/resources"))
}

modSettings {
    variableReplacements = mapOf(
        "authors" to "anna-oake",
        "homepage" to "https://modrinth.com/mod/forever-pets",
        "sources" to "https://github.com/anna-oake/forever-pets",
        "license" to "MIT",
        "loaderVersion" to property("loader_version").toString(),
        "javaVersion" to if (minecraftVersion.startsWith("26.")) "25" else "21",
        "fabricMinecraftRange" to if (compatibilityTest) {
            ">=$minecraftVersion"
        } else {
            property("fabric_minecraft_range").toString()
        },
        "neoForgeMinecraftRange" to if (compatibilityTest) {
            "[$minecraftVersion,)"
        } else {
            property("neoforge_minecraft_range").toString()
        }
    )
}

tasks.withType<Jar>().configureEach {
    from(rootProject.file("LICENSE")) {
        rename { "${it}_${rootProject.name}" }
    }
}

if (minecraftVersion.startsWith("26.")) {
    tasks.named<Jar>("jar") {
        archiveFileName.set(releaseJarName)
    }
} else {
    tasks.named<AbstractArchiveTask>("remapJar") {
        archiveFileName.set(releaseJarName)
    }
}
