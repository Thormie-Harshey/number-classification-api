# Number Classification API
## Overview:
The Number Classification API is a RESTful service built with Java Spring Boot that classifies numbers based on mathematical properties such as primality, perfect number status, and Armstrong number status.
It also fetches a fun fact about the number from the Numbers API.

## API Endpoints
Classify a Number
Endpoint:
`
GET /api/classify-number?number={number}
`
Request Example:
`
GET http://your-server-ip/api/classify-number?number=371
`
Successful Response (200 OK):
```
{
    "number": 371,
    "is_prime": false,
    "is_perfect": false,
    "properties": ["armstrong", "odd"],
    "digit_sum": 11,
    "fun_fact": "371 is an Armstrong number because 3^3 + 7^3 + 1^3 = 371"
}
```
Bad Request Response (400 Bad Request):
```
{
    "number": "alphabet",
    "error": true
}
```
## Installation Instructions
Prerequisites
- Java 17+
- Maven
- Spring Boot
- AWS EC2 instance (for deployment)
-Nginx (for reverse proxy)

## Local Setup
1. Clone the repository:
`
git clone git@github.com:Thormie-Harshey/number-classification-api.git
cd number-classification-api
`
2. Build the project:
`
mvn clean package
`
3. Run the application:
`
java -jar target/number-classification-api.jar
`
4. Test locally:
`
curl -X GET "http://localhost:8080/api/classify-number?number=371"
`

## Deployment Guide
1. Deploying on AWS EC2
Transfer the JAR file to EC2:
`
scp -i your-key.pem target/number-classification-api.jar ubuntu@your-ec2-ip:/home/ubuntu/
`
2. Run the application on EC2:
`
nohup java -jar /home/ubuntu/number-classification-api.jar > log.txt 2>&1 &
`
3. Set up Nginx (Reverse Proxy):
`
sudo nano /etc/nginx/sites-available/default
`
Add the following configuration:
```
server {
    listen 80;
    server_name your-ec2-ip;

    location / {
        proxy_pass http://localhost:8080/;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
    }
}
```
Save and exit.
4. Restart Nginx:
`
sudo systemctl restart nginx
`
5. Test the deployed API:
`
curl -X GET "http://your-ec2-ip/api/classify-number?number=371"
`

## Tech Stack
- Java 17 (Backend Development)
- Spring Boot (REST API Framework)
- Maven (Build Tool)
- Nginx (Reverse Proxy)
- AWS EC2 (Cloud Hosting)
- Numbers API (External API for fun facts)

## Live Deployment
Base URL: http://54.89.209.46/api/classify-number?number=371

## Author
GitHub: Thormie-Harshey
