pipeline {
  agent {
    label 'Slave_Induccion'
  }
  options {
    	buildDiscarder(logRotator(numToKeepStr: '3'))
		disableConcurrentBuilds()
  }
  tools {
    jdk 'JDK8_Centos' 
    gradle 'Gradle6.0.1_Centos'
  }
  stages{
		stage('Checkout') {
			steps{
				echo "------------>Checkout<------------"
				checkout([
					$class: 'GitSCM', 
					branches: [[name: '*/master']], 
					doGenerateSubmoduleConfigurations: false, 
					extensions: [], 
					gitTool: 'Default', 
					submoduleCfg: [], 
					userRemoteConfigs: [[
						credentialsId: 'GitHub_CarlosGarciaMera', 
						url:'https://github.com/CarlosGarciaMera/SistemaReservaRestaurante.git'
					]]
				])
			}		
		}
		stage('Clean targets') {
			steps{
				echo "------------>Clean targets<------------"
				sh 'gradle --b ./microservicio/build.gradle clean'
			}
		}
		stage('Compile') {
			steps{
				echo "------------>Compile<------------"
				sh 'gradle --b ./microservicio/build.gradle compileJava'
			}
		}
		stage('Unit Tests') {
			steps{
				echo "------------>Unit Tests<------------"
				sh 'gradle --b ./microservicio/build.gradle test'
				echo "------------>JacocoTestReport Tests<------------"
                sh 'gradle --b ./microservicio/build.gradle jacocoTestReport'
			}
		}
		stage('Static Code Analysis') {
			steps{
				echo '------------>Analisis de codigo estatico<------------'
				withSonarQubeEnv('Sonar') {	
				sh "${tool name: 'SonarScanner', type:'hudson.plugins.sonar.SonarRunnerInstallation'}/bin/sonar-scanner -Dproject.settings=sonar-project.properties"
				}
			}
		}
		stage('Build') {
			steps{
				echo "------------>Build<------------"
				sh 'gradle --b ./microservicio/build.gradle build -x test'
			}
		}  
  }

  post {
    always {
      echo 'run finished'
    }
    success {
      echo 'and it was successful'
      
      junit 'microservicio/dominio/build/test-results/test/*.xml'
      junit 'microservicio/infraestructura/build/test-results/test/*.xml'
    }
    failure {
		echo 'and it was failed'
		mail (to: 'carlos.garcia@ceiba.com.co',subject: "Failed Pipeline:${currentBuild.fullDisplayName}",body: "Something is wrong with ${env.BUILD_URL}")
    }
    unstable {
      echo 'This will run only if the run was marked as unstable'
    }
  }
}