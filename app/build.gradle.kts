plugins {
    id("com.ttbmp.cinehub.java-library-conventions")
}

dependencies {
    api(project(":domain"))

    testImplementation("org.junit.jupiter", "junit-jupiter-api", "5.6.2")
    testRuntimeOnly("org.junit.jupiter", "junit-jupiter-engine")
}
