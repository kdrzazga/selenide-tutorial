pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building..'
                gradle clean
                gradle build
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
                gradle test
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}
