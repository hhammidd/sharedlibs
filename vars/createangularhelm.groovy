def call(image, version, environment) {
    // remove the dir
    sh "rm -rf ~/apps/apps-helm-charts/helm-checkouts/${image}/charts"
    sh "rm -rf ~/apps/apps-helm-charts/helm-checkouts/${image}/charts/.git"
    sh "rm -rf ~/apps/apps-helm-charts/helm-checkouts/${image}/code"

    // get the helm.yaml variables
    sh "git clone https://github.com/hhammidd/${image}.git  ~/apps/apps-helm-charts/helm-checkouts/${image}/code"

    script {
        sh "cd  ~/apps/apps-helm-charts/helm-checkouts/${image}/code"
        APP_VERSION1 = sh(script: 'node -e "console.log(require(\'./package.json\').version);"', returnStdout: true)
        APP_VERSION = "${APP_VERSION1}".toString()
        APP_VERSION = "0.0.0"
        currentBuild.description = "<b>environment: </b>TODO<br/><b>version:</b>${APP_VERSION}<br/><b>Image done:</b>TODO"
    }

    // build image
    sh "docker build -t hhssaaffii/${service_name}:${APP_VERSION} ~/apps/apps-helm-charts/helm-checkouts/${image}/code"

    // push to docker hub
    sh "docker push hhssaaffii/${service_name}:${APP_VERSION}"

    // remove unwanted image version TODO

    // checkout last Chart
    sh "git clone https://github.com/hhammidd/Charts.git  ~/apps/apps-helm-charts/helm-checkouts/${image}/charts"

    // replace spring boot helm.yml with value.yaml
    sh "cp ~/apps/apps-helm-charts/helm-checkouts/${image}/code/helm.yml ~/apps/apps-helm-charts/helm-checkouts/${image}/charts/angular-apps/values.yaml"

    // cpy secrets to chart dir
//    sh "cp ~/apps/apps-helm-charts/secrets/${image}/secret.yaml ~/apps/apps-helm-charts/helm-checkouts/${image}/charts/angular-apps/templates"
    // remove unwanted code
//    sh "rm -rf ~/apps/apps-helm-charts/helm-checkouts/${image}/code"

    // start to deploy
    sh " helm upgrade --install ${image}  ~/apps/apps-helm-charts/helm-checkouts/${image}/charts/angular-apps --set tag=${APP_VERSION} --namespace=${environment}"

    sh "cd ~/apps/apps-helm-charts/helm-checkouts/sale-point-service/code"
    // increase version an push
//    sh "npm version patch"
    // make a new version
    script {
        def (value1, value2, value3) = VERSION.tokenize( '.' )
        NEW_VERSION_ = sh(script: 'npm version patch', returnStdout: true)
        NEW_VERSION = "${NEW_VERSION}".toString().substring(1)
    }

    // push
    sh "git add ."
    sh "git commit -m \'increament version to ${NEW_VERSION}\' --"
    sh "git push -u origin master"


}
