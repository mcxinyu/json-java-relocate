plugins {
    `java-library`
    alias(libs.plugins.shadow)
    `maven-publish`
}

repositories {
    mavenCentral()
}

group = "shadow.org.json"
version = "1.0.0"

dependencies {
    implementation(libs.json)
}

tasks {
    shadowJar {
        isEnableRelocation = true
        archiveClassifier = ""
        relocate("org.json", "shadow.org.json")
    }
    build {
        dependsOn(shadowJar)
    }
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("maven") {
                afterEvaluate {
                    from(components["shadow"])
                }
            }
        }
    }
}
