def environments = ['TEST', 'DEV', 'PROD']
def pipelines = ['1', '2', '3']
def config_path = "project_1/deployment/config/"
// def variables = fetch_variables_from_config(config_path)

for (String env_name : environments) {
    // create folders hierarchy
    folder('PROJECT_1/' + env_name) {
        description("Project for ${env_name} purposes")
    }
    for (String pipeline_num : pipelines) {
	pipelineJob('PROJECT_1/' + env_name + '/pipeline' + pipeline_num) {
	    definition {
		cpsScm {
		    scm {
			git("$PROJECT_URL", '*/master', {node -> node / 'extensions' << '' })
			scriptPath("jenkins/pipelines/pipeline1_${pipeline_num}.groovy")
		    }
		}
	    }
	    environmentVariables(PROJECT_ENVIRONMENT: env_name)
	    if (pipeline_num == '3') {
		environmentVariables{
		    env('CONFIG_PATH', config_path)
		    env('PROJECT_ENVIRONMENT', env_name)
		}
		parameters {
		    booleanParam('skip_stage', false)
		    stringParam('input_string', 'Example string', 'string for evaluation')
		    choiceParam('chosen_config', get_config_files(config_path), 'choose config for pipeline evaluation')
		    // variables.eachWithIndex {config, i ->
		    // 	textParam("config" + (i+1), config, 'config ' + (i+1) + ' descriptions')
		    // }
		}
	    }
	}
    }
}


def get_config_files(config_path) {
    def files = []
    dh = new File("$WORKSPACE/" + config_path)
    dh.eachFile {
	files.add(it.name)
    }
    return files
}

def fetch_variables_from_config(config_path) {
    configs = []
    dh = new File(config_path)
    dh.eachFile {
	def config_file = ''
	it.eachLine { line ->
	    config_file = config_file + line
	}
	configs.add(config_file)
    }
    return configs
}
