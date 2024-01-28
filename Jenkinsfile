pipeline {
  agent any

  stages {
    stage('Checkout') {
      steps {
        // Checkout the source code from the specified Git repository
        checkout([$class: 'GitSCM', branches: [
          [name: '*/main']
        ], userRemoteConfigs: [
          [url: 'https://github.com/ambiaahmed1/appE2E']
        ]])
      }
    }

    stage('Build Docker Image') {
      steps {
        script {
          def dockerProjectDirectory = '/var/lib/jenkins/workspace/App'
          def dockerImageName = 'App'
          def dockerImageTag = 'latest'
          // Change to your Docker project directory
          dir(dockerProjectDirectory) {
            // Build Docker image
            sh "docker build -t ${dockerImageName}:${dockerImageTag} ."
          }
        }
      }
    }

    stage('Build') {
      steps {
        // Build the Spring Boot application using Maven
        sh 'mvn clean install'
      }
    }

    // Add more stages for additional build, test, deploy steps, etc.
  }
}