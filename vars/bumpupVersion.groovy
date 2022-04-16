def call() {
    // make a new version
    sh "mvn build-helper:parse-version versions:set -DnewVersion=\\${parsedVersion.majorVersion}.\\${parsedVersion.minorVersion}.\\${parsedVersion.nextIncrementalVersion}-SNAPSHOT versions:commit"

    // go to directory push it
    sh "cd ~/apps/apps-helm-charts/helm-checkouts/${image}/code"
    sh "git commit -m 'increament version to ' -- /root/apps/apps-helm-charts/helm-checkouts/${image}/code/"
    sh "git push -u origin master"
}
