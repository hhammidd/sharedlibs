def call(service_name, version) {
    git 'https://github.com/hhammidd/${service_name}.git'
//    sh "git clone https://github.com/hhammidd/${service_name}.git"
    sh "echo ${version}"
    sh "docker build -t ${service_name}:${version} ."
}
