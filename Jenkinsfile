pipeline {
    agent any

    stages{
        stage('Build'){
            steps {
                script {
                    def mvnHome = tool 'Maven'
                    def javaHome = tool 'JDK11'

                    env.PATH = "${javaHome}/bin:${mvnHome}/bin:${env.PATH}"

                    dir('/home/zach/Documents/javacode/stockAppNoGui/stockApp') {
                        sh "mvn clean package"
                    }
                }
            }

        }
    }
}