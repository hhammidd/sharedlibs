def call(service_name, branch) {
//    checkout([$class: 'GitSCM', branches: [[name: '*/${branch}']], userRemoteConfigs: [[url: 'git@git.servername:pathto/myrepo.git']]])
    git branch: '${branch}', url: 'https://github.com/hhammidd/${service_name}.git'

    // if it  is env prd
    script {
        if ( "prd" == environment?.trim()) {
            sh "echo ${environment} here"
            sh "mvn versions:set -DremoveSnapshot=true -DgenerateBackupPoms=false"
        } else {
            sh "echo ${environment} there"
        }
    }

    sh "mvn clean install"
}
