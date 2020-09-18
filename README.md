# jenkins_study_task
Study task for jenkins-dsl-plugin test, etc.

# Requirements
docker


# How to build

cd docker/
docker build -t custom-jenkins:latest .

# How to run

docker run -p 8080:8080 custom-jenkins
