
pipeline {
    agent any

    environment {
        JAVA_HOME = tool 'JDK11'
        PATH = "$JAVA_HOME/bin:${env.PATH}"
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

        stage('Test'){
            steps{
                script{
                    sh 'mvn test'
                }
            }
            
        }

        stage('Install Dependencies') {
            steps{
                sh 'mvn clean install'
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