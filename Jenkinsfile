pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'hamzalamin/qatrat7ayat'
        DOCKER_TAG = 'latest'
        POSTGRES_DB = 'prod_db'
        POSTGRES_USER = 'postgres'
        POSTGRES_PASSWORD = 123
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

        stage('Setup Database') {
            steps {
                script {
                    // Start PostgreSQL container for testing
                    sh '''
                        docker run -d --name postgres-test \
                            -e POSTGRES_DB=${POSTGRES_DB} \
                            -e POSTGRES_USER=${POSTGRES_USER} \
                            -e POSTGRES_PASSWORD=${POSTGRES_PASSWORD} \
                            -p 5433:5432 \
                            postgres:15-alpine
                    '''

                    // Wait for PostgreSQL to be ready
                    sh '''
                        echo "Waiting for PostgreSQL to start..."
                        for i in {1..30}; do
                            if docker exec postgres-test pg_isready -U ${POSTGRES_USER} -d ${POSTGRES_DB}; then
                                echo "PostgreSQL is ready!"
                                break
                            fi
                            echo "Waiting... ($i/30)"
                            sleep 2
                        done
                    '''
                }
            }
        }

        stage('Build and Test') {
            steps {
                sh 'chmod +x ./mvnw || true'
                script {
                    // First, try to download dependencies separately
                    retry(3) {
                        sh './mvnw dependency:go-offline -U --batch-mode'
                    }

                    // Then build the project with database available
                    retry(2) {
                        sh './mvnw clean package -DskipTests --batch-mode'
                    }
                }
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
            // Stop and remove PostgreSQL container
            sh '''
                docker stop postgres-test || true
                docker rm postgres-test || true
            '''
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