node {
  stage('Clone') {
  git "https://github.com/RanjanGitHubb/mark2.git"
  }
  stage('Build') {
    bat "mvn -Dmaven.test.failure.ignore=true clean install"
  }
  stage('SonarCloud Analysis') {
    //withSonarQubeEnv('SonarQube')
    bat "mvn sonar:sonar"
    
  }

}
