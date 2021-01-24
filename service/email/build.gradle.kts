plugins {
    id("com.ttbmp.cinehub.java-library-conventions")
}

dependencies {
    api(project(":core"))
    implementation("javax.mail", "javax.mail-api", "1.6.0")
}
