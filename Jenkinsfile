// tkteb script dyal jenkins hna

pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'hamzalamin/qatrat7ayat';
        DOCKER_TAG = 'latest';
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/hamzalamin/Qatrat-7ayat.git'
            }
        }
        stage('build and test') {
           steps {
               script {
                   docker.image('maven:3.9-eclipse-temurin-22').inside('-v $PWD:/app') {
                       sh 'chmod +x ./mvnw'
                       sh './mvnw clean package'
                   }
               }
           }
        }

        stage('Docker Build') {
            steps {
                script {
                    sh 'docker build -t $DOCKER_IMAGE:$DOCKER_TAG .'
                }
            }
        }

         stage('Push Docker Image') {
             steps {
                 script {
                     echo "DEBUG: Starting Push Docker Image stage..."
                 }
                 withDockerRegistry([credentialsId: 'dockerhub', url: '']) {
                     sh 'docker info'
                     sh "docker push ${DOCKER_IMAGE}:${DOCKER_TAG}"
                 }
                 script {
                     echo "DEBUG: Docker Image pushed successfully!"
                 }
             }
         }

         stage('Deploy') {
             steps {
                 script {
                     echo 'yeees'
                 }
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