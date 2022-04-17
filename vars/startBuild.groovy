def call(service_name, version) {
    buildapp("${service_name}", "${environment}")
    script {
        currentBuild.description = "<b>branch: </b>${branch}<br/><b>environment: </b>${environment}<br/><b>version:</b>${version}<br/><b>PR:</b>TODO"
    }
}
