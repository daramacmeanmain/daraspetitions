pipeline {
    agent any
    parameters {
        booleanParam(name: 'CONFIRM', description: 'Would you like to build this project?')
    }

    stages {
        stage('GetProject') {
            when {
                expression { params.CONFIRM == true }
            }
            steps {
                git 'https://github.com/daramacmeanmain/daraspetitions.git'
            }
        }

        stage('Build') {
            when {
                expression { params.CONFIRM == true }
            }
            steps {
                sh "mvn clean:clean"
            }
        }

        stage('Package') {
            when {
                expression { params.CONFIRM == true }
            }
            steps {
                sh "mvn package"
            }
        }

        stage ('Archive') {
            when {
                expression { params.CONFIRM == true }
            }
            steps {
                archiveArtifacts allowEmptyArchive: true,
                    artifacts: '**/daraspetitions*.war'
            }
        }

        stage ('Deploy') {
            when {
                expression { params.CONFIRM == true }
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