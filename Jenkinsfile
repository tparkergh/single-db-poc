import groovy.transform.Field
@Library('jenkins-pipeline-utils') _

@Field
def GITHUB_CREDENTIALS_ID = '433ac100-b3c2-4519-b4d6-207c029a103b'
@Field
def newTag
@Field
def serverArti
@Field
def rtGradle
@Field
def emailGroup = 'ratnesh.raval@osi.ca.gov, tyler.clemens@osi.ca.gov, shahid.saleemi@osi.ca.gov, prasad.mysore@osi.ca.gov, oleg.korniichuk@osi.ca.gov, james.lebeau@osi.ca.gov, tom.parker@osi.ca.gov'
@Field
def appname = 'Cares Intake Application'
@Field
def githubSshUrl = 'git@github.com:ca-cwds/single-db-poc'
@Field
def githubHttpUrl = 'https://github.com/ca-cwds/single-db-poc'
@Field
def semverLable = 'caresIntake'
@Field
def node = 'linux'

switch(env.BUILD_JOB_TYPE) {
  case "master": buildMaster(); break;
  case "release":releasePipeline(); break;
  default: buildPullRequest();
}

def buildPullRequest() {
  node(${node}) {
    def triggerProperties = githubPullRequestBuilderTriggerProperties()
    properties([disableConcurrentBuilds(), [$class: 'RebuildSettings', autoRebuild: false, rebuildDisabled: false],
      buildDiscarder(logRotator(artifactDaysToKeepStr: '', artifactNumToKeepStr: '', daysToKeepStr: '', numToKeepStr: '25')),
      githubConfig(),
      pipelineTriggers([triggerProperties]),
      parameters([
        string(defaultValue: 'master', description: '', name: 'branch'),
        booleanParam(defaultValue: true, description: 'Default release version template is: <majorVersion>_<buildNumber>-RC', name: 'RELEASE_PROJECT'),
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
        emailext attachLog: true, body: "Failed: ${e}", recipientProviders: [[$class: 'DevelopersRecipientProvider']],
        subject: "${appname} failed with ${e.message}", to: ${emailGroup}
        currentBuild.result = "FAILURE"
        throw exception
    } finally {
        publishHTML([allowMissing: true, alwaysLinkToLastBuild: true, keepAll: true, reportDir: 'build/reports/tests', reportFiles: 'index.html', reportName: 'JUnit Report', reportTitles: 'JUnit tests summary'])
        cleanWs()
    }
  }
}

def buildMaster() {
  node(${node}) {
    triggerProperties = pullRequestMergedTriggerProperties('cares-intake--master')
    properties([disableConcurrentBuilds(),  [$class: 'RebuildSettings', autoRebuild: false, rebuildDisabled: false],
      buildDiscarder(logRotator(artifactDaysToKeepStr: '', artifactNumToKeepStr: '', daysToKeepStr: '', numToKeepStr: '25')),
      pipelineTriggers([triggerProperties]),
      parameters([
        booleanParam(defaultValue: true, description: '', name: 'USE_NEWRELIC'),
        string(defaultValue: 'master', description: '', name: 'branch'),
        booleanParam(defaultValue: true, description: 'Default release version template is: <majorVersion>_<buildNumber>-RC', name: 'RELEASE_PROJECT'),
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
      cleanWorkspace()
    } catch (Exception exception) {
        emailext attachLog: true, body: "Failed: ${e}", recipientProviders: [[$class: 'DevelopersRecipientProvider']],
        subject: "${appname} The Build failed with ${e.message}", to: ${emailGroup}
        currentBuild.result = "FAILURE"
    } finally {
        publishHTML([allowMissing: true, alwaysLinkToLastBuild: true, keepAll: true, reportDir: 'build/reports/tests', reportFiles: 'index.html', reportName: 'JUnit Report', reportTitles: 'JUnit tests summary'])
        cleanWs()
    }
  }
}

def checkOut()  {
  stage('Check Out') {
    cleanWs()
    git branch: '$branch', credentialsId: GITHUB_CREDENTIALS_ID, url: ${githubSshUrl}
  }
}

def verifySemVerLabel() {
  stage('Verify SemVer Label') {
    checkForLabel(${semverLable})
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
    def buildInfo = rtGradle.run buildFile: 'build.gradle', tasks: "jar shadowJar -DRelease=true -D build=${BUILD_NUMBER} -DnewVersion=${newTag}".toString()
  }
}

def testAndCoverage() {
  stage('Tests and Coverage') {
    buildInfo = rtGradle.run buildFile: 'build.gradle', switches: '--info', tasks: 'test jacocoTestReport'
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
    newTag = newSemVer()
  }
}

def tagRepo() {
  stage('Tag Repo') {
    tagGithubRepo(newTag, GITHUB_CREDENTIALS_ID)
  }
}

def cleanWorkspace() {
  stage('Clean WorkSpace') {
    publishHTML([allowMissing: true, alwaysLinkToLastBuild: true, keepAll: true, reportDir: 'build/docs/javadoc', reportFiles: 'index-all.html', reportName: 'javadoc', reportTitles: 'javadoc'])
  }
}

def githubConfig() {
  githubConfigProperties(${githubHttpUrl})
}
