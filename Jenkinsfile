pipeline {
  agent {
    docker {
      image 'node:7-alpine'
    }

  }
  stages {
    stage('Print Message') {
      steps {
        echo 'Hello world'
      }
    }
    stage('error') {
      steps {
        echo 'hello'
      }
    }
  }
}