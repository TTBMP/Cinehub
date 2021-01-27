plugins {
    id("com.ttbmp.cinehub.java-library-conventions")
}

dependencies {
    api(project(":core"))
    implementation("com.google.code.gson", "gson", "2.8.5")
}
