plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.3.4"
	id("io.spring.dependency-management") version "1.1.6"
}

group = "daelim"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	//레트로핏2 의존성 추가
	implementation("com.squareup.retrofit2:retrofit:2.9.0")
	//WebClient 의존성 추가
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	//Xml 변환 라이브러리 의존성 추가
	implementation("com.squareup.retrofit2:converter-simplexml:2.9.0")
	implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.13.4")
	//implementation("javax.xml.bind:jaxb-api:2.3.1")
	//implementation("org.glassfish.jaxb:jaxb-runtime:2.3.1")

	runtimeOnly("com.mysql:mysql-connector-j")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
