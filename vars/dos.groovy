def call(service_name, version) {
    stage("start build process") {
        steps {
//                buildapp("${service_name}")
            buildapp("${service_name}", "${VERSION}")
            script {
                currentBuild.description = "<b>environment: </b>${environment}<br/><b>version:</b>${VERSION}<br/><b>PR:</b>TODO"
            }
        }
    }
}
