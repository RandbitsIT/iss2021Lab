/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Java application project to get you started.
 * For more details take a look at the 'Building Java & JVM projects' chapter in the Gradle
 * User Manual available at https://docs.gradle.org/6.7.1/userguide/building_java_projects.html
 */

plugins {
    // Apply the application plugin to add support for building a CLI application in Java.
    application
}

repositories {
    // Use JCenter for resolving dependencies.
    jcenter()
}

dependencies {
    // Use JUnit test framework.
    testImplementation("junit:junit:4.13")
    implementation("junit:junit:4.13")

    // This dependency is used by the application.
    implementation("com.google.guava:guava:29.0-jre")
}

application {
    // Define the main class for the application.
    mainClass.set("demo.App")
}

/*
CHANGE PROPERTIES
*/
version = 1.0
/*
tasks.jar {
    manifest {
        attributes(mapOf("Implementation-Title" to project.name,
                "Implementation-Version" to project.version))
    }
}
*/
tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "demo.App"
    }
}

/*
ADDED TASKS
 */
println(" ...... build in app  ")

task("hello") {
    doLast {
        println("Hello world from app - this=${this}")
    }
}
/*
tasks.register("hello") {
    doLast {
        println("Hello world from app - this=${this}")
    }
}
*/
task("notgood"){
    println("Message shown during the configuration phase: no task action defined in ${this}")
}

tasks.register("count") {
    doLast {
        repeat(4) { print("$it ") }
        println()
    }
}

tasks.named("count"){
    doFirst { println("count STARTS ")}
}

tasks.register("welcome") { //could be declared before hello and count
    dependsOn("hello")
    dependsOn("count")
    doLast {
        println("Welcome task that depends on tasks hello and count ")
    }
}

repeat(4) { counter ->
    //println("app task$counter in $tasks"   )
    tasks.register("task$counter") {
        doLast {
            println("I'm task number $counter")
        }
    }
}

tasks.register<Copy>("mycopy") {    //Registers a new task of type Copy and configures it
    println("projectDir= $projectDir") //GradleIntro\app
    println("buildDir  = $buildDir")   //GradleIntro\app\build
    from("$projectDir/../app/src"){
        exclude( "**/main/resources", "**/test" )
    }
    into( "../copiedFiles" )
}

tasks.register("myclean") {
    doLast {
        if ( !buildDir.deleteRecursively() ) {
            throw IllegalStateException("Cannot delete $buildDir")
        }
    }
}

/*
INPUT-OUTPUT dependencies
 */
task("t1"){
    //Configuration
    inputs.property("version",version)
    //outputs.property()
    //Action
    doLast{
        version="1.1"
        println("task t1 $version")
    }
}

task("t2"){
    doLast{
        println("task t2 $version")
    }
}