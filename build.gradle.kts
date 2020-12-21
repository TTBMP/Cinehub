plugins {
    id ("org.sonarqube") version "3.0"
}

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("org.sonarsource.scanner.gradle","sonarqube-gradle-plugin","3.0")
    }
}

sonarqube {
    properties {
        property("sonar.sourceEncoding", "UTF-8")
        property("sonar.projectKey", "TTBMP_Cinehub")
        property("sonar.sources", "src/main/java, webapp/src/main/resources")
    }
}
