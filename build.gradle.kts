import net.minecrell.pluginyml.bukkit.BukkitPluginDescription
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
plugins {
    `java-library`
    kotlin("jvm") version "1.7.0"
    id("io.papermc.paperweight.userdev") version "1.3.7"
    id("net.minecrell.plugin-yml.bukkit") version "0.5.2"
    id("xyz.jpenilla.run-paper") version "1.0.6"
}

bukkit {
    load = BukkitPluginDescription.PluginLoadOrder.STARTUP
    main = "nyc.cleo.bettermending.BetterMending"
    apiVersion = "1.18"
    authors = listOf("cleonyc")
    libraries = listOf("net.axay:kspigot:1.19.0")
}
group = "nyc.cleo"
version = "1.1-SNAPSHOT"
val kotlinJvmTarget = JavaVersion.toVersion(17)
repositories {
    mavenCentral()
//    maven("https://oss.sonatype.org/content/groups/public/") {
//        name = "sonatype"
//    }
    maven("https://repo.onarandombox.com/content/groups/public/" ) {
        name = "mv-repo"
    }
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    compileOnly("com.onarandombox.multiversecore:Multiverse-Core:4.3.2-SNAPSHOT")
    implementation("net.axay:kspigot:1.19.0")
    paperDevBundle("1.19-R0.1-SNAPSHOT")
}


tasks {
    build {
        dependsOn(reobfJar)
    }
    compileJava {
        options.encoding = "UTF-8"
        options.release.set(17)
    }
    compileKotlin {
        kotlinOptions.jvmTarget = "17"
    }
}
