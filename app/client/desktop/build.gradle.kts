plugins {
    id("com.ttbmp.cinehub.java-application-conventions")
    id("org.openjfx.javafxplugin") version "0.0.9"
}

dependencies {
    implementation(project(":core"))
    implementation(project(":data:local"))
    implementation(project(":service:authentication"))
    implementation(project(":service:payment"))
    implementation(project(":service:email"))
    implementation(project(":service:movie-information"))

    testImplementation("org.junit.jupiter", "junit-jupiter-api", "5.6.2")
    testRuntimeOnly("org.junit.jupiter", "junit-jupiter-engine")
}

javafx {
    version = "15.0.1"
    modules("javafx.controls", "javafx.fxml")
}

application {
    mainClass.set("com.ttbmp.cinehub.app.client.desktop.CinehubApplication")
}
