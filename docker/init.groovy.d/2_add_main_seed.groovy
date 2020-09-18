import hudson.model.FreeStyleProject
import hudson.plugins.git.GitSCM
import hudson.plugins.git.UserRemoteConfig
import hudson.plugins.git.BranchSpec
import hudson.triggers.SCMTrigger
import javaposse.jobdsl.plugin.*
import jenkins.model.Jenkins

println "--> create main seed-job"

def gitSeedScmURL = System.getenv("PROJECT_URL")
assert gitSeedScmURL != null : "No SEED_JOBS_URL env var provided, but required"
def gitSeedCredentialsId = System.getenv("CREDS_ID")

println "--> seed jobs url: " + gitSeedScmURL

jenkins = Jenkins.instance;

jobName = "main_seed";
branch = "*/master"

jenkins.items.findAll { job -> job.name == jobName }
  .each { job -> job.delete() }

gitTrigger = new SCMTrigger("H * * * *");
dslBuilder = new ExecuteDslScripts()

dslBuilder.setTargets("jenkins/main_seed")
dslBuilder.setUseScriptText(false)
dslBuilder.setIgnoreExisting(false)
dslBuilder.setIgnoreMissingFiles(false)
dslBuilder.setRemovedJobAction(RemovedJobAction.DISABLE)
dslBuilder.setRemovedViewAction(RemovedViewAction.IGNORE)
dslBuilder.setLookupStrategy(LookupStrategy.SEED_JOB)

dslProject = new hudson.model.FreeStyleProject(jenkins, jobName);
dslProject.scm = new GitSCM(gitSeedScmURL);
dslProject.scm.branches = [new BranchSpec(branch)];
if (gitSeedCredentialsId != null) {
  println "--> seed jobs credentials: " + gitSeedCredentialsId
  config = new UserRemoteConfig(gitSeedScmURL, null, null, gitSeedCredentialsId)
  dslProject.scm.userRemoteConfigs = [config];
}

// dslProject.addTrigger(gitTrigger);
dslProject.createTransientActions();
dslProject.getPublishersList().add(dslBuilder);

jenkins.add(dslProject, jobName);

gitTrigger.start(dslProject, true);
