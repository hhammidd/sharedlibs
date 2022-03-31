def call(image, version, environment) {
    // remove the dir
    sh "rm -rf ~/apps/apps-helm-charts/helm-checkouts/${image}/charts"
    sh "rm -rf ~/apps/apps-helm-charts/helm-checkouts/${image}/charts/.git"
    sh "rm -rf ~/apps/apps-helm-charts/helm-checkouts/${image}/code"

    // start to deploy
    sh " helm upgrade --install ${image}  ~/apps/apps-helm-charts/helm-checkouts/${image}/charts/angular-apps --set tag=${version} --namespace=${environment}"
}
