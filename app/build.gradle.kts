plugins {
    id("com.ttbmp.cinehub.java-application-conventions")
    id("org.openjfx.javafxplugin") version "0.0.9"
}

dependencies {
    implementation(project(":core"))
    implementation(project(":data"))
}

javafx {
    version = "15.0.1"
    modules("javafx.controls", "javafx.fxml")
}

application {
    mainClass.set("com.ttbmp.cinehub.app.CinehubApplication")
}
