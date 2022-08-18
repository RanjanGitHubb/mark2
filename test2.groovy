node {
  stage('Clone') {
  git "https://github.com/RanjanGitHubb/mark2.git"
  }
  stage('Build') {
    bat "mvn -Dmaven.test.failure.ignore=true clean package"
  }
  stage('SonarCloud Analysis') {
    bat "mvn verify sonar:sonar"
    //bat "mvn verify org.sonarsource.scanner.maven:sonar-maven-plugin:sonar -Dsonar.projectKey=RanjanGitHubb_mark2"
  }

}
