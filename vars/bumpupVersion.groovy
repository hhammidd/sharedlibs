def call(image, branch) {
    sh 'mvn build-helper:parse-version versions:set -DnewVersion=\\${parsedVersion.majorVersion}.\\${parsedVersion.minorVersion}.\\${parsedVersion.nextIncrementalVersion}-SNAPSHOT versions:commit'
    // go to directory push it
    sh "cd ~/apps/apps-helm-charts/helm-checkouts/sale-point-service/code"
    sh "git add ."
    sh "git commit -m 'increament version to ' "
    sh "git push -u origin master"

    sh "rm -rf ~/apps/apps-helm-charts/helm-checkouts/${image}/code"
    // make a new version
//    script {
//        sh "mvn build-helper:parse-version versions:set -DnewVersion=\\${parsedVersion.majorVersion}.\\${parsedVersion.minorVersion}.\\${parsedVersion.nextIncrementalVersion}\\-SNAPSHOT versions:commit"
//    }
//
//    // go to directory push it
//    sh "cd ~/apps/apps-helm-charts/helm-checkouts/${image}/code"
//    sh "git commit -m 'increament version to ' -- /root/apps/apps-helm-charts/helm-checkouts/${image}/code/"
//    sh "git push -u origin master"
}
