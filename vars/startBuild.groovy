def call(service_name, version) {
//def call(service_name, version, branch) {
//    buildapp("${service_name}", "${version}", "${branch}")
    buildapp("${service_name}", "${version}")
    sh "mvn build-helper:parse-version versions:set -DnewVersion=\\${parsedVersion.majorVersion}.\\${parsedVersion.minorVersion}.\\${parsedVersion.nextIncrementalVersion}\\-SNAPSHOT versions:commit" // TODO tetst
    script {
        currentBuild.description = "<b>branch: </b>${branch}<br/><b>environment: </b>${environment}<br/><b>version:</b>${VERSION}<br/><b>PR:</b>TODO"
    }
}
