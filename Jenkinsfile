pipeline  {
    agent any
    parameters{
        string(name: 'TAGS',defaultValue: "~@ignore", description: "Enter the script path you want to execute" )
        choice(name: 'BROWSER',choices: ['chrome','firefox','headless'],description: "Where you want to execute")

    }

    stages{

        stage('Testing'){
            steps{

                sh "gradle test -Dkarate.options=\"--tags ~@ignore\""
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

        }
    }
}