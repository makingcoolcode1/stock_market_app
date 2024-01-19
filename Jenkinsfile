pipeline {
    agent any

    environment {
        JAVA_HOME = tool 'JDK' // Assuming JDK is configured in Jenkins Tools
        PATH = "$JAVA_HOME/bin:${env.PATH}"
    }

    stages {
        stage('Build') {
            steps {
                script {
                    sh 'javac -cp . com/stockapp/stockappnogui.java'
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    sh 'java -cp . com.stockapp.stockappnogui'
                }
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }
}
