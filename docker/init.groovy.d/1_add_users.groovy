import jenkins.model.*
import hudson.security.*

def env = System.getenv()

def jenkins = Jenkins.getInstance()
jenkins.setSecurityRealm(new HudsonPrivateSecurityRealm(false))

def user = jenkins.getSecurityRealm().createAccount(env.JENKINS_USER, env.JENKINS_PASS)
user.save()

jenkins.setAuthorizationStrategy(new GlobalMatrixAuthorizationStrategy())

jenkins.getAuthorizationStrategy().add(Jenkins.ADMINISTER, env.JENKINS_USER)

def userAlice = jenkins.getSecurityRealm().createAccount("Alice", "AlicePassword")
userAlice.save()
def userBob = jenkins.getSecurityRealm().createAccount("Bob", "BobPassword")
userBob.save()
jenkins.getAuthorizationStrategy().add(Jenkins.READ, "Alice")
jenkins.getAuthorizationStrategy().add(Jenkins.READ, "Bob")
jenkins.save()
print("--> users added successfully \n")

