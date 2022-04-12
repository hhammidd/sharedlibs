def call(image) {
    // check security
    // remove the dir
    sh "rm -rf ~/apps/apps-helm-charts/helm-checkouts/${image}/charts"
    sh "rm -rf ~/apps/apps-helm-charts/helm-checkouts/${image}/charts/.git"
    sh "rm -rf ~/apps/apps-helm-charts/helm-checkouts/${image}/code"

    // get the helm.yaml variables
    sh "git clone https://github.com/hhammidd/${image}.git  ~/apps/apps-helm-charts/helm-checkouts/${image}/code"

    sh "cd  ~/apps/apps-helm-charts/helm-checkouts/${image}/code"
    //    sh "npm i --package-lock-only" // No need maybe
    // check security
    sh "npm update && npm audit"
}
