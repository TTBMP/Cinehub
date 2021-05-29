plugins {
    id("com.ttbmp.cinehub.java-library-conventions")
}

dependencies {

    implementation("javax", "javaee-api", "8.0.1")

    runtimeOnly("mysql", "mysql-connector-java", "8.0.25")

    testImplementation("org.junit.jupiter", "junit-jupiter-api", "5.7.2")
    testRuntimeOnly("org.junit.jupiter", "junit-jupiter-engine")
}
