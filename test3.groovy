node {
  stage('Clone') {
  git "https://github.com/RanjanGitHubb/mark2.git"
  }
  stage('Build') {
    bat "mvn -Dmaven.test.failure.ignore=true clean install"
  }
  stage ('SonarQube Analysis') {
   bat "mvn sonar:sonar \
    -Dsonar.projectKey=gs-maven1 \
    -Dsonar.host.url=http://localhost:9000 \
    -Dsonar.user=admin \
    -Dsonar.login=sqp_4dfd6313de89be694624cfdee7d60d46cee816b4"
  }
  stage('upload to nexus') {
   def mavenPom = readMavenPom file: ''
   def nexusRepoName = mavenPom.version.endsWith("SNAPSHOT") ? "gs-maven" : "mavenforjenkins-release"
    echo "${mavenPom.version}"
    echo "${nexusRepoName}"
      nexusArtifactUploader artifacts: [
      [
        echo "${mavenPom.version}"
        artifactId: 'mavenforjenkins', 
        classifier: '', 
        file: 'target/mavenforjenkins-"${mavenPom.version}".jar', 
        type: 'jar'
      ]
    ], 
      credentialsId: '08b1f4bf-c897-4b47-9d27-82c26e6561a8', 
      groupId: 'mavenforjenkins', 
      nexusUrl: 'localhost:8081', 
      nexusVersion: 'nexus3', 
      protocol: 'http', 
        repository: '"${nexusRepoName}"', 
        version: '"${mavenPom.version}"'
    
  }
}
