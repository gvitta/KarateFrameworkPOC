pipeline  {
    agent any
    parameters{
        string(name: 'TAGS',defaultValue: "~@ignore", description: "Enter the script path you want to execute" )
        choice(name: 'BROWSER',choices: ['chrome','firefox','headless'],description: "Where you want to execute")


    }

    node {
        withGradle {
            sh './gradlew build'
         }


        stage('Testing'){

                  withGradle {
                       sh """
                          "./gradlew test"
                       """
                       sh "Execution completed"
                  }
            }
        }


    post{
        always{
            archiveArtifacts artifacts: 'build/karate-reports/*.json', followSymlinks: false

        }
    }
}