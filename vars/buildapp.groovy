def call(service_name, version) {
//    checkout([$class: 'GitSCM', branches: [[name: '*/${branch}']], userRemoteConfigs: [[url: 'git@git.servername:pathto/myrepo.git']]])
    git branch: '${branch}', url: 'https://github.com/hhammidd/${service_name}.git'
    sh "mvn build-helper:parse-version versions:set -DnewVersion=\\${parsedVersion.majorVersion}.\\${parsedVersion.minorVersion}.\\${parsedVersion.nextIncrementalVersion}\\-SNAPSHOT versions:commit" // TODO tetst
    sh "mvn clean install"
}
