pipeline {
  agent {
    node {
      label 'Pipeline'
    }

  }
  stages {
    stage('Print Message') {
      steps {
        echo 'Hello world'
      }
    }
    stage('Sleep') {
      steps {
        sleep 5
      }
    }
    stage('List directory') {
      steps {
        bat 'dir'
      }
    }
  }
}