
pipeline {
    agent any

    environment {
        JAVA_HOME = tool 'JDK'
        PATH = "$JAVA_HOME/bin:${env.PATH}"
    }

    stages{
        stage('Build'){
            steps{
                script{
                    sh 'java -co . com/stockapp/stockappnogui.java'
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
        always{
            checkout()
        }
    }
}