def environments = ['TEST', 'DEV', 'PROD']
def pipelines = ['1', '2', '3']

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
	}
    }
}
//     // pipeline 1 definition
//     pipelineJob('PROJECT_1/' + env_name + '/pipeline1') {
// 	definition {
//             cpsScm {
// 		scm {
//                     git("$PROJECT_URL", '*/master', {node -> node / 'extensions' << '' })
// 		    scriptPath('jenkins/pipelines/pipeline1_1.groovy')
// 		}
//             }
// 	}
// 	environmentVariables(PROJECT_ENVIRONMENT: env_name)	
//     }
    
//     // pipeline 2 definition
//     pipelineJob('PROJECT_1/' + env_name + '/pipeline2') {
// 	definition {
//             cpsScm {
// 		scm {
//                     git("$PROJECT_URL", '*/master', {node -> node / 'extensions' << '' })
// 		    scriptPath('jenkins/pipelines/pipeline1_2.groovy')
// 		}
//             }
// 	}
// 	environmentVariables(PROJECT_ENVIRONMENT: env_name)	
//     }
// }
