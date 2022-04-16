def call(service_name, version) {
    // remove the dir
    sh "rm -rf ~/apps/apps-helm-charts/helm-checkouts/${service_name}/charts"
    sh "rm -rf ~/apps/apps-helm-charts/helm-checkouts/${service_name}/charts/.git"
    sh "rm -rf ~/apps/apps-helm-charts/helm-checkouts/${service_name}/code"

    // get the helm.yaml variables
    sh "git clone https://github.com/hhammidd/${service_name}.git  ~/apps/apps-helm-charts/helm-checkouts/${service_name}/code"

    // checkout last Chart
    sh "git clone https://github.com/hhammidd/Charts.git  ~/apps/apps-helm-charts/helm-checkouts/${service_name}/charts"

    // replace spring boot helm.yml with value.yaml
    sh "cp ~/apps/apps-helm-charts/helm-checkouts/${service_name}/code/helm.yml ~/apps/apps-helm-charts/helm-checkouts/${service_name}/charts/springboot-services/values.yaml"

    // cpy secrets to chart dir
    sh "cp ~/apps/apps-helm-charts/secrets/${service_name}/secret.yaml ~/apps/apps-helm-charts/helm-checkouts/${service_name}/charts/springboot-services/templates"
    // remove unwanted code
    sh "rm -rf ~/apps/apps-helm-charts/helm-checkouts/${service_name}/code"

    // start to deploy
    sh " helm upgrade --install ${service_name}  ~/apps/apps-helm-charts/helm-checkouts/${service_name}/charts/springboot-services --set tag=${version} --namespace=${environment}"
}
