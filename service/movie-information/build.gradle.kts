plugins {
    id("com.ttbmp.cinehub.java-library-conventions")
}

dependencies {
    api(project(":core"))
    implementation("net.minidev", "json-smart", "2.3")
}
