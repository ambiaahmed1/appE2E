pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout([$class:'GitSCM',
                    branches:[[name:'*/main']],
                    userRemoteConfigs:[[url:'https://github.com/ambiaahmed1/appE2E']]])
            }
        }

        stage('Build Application') {
            steps {
                // Build the Spring Boot application using Maven
                script {
                    def mavenHome = tool 'Maven 3.8.4'
                    sh "${mavenHome}/bin/mvn clean install -DskipTests"
                }
            }
        }

        stage('Build and Push Docker Image') {
            steps {
                script {
                    def dockerProjectDirectory = '/var/lib/jenkins/workspace/App'
                    def dockerImageName = 'app'
                    def dockerImageTag = 'latest'

                    // Change to your Docker project directory
                    dir(dockerProjectDirectory) {
                        // Build Docker image
                        sh "docker build -t ${dockerImageName}:${dockerImageTag} ."

                        // Retrieve Docker Hub credentials
                        withCredentials([usernamePassword(credentialsId: 'docker-hub-credentials', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                         // Log in to Docker Hub using --password-stdin
                            sh "echo $DOCKER_PASSWORD | docker login -u $DOCKER_USERNAME --password-stdin"

                            // Push Docker image to Docker Hub
                            sh "docker push ${dockerImageName}:${dockerImageTag}"
                        }
                    }
                }
            }
        }
    }
}
