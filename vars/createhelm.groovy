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
    sh "rm -rf ~/apps/apps-helm-charts/helm-checkouts/${image}/code"
    // TODO if the environment is tst, VERSION has SNAPSHOT, if deploy is PRD VERSION
    //
    sh " helm upgrade --install ${service_name}  ~/apps/apps-helm-charts/helm-checkouts/${service_name}/charts/springboot-services --set tag=${version} --namespace=${environment}"

    //TODO remove from below
    // make a new version
    sh "mvn build-helper:parse-version versions:set -DnewVersion=\\${parsedVersion.majorVersion}.\\${parsedVersion.minorVersion}.\\${parsedVersion.nextIncrementalVersion} versions:commit"

    // go to directory push it
    sh "cd ~/apps/apps-helm-charts/helm-checkouts/sale-point-service/code"
    sh "git commit -m 'increament version to ${bla}' -- /root/apps/apps-helm-charts/helm-checkouts/${image}/code/"
    sh "git push -u origin master"
}
