pipeline {
    agent {
        label 'be'
    }
    tools {
        nodejs 'NodeJS' 
    }
    stages {
        stage('Build') {
            echo 'Hello world'
        }
    }
}