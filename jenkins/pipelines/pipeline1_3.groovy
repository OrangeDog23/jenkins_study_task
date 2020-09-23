def json_files
def chosen_config
def config

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
		ok 'Ok'
		parameters {
		    string(name: 'INPUT_STR', defaultValue: 'STRING', description: 'Input value')
		    text(name: 'INPUT_TEXT', defaultValue: 'TEXT', description: 'Input text')
		    booleanParam(name: 'SKIP_STEPS', defaultValue: false, description: 'skip this stage')
		}
	    }
	    when { equals expected: "false", actual: SKIP_STEPS }
	    steps {
		echo "INPUT_STR: ${INPUT_STR}"
		echo "INPUT_TEXT ${INPUT_TEXT}"
	    }
	}
	stage('Dynamic input values') {
	    steps {
		script {
		    json_files = get_json_files()
		    echo json_files.toString()
		    chosen_config = input id: 'chosen_config', message: 'Choose config',
			parameters: [
			choice (
			    choices: json_files, name: 'config',
			    description: 'choose appropriate config'
			)
		    ]
		    echo "${chosen_config}"
		}
	    }
	}
	stage('Get data from json config') {
	    steps {
		script {
		    config = readJSON file: chosen_config
		    config.each { key, value ->
			echo "Walked through key $key and value $value"
		    }
		}
	    }
	}
    }
}

def get_json_files() {
    def files = ''
    files = findFiles(glob: "project_1/deployment/config/*.json")
    def json_files = []
    for (file in files) {
	json_files.add(file.path.toString())
    }
    return json_files
}
