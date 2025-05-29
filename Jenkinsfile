pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'hamzalamin/qatrat7ayat'
        DOCKER_TAG = 'latest'
        POSTGRES_DB = 'test_db'
        POSTGRES_USER = 'postgres'
        POSTGRES_PASSWORD = '123'
        // Skip Liquibase during build
        SPRING_LIQUIBASE_ENABLED = 'false'
        SPRING_PROFILES_ACTIVE = 'test'
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

        stage('Setup Test Database') {
            steps {
                script {
                    def dockerAvailable = sh(script: 'which docker', returnStatus: true) == 0
                    if (dockerAvailable) {
                        echo "Setting up PostgreSQL test database..."
                        sh '''
                            # Stop and remove existing container if it exists
                            docker stop postgres-test || true
                            docker rm postgres-test || true

                            # Start PostgreSQL container for testing
                            docker run -d \
                                --name postgres-test \
                                -e POSTGRES_DB=${POSTGRES_DB} \
                                -e POSTGRES_USER=${POSTGRES_USER} \
                                -e POSTGRES_PASSWORD=${POSTGRES_PASSWORD} \
                                -p 5432:5432 \
                                postgres:15-alpine

                            # Wait for PostgreSQL to be ready
                            echo "Waiting for PostgreSQL to start..."
                            sleep 20

                            # Test connection
                            docker exec postgres-test pg_isready -U ${POSTGRES_USER}
                        '''
                    } else {
                        echo "Docker not available, will build without database"
                    }
                }
            }
        }

        stage('Build and Test') {
            steps {
                sh 'chmod +x ./mvnw || true'
                script {
                    // Set database connection properties for build
                    def buildProps = ""
                    def dockerAvailable = sh(script: 'which docker', returnStatus: true) == 0

                    if (dockerAvailable) {
                        buildProps = '''
                            -Dspring.datasource.url=jdbc:postgresql://localhost:5432/test_db \
                            -Dspring.datasource.username=postgres \
                            -Dspring.datasource.password=123 \
                            -Dspring.liquibase.enabled=true \
                            -Dspring.profiles.active=test
                        '''
                    } else {
                        buildProps = '''
                            -Dspring.liquibase.enabled=false \
                            -Dspring.profiles.active=test
                        '''
                    }

                    // First, try to download dependencies separately
                    retry(3) {
                        sh './mvnw dependency:go-offline -U --batch-mode'
                    }

                    // Then build the project
                    retry(2) {
                        sh """
                            ./mvnw clean package -DskipTests --batch-mode ${buildProps}
                        """
                    }
                }
            }
        }

        stage('Run Tests') {
            steps {
                script {
                    def dockerAvailable = sh(script: 'which docker', returnStatus: true) == 0

                    if (dockerAvailable) {
                        sh '''
                            ./mvnw test --batch-mode \
                                -Dspring.datasource.url=jdbc:postgresql://localhost:5432/test_db \
                                -Dspring.datasource.username=postgres \
                                -Dspring.datasource.password=123 \
                                -Dspring.profiles.active=test
                        '''
                    } else {
                        echo "Skipping tests - no database available"
                    }
                }
            }
        }

        stage('Docker Build') {
            steps {
                script {
                    def dockerAvailable = sh(script: 'which docker', returnStatus: true) == 0
                    if (dockerAvailable) {
                        sh "docker build -t ${DOCKER_IMAGE}:${DOCKER_TAG} ."
                    } else {
                        echo "Docker not available, skipping Docker build"
                        currentBuild.result = 'UNSTABLE'
                    }
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    def dockerAvailable = sh(script: 'which docker', returnStatus: true) == 0
                    if (dockerAvailable) {
                        withDockerRegistry(credentialsId: 'dockerhub', url: '') {
                            sh "docker push ${DOCKER_IMAGE}:${DOCKER_TAG}"
                        }
                    } else {
                        echo "Docker not available, skipping Docker push"
                    }
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
            script {
                // Clean up Docker containers if Docker is available
                def dockerAvailable = sh(script: 'which docker', returnStatus: true) == 0
                if (dockerAvailable) {
                    sh '''
                        docker stop postgres-test || true
                        docker rm postgres-test || true
                    '''
                }
                // Clean up git config changes
                sh '''
                    git config --global --unset http.version || true
                    git config --global --unset http.postBuffer || true
                    git config --global --unset http.lowSpeedLimit || true
                    git config --global --unset http.lowSpeedTime || true
                '''
            }
        }
        success {
            echo 'Pipeline succeeded!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}