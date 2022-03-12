def call(version) {
    stage("build Image") {
        steps {
            script {
                dockerImage = docker.build registry + ":${version}"
            }
        }
    }

    stage("Push image") {
        steps {
            script {
                docker.withRegistry('') {
                    dockerImage.push()
                }
            }
        }
    }
}
