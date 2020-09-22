pipeline {
    agent any
    stages {
        stage('Hello stage') {
            steps { 
                echo "Hello from pipeline 2_1 and environment: ${PROJECT_ENVIRONMENT}"
            }
        }
    }
}
