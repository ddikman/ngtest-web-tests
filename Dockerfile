FROM openjdk:8-jdk-alpine

RUN apk add --no-cache gradle chromium chromium-chromedriver


# Set the working directory
WORKDIR /app

# Copy the project files
COPY . /app

RUN gradle test
