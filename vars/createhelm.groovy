def call(image, version) { // TODO get branch def call(image, branch) {
    // remove the dir
    sh "rm -rf ~/apps/apps-helm-charts/helm-checkouts/${image}/charts"
    sh "rm -rf ~/apps/apps-helm-charts/helm-checkouts/${image}/charts/.git"
    sh "rm -rf ~/apps/apps-helm-charts/helm-checkouts/${image}/code"

    // get the helm.yaml variables
    sh "git clone https://github.com/hhammidd/${image}.git  ~/apps/apps-helm-charts/helm-checkouts/${image}/code"

    // checkout last Chart
    sh "git clone https://github.com/hhammidd/Charts.git  ~/apps/apps-helm-charts/helm-checkouts/${image}/charts"

    // replace spring boot helm.yml with value.yaml
    sh "cp ~/apps/apps-helm-charts/helm-checkouts/${image}/code/helm.yml ~/apps/apps-helm-charts/helm-checkouts/${image}/charts/springboot-services/values.yaml"

    // cpy secrets to chart dir
    sh "cp ~/apps/apps-helm-charts/secrets/${image}/secret.yaml ~/apps/apps-helm-charts/helm-checkouts/${image}/charts/springboot-services/templates"
    // remove unwanted code
    // TODO if the environment is tst, VERSION has SNAPSHOT, if deploy is PRD VERSION
    //
    sh " helm upgrade --install ${service_name}  ~/apps/apps-helm-charts/helm-checkouts/${service_name}/charts/springboot-services --set tag=${version} --namespace=${environment}"

    //TODO remove from below
    // make a new version

}
