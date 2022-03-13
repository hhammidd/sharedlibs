def call(service_name) {
    git 'https://github.com/hhammidd/${service_name}.git'
    sh "docker build -t ${service_name}:3 ."
}
