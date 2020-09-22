folder('PROJECT_1/DEV') {
    description('Project for dev purposes')
}

folder('PROJECT_1/TEST') {
    description('Project for test purposes')
}

folder('PROJECT_1/PROD') {
    description('Project for prod purposes')
}

pipelineJob('PROJECT_1/PROD/pipeline1') {
    definition {
        cpsScm {
            scm {
                git('$PROJECT_URL', '*/master', {node -> node / 'extensions' << '' })
		scriptPath('jenkins/src/com/domain/project/dsl/factory/GenerateJobFactory1.groovy')
            }
        }
    }
}
