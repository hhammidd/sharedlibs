def call(service_name, version) {
    buildapp("${service_name}", {VERSION})
    script {
        currentBuild.description = "<b>service name: </b>${service_name}<br/><b>environment: </b>${environment}<br/><b>version:</b>${VERSION}<br/><b>PR:</b>TODO"
    }
}
