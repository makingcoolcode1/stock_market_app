
pipeline {
    agent any

    tools {
        maven 'maven1'
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
                    def mavenCmd = tool 'maven1'
                    sh "${mavenCmd} compile"
                }
            }
        }

        stage('Test'){
            steps{
                script{
                    def mavenCmd = tool 'maven1'
                    sh "${mavenCmd} compile"
                }
            } 
        }

        stage('Install Dependencies') {
            steps{
                script {
                    def mavenCmd = tool 'maven1'
                    sh "${mavenCmd} compile"
                }
            }
        }

        stage ('Run Application') {
            steps{
                script{
                    def mavenCmd = tool 'maven1'
                    sh "${mavenCmd} exec:java -Dexec.mainClass=com.stockapp1.stockappgui"
                }
            }
        }

        stage ('Debug') {
            steps{
                script{
                    sh 'echo $PATH'
                    sh 'which mvn'
                    sh 'ls -l /var/jenkins_home/tools/hudson.tasks.Maven_MavenInstallation/maven1'
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