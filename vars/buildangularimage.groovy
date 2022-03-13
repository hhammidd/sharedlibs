def call(servicename) {
    sh "docker build -t ${servicename}:3 ."
}
