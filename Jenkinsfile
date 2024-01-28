pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Checkout the source code from the specified Git repository
                checkout([$class:'GitSCM',
                    branches:[[name:'*/main']],
                    userRemoteConfigs:[[url:'https://github.com/ambiaahmed1/appE2E']]])
            }
        }

        stage('Build') {
            steps {
                // Build the Spring Boot application using Maven
                 script {
                            def mavenHome = tool 'Maven 3.8.4'
                            sh "${mavenHome}/bin/mvn clean install -DskipTests"
                        }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    def dockerProjectDirectory = '/var/lib/jenkins/workspace/App'
                    def dockerImageName = 'app'
                    def dockerImageTag = 'latest'

                    // Change to your Docker project directory
                    dir(dockerProjectDirectory) {
                        // Build Docker image
                        sh "docker build -t ${dockerImageName}:${dockerImageTag} ."
                    // Push Docker image to Docker Hub
                        withCredentials([string(credentialsId: 'docker-hub-credentials', variable: 'DOCKER_HUB_CREDENTIALS')]) {
                            sh "echo ${DOCKER_HUB_CREDENTIALS} | docker login -u ambiaahmed1 --password-stdin"
                            sh "docker push ambiaahmed1/${dockerImageName}:${dockerImageTag}"
                    }
                  }
                }
            }
        }
    }
}

