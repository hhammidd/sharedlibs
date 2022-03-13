def call(service_name, environment) {

    pipeline {

//        environment {
//            registry = "hhssaaffii/${service_name}"
//            registryCredential = ''
//            dockerImage = ''
//            //Use Pipeline Utility Steps plugin to read information from pom.xml into env variables
//            IMAGE = readMavenPom().getArtifactId()
//            VERSION = readMavenPom().getVersion()
//        }
        agent any
        stages {
//        stage("get version") {
//            steps {
//                script {
//                    if ("${IMAGE_TAG}"?.trim()) {
//                        stage('Input pam') {
//                            sh 'echo ${IMAGE_TAG}'
//                        }
//                    } else {
//                        stage('current') {
//                            sh 'echo ${VERSION}'
//                        }
//                    }
//                }
//            }
//        }
            stage("start build process") {
                steps {
                    buildapp("${service_name}")
                }
            }

            stage("start build and push image") {
                steps {
                    buildimage("${VERSION}")
                }
            }

            stage("deploy") {
                steps {
                    createhelm("${IMAGE}")
                }
            }

        }
    }

}
