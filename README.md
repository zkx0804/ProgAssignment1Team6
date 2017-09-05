# ProgAssignment1Team6

## Team 6: Programming Assignment 1
Team members: Hua Jiang, Kaixin Zhang, Kunpeng Xie, Tu Lan

## Required Tools:
Java Version: 1.8.0
Maven Version: 3.5.0
Git

## Download:
Maven: https://maven.apache.org/download.cgi
Git Download: https://git-scm.com/downloads

## Installation Guide:
Maven: https://maven.apache.org/install.html
		For windows: https://www.mkyong.com/maven/how-to-install-maven-in-windows/
Git: https://git-scm.com/book/en/v2/Getting-Started-Installing-Git

## Some useful tips for git:
Add existing project: 
https://help.github.com/articles/adding-an-existing-project-to-github-using-the-command-line/

Basic Git commands:
https://confluence.atlassian.com/bitbucketserver/basic-git-commands-776639767.html



Project Github Link:
https://github.com/zkx0804/ProgAssignment1Team6

## Add trec-car tool dependency to project
Please download the trec-car tool to your local disk, use terminal direct to ..\trec-car-tools\java1.7, and use command "mvn clean install" to install the jar to the local .m2 file. Then the project should be able to run locally.


## Run project locally
This project is using maven to manage denpendencies.
If you're using Eclipse for Java editor:
1. import -> Existing Maven Project.
2. if the trec-car tool dependency is missing, please check the step above, or import the trec-car tool project to eclipse. 
3. Build project.
4. Run InitSearch.java.
