def skip_stage = "${env.skip_stage}"
def input_string = "${env.input_string}"
def chosen_config = "${env.chosen_config}"
def config_path = "${env.CONFIG_PATH}"

pipeline {
    agent any
    stages {
        stage('Description stage') {
            steps { 
                echo "Pipeline for testing input values abilities for jenkins"
            }
        }
	stage('print input variables') {
	    when { equals expected: "false", actual: skip_stage }
	    steps {
		echo input_string
		echo chosen_config
	    }
	}
	stage('Get data from json config') {
	    steps {
		script {
		    config = readJSON file: config_path + chosen_config
		    config.each { key, value ->
			echo "Walked through key $key and value $value"
		    }
		}
	    }
	}
    }
}

// def get_json_files() {
//     def files = ''
//     files = findFiles(glob: "project_1/deployment/config/*.json")
//     def json_files = []
//     for (file in files) {
// 	json_files.add(file.path.toString())
//     }
//     return json_files
// }
