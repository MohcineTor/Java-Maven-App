
def gv 
pipeline {
    agent any
    tools {
        maven 'maven-3.6'
    } 
    stages {
        stage("Init") {
            steps {
                script {
                    gv = load "script.groovy"
                }
            }
        }
        stage('increment version') {
            steps {
                script {
                    gv.increment_version()
                }
            }
        }
        stage("Build") {
            steps {
                script {
                    gv.buildJar()
                }
            }
        }
        stage("Test") {
            steps {
                script {
                    gv.testApp()
                }
            }
        }
        stage("Build Image") {
            steps {
                script {
                    gv.buildImage("${IMAGE_NAME}")
                }
            }
        }
        stage("Deploy") {
            steps {
                script {
                    gv.deployApp("${IMAGE_NAME}")
                }
            }
        }
    }
}
