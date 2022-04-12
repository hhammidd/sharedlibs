def call(image, version, environment) {
    // remove the dir
    sh "rm -rf ~/apps/apps-helm-charts/helm-checkouts/${image}/charts"
    sh "rm -rf ~/apps/apps-helm-charts/helm-checkouts/${image}/charts/.git"
    sh "rm -rf ~/apps/apps-helm-charts/helm-checkouts/${image}/code"

    // get the helm.yaml variables
    sh "git clone https://github.com/hhammidd/${image}.git  ~/apps/apps-helm-charts/helm-checkouts/${image}/code"

    // check security
    sh "npm audit"

    def APP_VERSION = ""
    script { // get current version
        sh "cd  ~/apps/apps-helm-charts/helm-checkouts/${image}/code"
        def APP_VERSION1 = sh(script: 'node -e "console.log(require(\'./package.json\').version);"', returnStdout: true)
        APP_VERSION = "${APP_VERSION1}".toString()
//        APP_VERSION = "0.0.0"
        def (value1, value2, value3) = APP_VERSION1.tokenize('.')
        VERSION1 = Integer.parseInt(value3.trim())
        currentBuild.description = "<b>environment: </b>TODO<br/><b>version:</b>${APP_VERSION}<br/><b>Image done:</b>TODO"
    }

    // TODO make 0.0.xxx dynamic
    sh "docker build -t hhssaaffii/${image}:0.0.${VERSION1}  ~/apps/apps-helm-charts/helm-checkouts/${image}/code"

    // push to docker hub
    sh "docker push hhssaaffii/${image}:${APP_VERSION}"

    // checkout last Chart
    sh "git clone https://github.com/hhammidd/Charts.git  ~/apps/apps-helm-charts/helm-checkouts/${image}/charts"

    // replace spring boot helm.yml with value.yaml
    sh "cp ~/apps/apps-helm-charts/helm-checkouts/${image}/code/helm.yml ~/apps/apps-helm-charts/helm-checkouts/${image}/charts/angular-apps/values.yaml"

    // start to deploy
    sh " helm upgrade --install ${image}  ~/apps/apps-helm-charts/helm-checkouts/${image}/charts/angular-apps --namespace=${environment} --set tag=${APP_VERSION} "

    // make a new version and push it TODO make separate method and stage

    sh "cd  ~/apps/apps-helm-charts/helm-checkouts/${image}/code/"
    sh "npm version patch"
    sh "git push -u origin master"
    sh "rm -rf ~/apps/apps-helm-charts/helm-checkouts/${image}/code"

    sh "rm -rf ~/apps/apps-helm-charts/helm-checkouts/${image}/code"
}
