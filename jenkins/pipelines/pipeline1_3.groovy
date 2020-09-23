pipeline {
    agent any
    stages {
        stage('Description stage') {
            steps { 
                echo "Pipeline for testing input values abilities for jenkins"
            }
        }
	stage('Static input values') {
	    input {
		message 'Enter test values'
		ok 'Yes'
		parameters {
		    string(name: 'INPUT_STR', defaultValue: 'STRING', description: 'Input value')
		    booleanParam(name 'SKIP_P1', defaultValue: false, description: 'Skip step 1')
		}
	    }
	    steps {
	    }
	}
    }
}
