pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'hamzalamin/qatrat7ayat'
        DOCKER_TAG = 'latest'
    }

    stages {
        stage('Checkout') {
            steps {
                script {
                    // Configure git to use HTTP/1.1 and increase buffer size
                    sh '''
                        git config --global http.version HTTP/1.1
                        git config --global http.postBuffer 1048576000
                        git config --global http.lowSpeedLimit 0
                        git config --global http.lowSpeedTime 999999
                    '''

                    // Retry the checkout operation
                    retry(3) {
                        checkout([
                            $class: 'GitSCM',
                            branches: [[name: '*/main']],
                            userRemoteConfigs: [[url: 'https://github.com/hamzalamin/Qatrat-7ayat.git']],
                            extensions: [
                                [$class: 'CloneOption', depth: 1, noTags: false, reference: '', shallow: true],
                                [$class: 'CheckoutOption', timeout: 20]
                            ]
                        ])
                    }
                }
            }
        }

        stage('Build and Test') {
            steps {
                sh 'chmod +x ./mvnw || true'
                sh './mvnw clean package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                sh "docker build -t ${DOCKER_IMAGE}:${DOCKER_TAG} ."
            }
        }

        stage('Push Docker Image') {
            steps {
                withDockerRegistry(credentialsId: 'dockerhub', url: '') {
                    sh "docker push ${DOCKER_IMAGE}:${DOCKER_TAG}"
                }
            }
        }

        stage('Deploy') {
            steps {
                echo 'Déploiement fictif...'
            }
        }
    }

    post {
        always {
            echo 'Cleaning up ...'
            // Clean up git config changes
            sh '''
                git config --global --unset http.version || true
                git config --global --unset http.postBuffer || true
                git config --global --unset http.lowSpeedLimit || true
                git config --global --unset http.lowSpeedTime || true
            '''
        }
        success {
            echo 'Pipeline succeeded!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}