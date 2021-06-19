plugins {
    id("org.sonarqube") version "3.3"
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
