FROM jenkins/jenkins:2.257
USER jenkins
COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN jenkins-plugin-cli -f /usr/share/jenkins/ref/plugins.txt
#COPY init.groovy.d /var/jenkins_home/init.groovy.d
COPY casc_configs /var/jenkins_home/casc_configs
ENV JENKINS_USER=Dmitry
ENV JENKINS_PASS=pwd12345
ENV JAVA_OPTS=-Djenkins.install.runSetupWizard=false
ENV PROJECT_URL=https://github.com/OrangeDog23/jenkins_study_task
ENV CASC_JENKINS_CONFIG=/var/jenkins_home/casc_configs
