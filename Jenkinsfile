import groovy.transform.Field
@Library('jenkins-pipeline-utils') _

@Field
def GITHUB_CREDENTIALS_ID = '433ac100-b3c2-4519-b4d6-207c029a103b'
@Field
def DOCKER_CREDENTIALS_ID = '6ba8d05c-ca13-4818-8329-15d41a089ec0'
@Field
def newTag
@Field
def serverArti
@Field
def rtGradle
@Field
def emailGroup = 'tom.parker@osi.ca.gov'
@Field
def appname = 'Cares Intake Application'
@Field
def githubSshUrl = 'git@github.com:ca-cwds/single-db-poc'
@Field
def githubHttpUrl = 'https://github.com/ca-cwds/single-db-poc'
@Field
def githubProjectName = 'single-db-poc'
@Field
def buildNode = 'linux'

switch(env.BUILD_TYPE) {
  case "master": buildMaster(); break;
  default: buildPullRequest();
}

def buildPullRequest() {
  node(buildNode) {
    def triggerProperties = githubPullRequestBuilderTriggerProperties()
    properties([disableConcurrentBuilds(), [$class: 'RebuildSettings', autoRebuild: false, rebuildDisabled: false],
      buildDiscarder(logRotator(artifactDaysToKeepStr: '', artifactNumToKeepStr: '', daysToKeepStr: '', numToKeepStr: '25')),
      githubConfig(),
      pipelineTriggers([triggerProperties]),
      parameters([
        string(defaultValue: 'tparker-jenkins', description: '', name: 'branch'),
      ])
    ])
    try {
      checkOut()
      verifySemVerLabel()
      build()
      javadoc()
      testAndCoverage()
      sonarQubeAnalysis()
    } catch(Exception exception) {
        emailext attachLog: true, body: "Failed: ${exception}", recipientProviders: [[$class: 'DevelopersRecipientProvider']],
        subject: "${appname} failed with ${exception.message}", to: emailGroup
        currentBuild.result = "FAILURE"
        throw exception

    } finally {
        publishHTML([allowMissing: true, alwaysLinkToLastBuild: true, keepAll: true, reportDir: 'build/reports/tests', reportFiles: 'index.html', reportName: 'JUnit Report', reportTitles: 'JUnit tests summary'])
        cleanWs()
    }
  }
}

def buildMaster() {
  node(buildNode) {
    triggerProperties = pullRequestMergedTriggerProperties('single-db-poc')
    properties([disableConcurrentBuilds(),  [$class: 'RebuildSettings', autoRebuild: false, rebuildDisabled: false],
      buildDiscarder(logRotator(artifactDaysToKeepStr: '', artifactNumToKeepStr: '', daysToKeepStr: '', numToKeepStr: '25')),
      pipelineTriggers([triggerProperties]),
      githubConfig(),
      parameters([
        string(defaultValue: 'master', description: '', name: 'branch'),
        string(name: 'INCREMENT_VERSION', defaultValue: '', description: 'major, minor, or patch')
      ])
    ])
    try {
      checkOut()
      incrementTag()
      build()
      javadoc()
      testAndCoverage()
      sonarQubeAnalysis()
      tagRepo()
      deployDocker()
      cleanWorkspace()
    } catch (Exception exception) {
        emailext attachLog: true, body: "Failed: ${exception}", recipientProviders: [[$class: 'DevelopersRecipientProvider']],
        subject: "${appname} The Build failed with ${exception.message}", to: emailGroup
        currentBuild.result = "FAILURE"
        throw exception
    } finally {
        publishHTML([allowMissing: true, alwaysLinkToLastBuild: true, keepAll: true, reportDir: 'build/reports/tests', reportFiles: 'index.html', reportName: 'JUnit Report', reportTitles: 'JUnit tests summary'])
        cleanWs()
    }
  }
}

def checkOut()  {
  stage('Check Out') {
    cleanWs()
    checkout scm
  }
}

def verifySemVerLabel() {
  stage('Verify SemVer Label') {
    checkForLabel(githubProjectName)
  }
}

def build() {
  stage('Build') {
    serverArti = Artifactory.server 'CWDS_DEV'
    rtGradle = Artifactory.newGradleBuild()
    rtGradle.tool = 'Gradle_35'
    rtGradle.resolver repo:'repo', server: serverArti
    rtGradle.deployer.mavenCompatible = true
    rtGradle.deployer.deployMavenDescriptors = true
    rtGradle.useWrapper = true
    def buildInfo = rtGradle.run buildFile: 'build.gradle', tasks: "clean build -D build=${BUILD_NUMBER} -DnewVersion=${newTag}".toString()
  }
}

def testAndCoverage() {
  stage('Tests and Coverage') {
    buildInfo = rtGradle.run buildFile: 'build.gradle', switches: '--info', tasks: 'test'
  }
}

def sonarQubeAnalysis() {
  stage('SonarQube analysis'){
    lint(rtGradle)
  }
}

def javadoc() {
  stage('Javadoc') {
    rtGradle.run buildFile: 'build.gradle', tasks: 'javadoc'
  }
}

def incrementTag() {
  stage('Increment Tag') {
    newTag = newSemVer(INCREMENT_VERSION)
  }
}

def tagRepo() {
  stage('Tag Repo') {
    tagGithubRepo(newTag, GITHUB_CREDENTIALS_ID)
  }
}

def deployDocker(){
  stage ('Build Docker'){
    withDockerRegistry([credentialsId: DOCKER_CREDENTIALS_ID]) {
           buildInfo = rtGradle.run buildFile: 'build.gradle', tasks: 'dockerPushLatestVersion'
    }
  }
}

def cleanWorkspace() {
  stage('Clean WorkSpace') {
    publishHTML([allowMissing: true, alwaysLinkToLastBuild: true, keepAll: true, reportDir: 'build/docs/javadoc', reportFiles: 'index-all.html', reportName: 'javadoc', reportTitles: 'javadoc'])
  }
}

def githubConfig() {
  githubConfigProperties(githubHttpUrl)
}
