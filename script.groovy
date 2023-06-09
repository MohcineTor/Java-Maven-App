
def buildJar() {
    echo "Building the Jar of Application....."
    sh 'mvn clean package'
}

def testApp() {
    echo "Testing the Application ..."
    sh 'mvn test'
}

def buildImage(IMAGE_NAME) {
    echo "Building the Image docker for our Application and push this in dockerHub repo ..."
    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
        sh "docker build -t dockermohcine/my-repo:${IMAGE_NAME} ."
        sh "echo $PASSWORD | docker login -u $USERNAME --password-stdin"
        sh "docker push dockermohcine/my-repo:${IMAGE_NAME}"
    }
}
def deployApp() {
    echo "Deploying application...."
    sh 'docker run -d -p 8080:8080 dockermohcine/my-repo:jma-3.0'
}

return this 
