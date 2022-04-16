def call(service_name, version) {
//    checkout([$class: 'GitSCM', branches: [[name: '*/${branch}']], userRemoteConfigs: [[url: 'git@git.servername:pathto/myrepo.git']]])
    git branch: '${branch}', url: 'https://github.com/hhammidd/${service_name}.git'
    sh "mvn clean install"
}
