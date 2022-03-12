def call(service_name) {
    git 'https://github.com/hhammidd/${service_name}.git'
    sh "mvn clean install"
}
