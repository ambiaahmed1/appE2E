pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Checkout the source code from the specified Git repository
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], userRemoteConfigs: [[url: 'https://github.com/ambiaahmed1/appE2E']]])
            }
        }

        // Add more stages for additional build, test, deploy steps, etc.
    }

    post {
        always {
            // Clean up resources, if needed
        }
    }
}