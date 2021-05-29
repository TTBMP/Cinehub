plugins {
    id("org.sonarqube") version "3.2.0"
}

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("org.sonarsource.scanner.gradle", "sonarqube-gradle-plugin", "3.2.0")
    }
}

sonarqube {
    properties {
        property("sonar.sourceEncoding", "UTF-8")
        property("sonar.projectKey", "TTBMP_Cinehub")
        property("sonar.organization", "ttbmp")
        property("sonar.host.url", "https://sonarcloud.io")
        property("sonar.verbose", "true")
    }
}
