def call(version) {

    script {
        dockerImage = docker.build registry + ":${version}"
    }

    script {
        docker.withRegistry('') {
            dockerImage.push()
        }
    }
}
