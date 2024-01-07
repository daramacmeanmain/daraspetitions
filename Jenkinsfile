pipeline {
    agent any

    stages {
        stage('GetProject') {
            steps {
                git 'https://github.com/daramacmeanmain/daraspetitions.git'
            }
        }

        stage('Build') {
            steps {
                sh "mvn clean:clean"
            }
        }

        stage('Package') {
            steps {
                sh "mvn package"
            }
        }

        stage ('Archive') {
            steps {
                archiveArtifacts allowEmptyArchive: true,
                    artifacts: '**/daraspetitions*.war'
            }
        }

        stage ('Deploy') {
            input {
                message "Would you like to deploy?"
                ok "Deploy"
            }
            steps {
                sh 'docker build -f Dockerfile -t myapp . '
                sh 'docker run --name "daraspetitions_container" -p 9090:8080 --detach myapp:latest'
            }
        }
    }

    post {
        failure {
            sh 'docker kill daraspetitions_container'
            sh 'docker rm daraspetitions_container'
            sh 'docker run --name "daraspetitions_container" -p 9090:8080 --detach myapp:latest'
        }
    }
}