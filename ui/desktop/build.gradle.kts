plugins {
    id("com.ttbmp.cinehub.java-application-conventions")
    id("org.openjfx.javafxplugin") version "0.0.10"
}

dependencies {
    implementation(project(":app"))

    testImplementation("org.junit.jupiter", "junit-jupiter-api", "5.7.0")
    testRuntimeOnly("org.junit.jupiter", "junit-jupiter-engine")

    testImplementation("org.testfx", "testfx-core", "4.0.16-alpha")
    testImplementation("org.testfx", "testfx-junit5", "4.0.16-alpha")
    testImplementation("org.testfx", "openjfx-monocle", "jdk-11+26")

}

javafx {
    version = "11.0.2"
    modules("javafx.base", "javafx.controls", "javafx.fxml")
}

application {
    mainClass.set("com.ttbmp.cinehub.ui.desktop.CinehubApplication")
}

tasks.withType<Test>().all {
    if (project.hasProperty("headless")) {
        jvmArgs("-Djava.awt.headless=true -Dtestfx.robot=glass -Dtestfx.headless=true -Dprism.order=sw")
    }
}
