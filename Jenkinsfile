
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
                    def mvnCmd = tool 'Maven 3.9.6'
                    sh "${mavenCmd} compile"
                }
            }
        }

        stage('Test'){
            steps{
                script{
                    def mvnCmd = tool 'Maven 3.9.6'
                    sh "${mavenCmd} test"
                }
            } 
        }

        stage('Install Dependencies') {
            steps{
                script {
                    def mvnCmd = tool 'Maven 3.9.6'
                    sh "${mavenCmd} clean install"
                }
            }
        }

        stage ('Run Application') {
            steps{
                script{
                    def mvnCmd = tool 'Maven 3.9.6'
                    sh "${mavenCmd} exec: java -Dexec.mainClass=com.stockapp1.stockappgui"
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