plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.3.4"
	id("io.spring.dependency-management") version "1.1.6"
	id("com.google.cloud.tools.jib") version "3.4.3"
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
	//WebClient 의존성 추가
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	//Xml 변환 라이브러리 의존성 추가
	implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.13.4")
	//mysql 관련 의존성 추가
	implementation ("org.springframework.boot:spring-boot-starter-data-jpa")
	runtimeOnly("com.mysql:mysql-connector-j")
	implementation("org.springframework.boot:spring-boot-starter-jdbc")

	implementation ("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.1.0") //SpringDoc OpenAPI를 사용하는 Spring Boot 프로젝트에 OpenAPI 문서 생성을 위한 라이브러리

	implementation("io.github.cdimascio:dotenv-kotlin:6.4.0")

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
	exclude("**/*")
	useJUnitPlatform()
}


jib {
	val activeProfile = System.getProperty("spring.profiles.active")
//////	val activeProfile = System.getenv("")
////	println("current active profile : ${activeProfile} ")
//
	println("Current active profile: $activeProfile")
//	if(activeProfile !="prod") {
//		to {
//			image = "docker-repo.minq.work/emergency-backend:latest"  // Docker 이미지 경로
//			auth {
//				username = System.getenv("REGISTRY_USER")  // 환경 변수에서 인증 정보 불러오기
//				password = System.getenv("REGISTRY_PASSWORD")
//			}
//		}
//	}
}


tasks.named<org.springframework.boot.gradle.tasks.run.BootRun>("bootRun") {

	val activeProfile = System.getProperty("spring.profiles.active")
	println(activeProfile)
	systemProperty("spring.profiles.active", activeProfile)
//	systemProperty("spring.profiles.active", System.getProperty("spring.profiles.active") ?: "default")
}