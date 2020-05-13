# **Build the BooksReviewPlatform application using Docker**
### Prerequisites:
- Installed [Docker for Windows](https://docs.docker.com/docker-for-windows/install/) or [Linux](https://runnable.com/docker/install-docker-on-linux) 
- Cloned project repository
### Step by step guide: 
1. Open the command promt or terminal in the directory with Dockerfile.
2. Run ./BuildApp.sh {**NUMBER**} (for Linux) or .\BuildApp.bat {**NUMBER**} (for Windows), where
number correspones to the minor version of the project e.g 0.0.{**NUMBER**}.
3. Open http://localhost:8080 in your browser.
4. When you finished working with the application press **CTRL+C** in your command prompt to terminate the process
5. Run docker container stop booksplatform-0.0.{**NUMBER**}

To rebuild your version of application you could just re-run the script.
To upload the new stable version of application to DockerHub please folow the guide below
#### ***{To be updated}***

**NOTE:** Docker images/containers **DO** occupy the space on your computer. To ommit exceeding the free space, it's recommended to remove all the unused images/containers. To achive this, please do the following atleast once a week if you use docker:
 - docker system prune -a
**This will remove all unused images, stoped containers, volumes and networks.**

## Developer full setup:
### Add sendgrid api key
- Create .env file under /server
- Add `SENDGRID_API_KEY=*` in .env file
- use sendgrid api key instead of *
