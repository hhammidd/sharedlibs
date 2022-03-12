def call(service_name, VERSION) {
    stage("git checkout") {
        steps {
            git 'https://github.com/hhammidd/${service_name}.git'
        }
    }


    stage("build-test") {
        steps {
            sh "mvn clean install"
        }
    }
    stage("build Image") {
        steps {
            script {
                dockerImage = docker.build registry + ":${VERSION}"
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
