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
        stage('Checkout') {
            steps {
                git credentialsId: 'f50ff99b-330f-4bfb-8ad5-5953526e33b4', url: 'https://github.com/Hungnd562k/devops-bootcamp-todolist-backend-api.git', branch: 'master'
            }
        }
        stage('Build Image') {
            steps {
                script {
                    echo 'Build image'
                    docker.withRegistry(REGISTRY_URL, DOCKER_CREDENTIAL_ID) {
                        docker.build("${IMAGE_NAME}:${IMAGE_TAG}", ".")
                    }
                }
            }
        }
        stage('Push Image') {
            input {
                message "Approval for pushing to registry?"
                ok "Approve"
                submitter "admin"
            }
            steps {
                script {
                    docker.withRegistry(REGISTRY_URL, DOCKER_CREDENTIAL_ID) {
                        docker.image("${IMAGE_NAME}:${IMAGE_TAG}").push()
                    } 
                }
            }
        }
        stage('Deploy to k8s') {
            steps {
                dir('infrastructure') {
                    git credentialsId: 'your-credentials-id',
                        url: 'https://github.com/Hungnd562k/devops-bootcamp-infrastructure.git',
                        branch: 'main'
                }

                withKubeConfig([credentialsId: 'k8s-kubeconfig-id']) {
                    dir('infrastructure/Pipelines/Backend/deployments') {
                        sh 'kubectl apply -f .'
                        sh 'kubectl rollout restart deployment backend-api'
                    }
                }
            }
        }
    }
}