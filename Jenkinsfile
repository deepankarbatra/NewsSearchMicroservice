pipeline {
    agent any

    environment {
        FRONTEND_DIR = 'NewsSearchFrontend'
        BACKEND_DIR = 'NewsSearchBackend'
	ZIP_FILE = 'NewsSearchMS.zip'
    }

    stages {

        stage('Extract ZIP') {
            steps {
                script {
                    sh 'rm -rf *'
                    
                    echo "Unzipping ${ZIP_FILE}"
                    sh "unzip ${ZIP_FILE}"
                }
            }
        }

        stage('Build Frontend') {
            steps {
                dir(FRONTEND_DIR) {
                    script {
                        sh 'npm install'
                        sh 'ng build'
                    }
                }
            }
        }

        stage('Build Backend') {
            steps {
                dir(BACKEND_DIR) {
                    script {
                        sh './mvnw clean install'
                    }
                }
            }
        }

        stage('Dockerize') {
            steps {
                script {
                    sh 'docker build -t myapp:latest .'
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    sh 'docker run -p 8080:8080 myapp:latest'
                }
            }
        }
    }
}
