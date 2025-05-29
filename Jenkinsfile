pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'hamzalamin/qatrat7ayat'
        DOCKER_TAG = 'latest'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/hamzalamin/Qatrat-7ayat.git'
            }
        }

        stage('Check JDK') {
            steps {
                sh '''
                 ssh jenkins-agent
                 java -version
                 which javac
                 echo $JAVA_HOME
                '''
            }
        }

        stage('Build and Test') {
            steps {
                sh 'chmod +x ./mvnw || true'
                sh './mvnw clean package -DskipTests -P!with-liquibase'
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
        }
        success {
            echo 'Pipeline succeeded!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}