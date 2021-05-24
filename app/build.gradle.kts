plugins {
    id("com.ttbmp.cinehub.java-library-conventions")
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":service:security"))
    implementation(project(":service:email"))
    implementation(project(":service:movie-api"))
    implementation(project(":service:payment"))
    implementation(project(":service:persistence"))

    testImplementation("org.junit.jupiter", "junit-jupiter-api", "5.6.2")
    testRuntimeOnly("org.junit.jupiter", "junit-jupiter-engine")
}
