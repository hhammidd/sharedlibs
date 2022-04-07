def call(version) {
    buildimage("${version}")
    script {
        currentBuild.description = "<b>environment: </b>${environment}<br/><b>version:</b>${VERSION}<br/><b>Image done:</b>${VERSION}"
    }
}
