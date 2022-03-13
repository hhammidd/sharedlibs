def call(service_name, version) {
    git 'https://github.com/hhammidd/${service_name}.git'
    sh "docker build -t ${service_name}:${version} ."
}
