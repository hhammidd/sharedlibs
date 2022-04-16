def call(service_name, version) {
//def call(service_name, version, branch) {
//    buildapp("${service_name}", "${version}", "${branch}")
    buildapp("${service_name}", "${version}")
    script {
        currentBuild.description = "<b>branch: </b>${branch}<br/><b>environment: </b>${environment}<br/><b>version:</b>${VERSION}<br/><b>PR:</b>TODO"
    }
}
