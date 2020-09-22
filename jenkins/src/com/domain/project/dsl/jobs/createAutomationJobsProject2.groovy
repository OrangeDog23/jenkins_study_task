pipelineJob('PROJECT_2/pipeline1') {
    definition {
        cpsScm {
            scm {
                git('$PROJECT_URL', '*/master', {node -> node / 'extensions' << '' })
		scriptPath('jenkins/src/com/domain/project/dsl/factory/GenerateJobFactory1.groovy')
            }
        }
    }
}
