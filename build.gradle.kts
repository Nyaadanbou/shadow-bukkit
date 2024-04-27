plugins {
    `java-library`
    `maven-publish`
}

group = "cc.mewcraft"
version = "1.20.5-SNAPSHOT" // track MC version

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

repositories {
    mavenCentral()
    maven(uri("${System.getProperty("user.home")}/MewcraftRepository"))
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    api("cc.mewcraft", "shadow", "1.4")
    compileOnly("org.checkerframework", "checker-qual", "2.4.0")
    compileOnly("io.papermc.paper", "paper-api", "1.17.1-R0.1-SNAPSHOT")
}

publishing {
    repositories {
        maven {
            name = "mewcraftRepository"
            url = uri("https://repo.mewcraft.cc/releases/")
            credentials(PasswordCredentials::class)
            authentication {
                create<BasicAuthentication>("basic")
            }
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
