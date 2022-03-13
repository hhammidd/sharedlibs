def call() {
    sh "docker rmi \$(docker images --filter \"dangling=true\" -q --no-trunc)"
    //docker rmi $(docker images --filter "dangling=true" -q --no-trunc)
}
