pipeline {
    agent any
    
    environment {
        DOCKER_HUB_CREDENTIALS = credentials('docker-hub-cred')
        KUBECONFIG = credentials('kube-config-cred-hw3')
        dockerImage=''
    }

    stages {
        stage('Build the image') {
            steps {
                script {
                    checkout scm
                    // dockerImage= docker.build('banudeep/hw2:latest' + '${BUILD_NUMBER}')
                    sh 'docker build -t banudeep/hw3:${BUILD_NUMBER} .'
                }
            }
        }

        stage('Push to Docker Hub') {
            steps {
                script {
                
                    sh "docker login -u $DOCKER_HUB_CREDENTIALS_USR -p $DOCKER_HUB_CREDENTIALS_PSW"
                    // dockerImage.push()
                    sh 'docker push banudeep/hw3:${BUILD_NUMBER}'
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                script {
                    // Define the tag using Jenkins environment variables or custom logic
                    // def tag = "$BUILD_NUMBER"
                    def tag = currentBuild.number
                    // Replace placeholder in the Deployment YAML with the actual image tag
                    sh "sed -i 's|{{IMAGE_TAG}}|${tag}|g' deployment.yaml"
                    withCredentials([file(credentialsId: 'kube-config-cred-hw3', variable: 'KUBECONFIG')]) {
                        sh 'kubectl --kubeconfig=$KUBECONFIG apply -f deployment.yaml'
                        sh "kubectl --kubeconfig=\$KUBECONFIG apply -f service.yaml"
                        // sh 'kubectl --kubeconfig=$KUBECONFIG apply -f deployment.yaml'
                        // sh 'kubectl --kubeconfig=$KUBECONFIG set image deployments/{deploymentName} {container name given in deployment yaml file}={dockerId}/{projectName}:${BUILD_NUMBER}'
                    }
                }
            }
        }
    }
}

 
