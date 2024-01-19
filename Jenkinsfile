
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
                    sh 'java -cp . com/stockapp/stockappnogui.java'
                }
            }
        }

        stage('Test'){
            steps{
                script{
                    sh 'java -cp . com.stockapp.stockappnogui'
                }
            }
            
        }

        stage('Install Dependencies') {
            steps{
                sh 'mvn clean install'
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