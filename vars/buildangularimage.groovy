def call(version) {
    sh "docker build -t geo-sale-app:${version} ."
}
