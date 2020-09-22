folder('PROJECT_1') {
    displayName('Project 1')
    description('Folder for project 1')
    authorization {
        permissionAll('Alice')
    }
}

freeStyleJob('PROJECT_1/project_1_seed') {
    logRotator(-1, 10)
    scm {
	git('$PROJECT_URL', '*/master', {node -> node / 'extensions' << '' })
    }
    steps {
	shell('echo "Creating project 1 seed job"')
	dsl {
	    external('jenkins/src/com/domain/project/dsl/jobs/createAutomationJobsProject1.groovy')
	}
    }
}

folder('PROJECT_2') {
    displayName('Project 2')
    description('Folder for project 2')
}

freeStyleJob('PROJECT_2/project_2_seed') {
    logRotator(-1, 10)
    scm {
	git('$PROJECT_URL', '*/master', {node -> node / 'extensions' << '' })
    }
    steps {
	shell('echo "Creating project 2 seed job"')
	dsl {
	    external('jenkins/src/com/domain/project/dsl/jobs/createAutomationJobsProject2.groovy')
	}
    }
}


