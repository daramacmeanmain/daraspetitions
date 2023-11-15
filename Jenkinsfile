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
                archiveArtifacts allowEmptyASrchive: true,
                    artifacts: '**/daraspetitions*.war'
            }
        }

        stage ('Deploy') {
            steps {
                sh 'docker build -f Dockerfile -t myapp . '
            }
        }
    }
}