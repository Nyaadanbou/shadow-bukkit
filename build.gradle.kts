plugins {
    `java-library`
    `maven-publish`
}

group = "cc.mewcraft"
version = "1.20.4" // track MC version

java {
    withSourcesJar()
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

tasks {
    compileJava {
        options.encoding = "UTF-8"
    }
}

val userHome: String = when {
    System.getProperty("os.name").startsWith("Windows", ignoreCase = true) -> System.getenv("USERPROFILE")
    else -> System.getenv("HOME")
}

repositories {
    mavenCentral()
    maven(uri("$userHome/MewcraftRepository"))
    maven(url = "https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
}

dependencies {
    compileOnlyApi("cc.mewcraft", "shadow", "1.4")
    compileOnly("org.checkerframework", "checker-qual", "2.4.0")
    compileOnly("org.bukkit", "bukkit", "1.15.2-R0.1-SNAPSHOT")
}

publishing {
    repositories {
        maven {
            url = uri("$userHome/MewcraftRepository")
        }
    }
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])

            pom {
                name.set("shadow-bukkit")
                description.set("Extra annotations for use with shadow and Bukkit")
                url.set("https://github.com/lucko/shadow-bukkit")
                licenses {
                    license {
                        name.set("MIT")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }
                developers {
                    developer {
                        id.set("lucko")
                        name.set("Luck")
                        url.set("https://lucko.me")
                        email.set("git@lucko.me")
                    }
                }
                scm {
                    connection.set("scm:git:https://github.com/lucko/shadow-bukkit.git")
                    developerConnection.set("scm:git:git@github.com:lucko/shadow-bukkit.git")
                    url.set("https://github.com/lucko/shadow-bukkit")
                }
                issueManagement {
                    system.set("GitHub")
                    url.set("https://github.com/lucko/shadow-bukkit/issues")
                }
            }
        }
    }
}
