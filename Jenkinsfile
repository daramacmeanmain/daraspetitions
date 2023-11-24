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
            steps {
                sh 'docker build -f Dockerfile -t myapp . '
                sh 'docker kill mycontainer'
                sh 'docker rm mycontainer'
                sh 'docker run --name "mycontainer" -p 9090:8080 --detach myapp:latest'
            }
        }
    }

    post {
        failure {
            sh 'docker run --name "mycontainer" -p 9090:8080 --detach myapp:latest'
        }
    }
}