plugins {
    id("com.ttbmp.cinehub.java-library-conventions")
}

dependencies {
    api(project(":core"))
    implementation("com.stripe", "stripe-java", "20.24.0")
}
