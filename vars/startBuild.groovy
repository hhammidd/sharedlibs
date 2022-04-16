def call(service_name, version) {
    // TODO drop SNAPSHOT if it is PRD
    buildapp("${service_name}", "${environment}")
    script {
        currentBuild.description = "<b>branch: </b>${branch}<br/><b>environment: </b>${environment}<br/><b>version:</b>${VERSION}<br/><b>PR:</b>TODO"
    }
}
