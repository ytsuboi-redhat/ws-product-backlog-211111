#!groovy
properties([pipelineTriggers([pollSCM('H/2 * * * *')])])
pipeline {
    agent any
    tools {
        maven 'maven3.6.3'
    }
    stages {
        stage('チェックアウト') {
            steps {
                checkout scm
            }
        }
        stage('frontendビルド') {
            steps {
                dir('frontend') {
                    nodejs(nodeJSInstallationName: 'NodeJS LTS') {
                        // prepare
                        sh 'npm install --silent'
                        // Run Frontend UT
                        // sh 'npm run test:unit'
                    }
                }
            }
        }
        stage('backendビルド') {
            steps {
                script {
//                    // Wait until mysql service is up
//                    sh './todo-backend/wait-for-it.sh -t 30 todo-mysql:3306'
//                    // Run Backend UT
//                    //sh 'mvn clean jacoco:prepare-agent test jacoco:report -f todo-backend'
                      sh 'mvn clean package -f backend -DskipTests=true'
                }
            }
        }
        stage('静的解析') {
            steps {
//                withSonarQubeEnv('default') {
//                    sh """
//                      ${tool 'sonarqube-scanner'}/bin/sonar-scanner \
//                        -Dsonar.projectKey=workshop:frontend \
//                        -Dsonar.projectName=team2-frontend \
//                        -Dsonar.projectVersion=1 \
//                        -Dsonar.javascript.lcov.reportPaths=frontend/tests/unit/coverage/lcov.info \
//                        -Dsonar.sources=frontend/src 
//                    """
//                }
                sh """
                  mvn sonar:sonar \
                    -f backend \
                    -Dsonar.host.url=http://sonar:9000 \
                    -Dsonar.projectKey=workshop:backend \
                    -Dsonar.projectName=backend
                """
            }
        }
        stage('frontend実行') {
            steps {
                dir('frontend') {
                    nodejs(nodeJSInstallationName: 'NodeJS LTS') {
                        sh 'npm run build'
                        sh 'cp -r dist/* /usr/share/nginx/html'
                    }   
                }
            }
        }
//        stage('backend実行') {
//            steps {
//                sh 'mvn clean package -Dmaven.test.skip=true -f todo-backend'
//                sh 'oc start-build todo-backend --from-dir=./todo-backend --follow -n cicd2'
//            }
//        }
        stage('受け入れテスト') {
            steps {
                script {
                    sh '_JAVA_OPTIONS=-Dfile.encoding=UTF-8 xvfb-run mvn clean test -f at -Dselenide.baseUrl=http://nginx -Dselenide.browser=chrome'
                }
            }
        }
    }
    post {
        always {
            // save test JUnit format reports
            junit allowEmptyResults: true, testResults: 'test/**/*.xml'
        }
        failure {
            echo 'FAILURE!!!'
        }
    }
}
