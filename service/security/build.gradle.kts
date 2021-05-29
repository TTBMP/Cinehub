plugins {
    id("com.ttbmp.cinehub.java-library-conventions")
}

dependencies {
    implementation("com.google.firebase", "firebase-admin", "7.3.0")

    testImplementation("org.junit.jupiter", "junit-jupiter-api", "5.7.2")
    testRuntimeOnly("org.junit.jupiter", "junit-jupiter-engine")
}
