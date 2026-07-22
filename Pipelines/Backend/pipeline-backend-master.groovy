pipeline {
    agent {
        label 'be'
    }
    environment {
        REGISTRY_URL = 'https://index.docker.io/v1/'
        DOCKER_CREDENTIAL_ID = 'docker-hub-credentials'
        IMAGE_NAME = 'hungnd2/devops-bootcamp-backend-api'
        IMAGE_TAG = 'latest'
    }
    stages {
        stage('Build Image & push image') {
            steps {
                echo 'Build image'
                docker.withRegistry(REGISTRY_URL, DOCKER_CREDENTIAL_ID) {
                    def myImage = docker.build("${IMAGE_NAME}:${IMAGE_TAG}", ".")
                    myImage.push()
                }
            }
        }
    }
}