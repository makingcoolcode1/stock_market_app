
pipeline {
    agent any

    tools {
        maven 'Maven 3.9.6'
        jdk 'JDK11'
    }

    stages{

        stage('Checkout Code'){
            steps{
                checkout scm
            }
        }
    
        stage('Build'){
            steps{
                script{
                    sh 'mvn compile'
                }
            }
        }

        stage('Test'){
            steps{
                script{
                    sh 'mvn test'
                }
            } 
        }

        stage('Install Dependencies') {
            steps{
                script {
                    sh 'mvn clean install'
                }
            }
        }

        stage ('Run Application') {
            steps{
                script{
                    sh 'java -cp target/classes com.stockapp1.stockappnogui'
                }
            }
        }
    }

    post{
        success{
            echo 'Pipeline Build Success!'
        }

        failure{
            echo 'Pipeline Failed!'
        }
    }
}