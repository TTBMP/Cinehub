plugins {
    id("com.ttbmp.cinehub.java-library-conventions")
}

dependencies {
    implementation("javax.mail", "javax.mail-api", "1.6.0")

    testImplementation("org.junit.jupiter", "junit-jupiter-api", "5.7.0")
    testRuntimeOnly("org.junit.jupiter", "junit-jupiter-engine")
}
