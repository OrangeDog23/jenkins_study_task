folder('PROJECT_1/DEV') {
    description('Project for dev purposes')
}

folder('PROJECT_1/TEST') {
    description('Project for test purposes')
}

folder('PROJECT_1/PROD') {
    description('Project for prod purposes')
}

pipelineJob('PROJECT_1/TEST/pipeline1') {
    definition {
        cpsScm {
            scm {
                git("$PROJECT_URL", '*/master', {node -> node / 'extensions' << '' })
		scriptPath('jenkins/pipelines/pipeline1_1.groovy')
            }
        }
    }
    environmentVariables(ENVIRONMENT: 'TEST')
}


pipelineJob('PROJECT_1/DEV/pipeline1') {
    definition {
        cpsScm {
            scm {
                git("$PROJECT_URL", '*/master', {node -> node / 'extensions' << '' })
		scriptPath('jenkins/pipelines/pipeline1_1.groovy')
            }
        }
    }
    environmentVariables {
	environmentVariables(ENVIRONMENT: 'DEV')
    }
}

pipelineJob('PROJECT_1/PROD/pipeline1') {
    definition {
        cpsScm {
            scm {
                git("$PROJECT_URL", '*/master', {node -> node / 'extensions' << '' })
		scriptPath('jenkins/pipelines/pipeline1_1.groovy')
            }
        }
    }
    environmentVariables(ENVIRONMENT: 'PROD')
}


