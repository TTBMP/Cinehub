plugins {
    id("com.ttbmp.cinehub.java-application-conventions")
    id("org.openjfx.javafxplugin") version "0.0.10"
}

dependencies {
    implementation(project(":app"))

    implementation("org.kordamp.bootstrapfx", "bootstrapfx-core", "0.4.0")

    testImplementation("org.junit.jupiter", "junit-jupiter-api", "5.7.2")
    testRuntimeOnly("org.junit.jupiter", "junit-jupiter-engine")
}

javafx {
    version = "16"
    modules("javafx.controls", "javafx.fxml")
}

application {
    mainClass.set("com.ttbmp.cinehub.ui.desktop.CinehubApplication")
}
