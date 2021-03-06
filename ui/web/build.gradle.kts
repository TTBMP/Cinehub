plugins {
    id("com.ttbmp.cinehub.java-application-conventions")
    id("org.springframework.boot") version "2.5.0"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
}

dependencies {
    implementation(project(":app"))

    implementation("org.springframework.boot", "spring-boot-starter-web")
    implementation("org.springframework.boot", "spring-boot-starter-thymeleaf")
    compileOnly("org.springframework.boot", "spring-boot-devtools")
    annotationProcessor("org.springframework.boot", "spring-boot-configuration-processor")
    testImplementation("org.springframework.boot", "spring-boot-starter-test")

    implementation("org.webjars", "bootstrap", "5.0.1")

    implementation("org.seleniumhq.selenium", "selenium-java", "3.141.59")
    implementation("io.github.bonigarcia", "webdrivermanager", "4.4.3")
    implementation("com.google.guava", "guava", "30.1.1-jre")
}

application {
    mainClass.set("com.ttbmp.cinehub.ui.web.CinehubApplication")
}
