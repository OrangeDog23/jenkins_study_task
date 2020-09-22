def environments = ['TEST', 'DEV', 'PROD']

for (String env_name : environments) {
    pipelineJob('PROJECT_2/pipeline2_1_' + env_name) {
	definition {
            cpsScm {
		scm {
                    git("$PROJECT_URL", '*/master', {node -> node / 'extensions' << '' })
		    scriptPath('jenkins/pipelines/pipeline2_1.groovy')
		}
            }
	}
	environmentVariables(PROJECT_ENVIRONMENT: env_name)	
    }
}

