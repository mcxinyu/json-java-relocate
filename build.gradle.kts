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
    }
    assemble {
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
        repositories {
            maven {
                name = "XXX"
                url = uri("${project.buildDir}/repo")
            }
        }
    }
}
