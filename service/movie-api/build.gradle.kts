plugins {
    id("com.ttbmp.cinehub.java-library-conventions")
}

dependencies {
    implementation("com.google.code.gson", "gson", "2.8.7")

    testImplementation("org.junit.jupiter", "junit-jupiter-api", "5.7.2")
    testRuntimeOnly("org.junit.jupiter", "junit-jupiter-engine")
}
