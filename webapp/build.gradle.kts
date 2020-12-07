plugins {
    id("com.ttbmp.cinehub.java-application-conventions")
}

dependencies {
    implementation(project(":core"))
    implementation(project(":data"))

    implementation("org.seleniumhq.selenium","selenium-java","3.141.59")
    implementation("io.github.bonigarcia","webdrivermanager","4.2.0")
}

application {
    mainClass.set("com.ttbmp.cinehub.webapp.Example")
}
