pipeline {
    agent any

    environment {
        FRONTEND_DIR = 'NewsSearchFrontend'
        BACKEND_DIR = 'NewsSearchBackend'
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/deepankarbatra/NewsSearchMicroservice.git']])
            }
        }

        stage('Build Frontend') {
            steps {
                dir(FRONTEND_DIR) {
                    script {
                        bat 'npm install'
                        bat 'ng build'
                    }
                }
            }
        }
        
        stage('Copy file') {
            steps {
                script {
                    bat 'xcopy NewsSearchFrontend\\dist\\news-search NewsSearchBackend\\src\\main\\resources\\static /E /I /Y'
                }
            }
        }


        stage('Build Backend') {
            steps {
                dir(BACKEND_DIR) {
                    script {
                        bat 'mvn clean install'
                    }
                }
            }
        }

        stage('Dockerize') {
            steps {
                
                    script {
                        bat 'docker build -t myapp:latest .'
                    }
                
            }
        }

        stage('Deploy') {
            steps {
                script {
                    bat 'docker run -d -p 8080:8080 myapp:latest'
                }
            }
        }
    }
}
