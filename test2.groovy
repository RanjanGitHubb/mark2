node {
  stage('Clone') {
  git "https://github.com/RanjanGitHubb/mark2.git"
  }
  stage('Build') {
    bat "mvn -Dmaven.test.failure.ignore=true clean install"
  }
  stage('SonarCloud Analysis') {
    //withSonarQubeEnv('SonarQube')
    bat "nexusArtifactUploader credentialsId: '08b1f4bf-c897-4b47-9d27-82c26e6561a8', groupId: 'mavenforjenkins', nexusUrl: 'localhost:8081', nexusVersion: 'nexus3', protocol: 'http', repository: 'gs-maven', version: '0.0.1-SNAPSHOT'"
    
  }

}
