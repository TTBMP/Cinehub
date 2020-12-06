plugins {
    id("com.ttbmp.cinehub.java-application-conventions")
}

dependencies {
    implementation(project(":core"))
    implementation(project(":data"))
}

application {
    mainClass.set("com.ttbmp.cinehub.webapp.Example")
}
