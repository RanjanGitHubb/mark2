node {
  stage('Clone') {
  git "https://github.com/RanjanGitHubb/mark2.git"
  }
  stage('Build') {
    bat "mvn clean install"
  }
  stage('SonarCloud Analysis') {
    withSonarQubeEnv('SonarCloud')
    bat "mvn sonar:sonar"
    
  }

}
