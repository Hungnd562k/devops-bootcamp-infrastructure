pipeline {
    agent {
        label 'be'
    }
    tools {
        nodejs 'NodeJS' 
    }
    stages {
        stage('Build') {
            steps {
                echo 'Hello world!'
            }
        }
    }
}