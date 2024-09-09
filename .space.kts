/**
 * JetBrains Space Automation
 * This Kotlin script file lets you automate build activities
 * For more info, see https://www.jetbrains.com/help/space/automation.html
 */

job("Build and publish Docker image") {
    startOn {
        gitPush {
            anyTagMatching {
                +"release/v*"
            }
        }
    }
    // both 'host.shellScript' and 'host.dockerBuildPush' run on the same host
    host("Build artifacts and a Docker image") {
        // Gradle build creates artifacts in ./build
        shellScript {
            content = """
                ./gradlew build
            """
        }

        dockerBuildPush {
            // Note that if Dockerfile is in the project root, we don't specify its path.
            // We also imply that Dockerfile takes artifacts from ./build and puts them to image
            // e.g. with 'ADD /build/app.jar /root/home/app.jar'

            val spaceRepo = "bastelquartier.registry.jetbrains.space/p/private/docker/docker-pdm"
            tags {
                +"$spaceRepo:${'$'}{git.tag}"
            }
        }
    }
}
