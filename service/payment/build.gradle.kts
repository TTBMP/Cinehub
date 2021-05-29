plugins {
    id("com.ttbmp.cinehub.java-library-conventions")
}

dependencies {
    implementation("com.stripe", "stripe-java", "20.24.0")

    testImplementation("org.junit.jupiter", "junit-jupiter-api", "5.7.0")
    testRuntimeOnly("org.junit.jupiter", "junit-jupiter-engine")
}
