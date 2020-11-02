pipeline { //must be top level
    agent {label 'java'} //any Jenkins agent can execute this build

    stages { //here all the work happens
        stage("Build") {
            steps{
                sh 'gradlew build'
             }
        }
    }/*
        stage('Test') {
            when {
                expression {
                    BRANCH_NAME == 'dev' //executed only od dev branch
                }
            }
            steps {
                echo 'Testing not implemented yet'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying not implemented yet'
            }
        }
    }

    post {
        always {
            echo "Done"
        }

        /*success {
        }

        failure {
        }
    }*/
}
