plugins {
    id("com.ttbmp.cinehub.java-library-conventions")
}

dependencies {
    implementation("com.google.firebase", "firebase-admin", "6.8.1")

    testImplementation("org.junit.jupiter", "junit-jupiter-api", "5.6.2")
    testRuntimeOnly("org.junit.jupiter", "junit-jupiter-engine")
}
