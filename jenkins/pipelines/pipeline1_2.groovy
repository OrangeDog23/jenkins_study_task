pipeline {
    agent any
    stages {
        stage('Hello stage') {
            steps { 
                echo 'Hello from pipeline 1_2'
            }
        }
    }
}
