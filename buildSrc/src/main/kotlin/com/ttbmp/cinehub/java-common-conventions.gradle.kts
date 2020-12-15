package com.ttbmp.cinehub

plugins {
    java
    jacoco
}

repositories {
    jcenter()
}

dependencies {
    implementation("javax", "javaee-api", "8.0.1")
}

jacoco {
    toolVersion = "0.8.6"
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.isEnabled = true
    }
}
