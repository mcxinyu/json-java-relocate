plugins {
    `java-library`
    alias(libs.plugins.shadow)
    `maven-publish`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.json)
}

tasks {
    shadowJar {
        isEnableRelocation = true
        relocate("org.json", "shadow.org.json")
    }
    build {
        dependsOn(shadowJar)
    }
}
