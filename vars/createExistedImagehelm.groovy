def call(image, version, environment) {
    // remove the dir
    sh "rm -rf ~/apps/apps-helm-charts/helm-checkouts/${image}/charts"
    sh "rm -rf ~/apps/apps-helm-charts/helm-checkouts/${image}/charts/.git"
    sh "rm -rf ~/apps/apps-helm-charts/helm-checkouts/${image}/code"

    // checkout last Chart
    sh "git clone https://github.com/hhammidd/Charts.git  ~/apps/apps-helm-charts/helm-checkouts/${image}/charts"

    // replace spring boot helm.yml with value.yaml
    sh "cp ~/apps/apps-helm-charts/helm-checkouts/${image}/code/helm.yml ~/apps/apps-helm-charts/helm-checkouts/${image}/charts/angular-apps/values.yaml"

    sh " helm upgrade --install ${image}  ~/apps/apps-helm-charts/helm-checkouts/${image}/charts/angular-apps --namespace=${environment} --set tag=${version} "

    sh "rm -rf ~/apps/apps-helm-charts/helm-checkouts/${image}/code"
}
