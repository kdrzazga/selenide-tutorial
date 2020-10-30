pipeline { //must be top level
    agent any //any Jenkins agent can execute this build

    environment { //here declare all variables
        NEW_VERSION = '0.0.1'
        SERVER_CREDENTIALS = credentials('')
    }

     triggers {
            cron('H */8 * * *') //regular builds
            pollSCM('* * * * *') //polling for changes, here once a minute
        }

    stages { //here all the work happens
        stage("Build") {
            steps{
                bat 'gradle build'
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
        }*/
    }

    post {
        always {
            echo "Done"
        }

        /*success {
        }

        failure {
        }*/
    }
}
