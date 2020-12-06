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

    testImplementation("org.junit.jupiter","junit-jupiter-api","5.6.2")
    testRuntimeOnly("org.junit.jupiter","junit-jupiter-engine")
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
