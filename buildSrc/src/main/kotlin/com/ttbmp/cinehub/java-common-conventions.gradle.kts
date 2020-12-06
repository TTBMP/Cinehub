plugins {
    java
}

repositories {
    jcenter()
}

dependencies {
    implementation("javax", "javaee-api", "8.0.1")

    testImplementation("org.junit.jupiter","junit-jupiter-api","5.6.2")
    testRuntimeOnly("org.junit.jupiter","junit-jupiter-engine")
}

tasks.test {
    useJUnitPlatform()
}
