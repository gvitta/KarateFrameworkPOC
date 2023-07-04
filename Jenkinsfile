pipeline  {
    agent any
    parameters{
        string(name: 'TAGS',defaultValue: "cypress/integration/**/**", description: "Enter the script path you want to execute" )
        choice(name: 'BROWSER',choices: ['chrome','firefox','headless'],description: "Where you want to execute")

    }

    stages{

        stage('Testing'){
            steps{

                sh "gradle test -Dkarate.options=\"--tags ~ignore\""
                sh "Execution completed"
            }
        }
        stage('Deploying'){
             steps{
                echo "Deploying the Application"
             }
        }
    }
    post{
        always{
            archiveArtifacts artifacts: 'build/karate-reports/*.json', followSymlinks: false
            cucumber buildStatus: 'UNSTABLE',
                failedFeaturesNumber: 1,
                failedScenariosNumber: 1,
                skippedStepsNumber: 1,
                failedStepsNumber: 1,
                fileIncludePattern: 'build/karate-reports/*.json',
                sortingMethod: 'ALPHABETICAL',
                trendsLimit: 100
        }
    }
}