plugins {
    id("com.ttbmp.cinehub.java-library-conventions")
}

dependencies {
    api(project(":core"))

    testImplementation("org.junit.jupiter","junit-jupiter-api","5.6.2")
    testRuntimeOnly("org.junit.jupiter","junit-jupiter-engine")
}
