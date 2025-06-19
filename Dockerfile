# Use official OpenJDK base image
FROM openjdk:24-jdk-slim

# Install find and other useful tools
RUN apt-get update && apt-get install -y findutils

# Create working directory inside container
WORKDIR /app

# Copy all files from host project to container
COPY . .

# Compile all .java files recursively (preserves package structure)
RUN find . -name "*.java" > sources.txt && javac @sources.txt

# Command to run your main class with argument
ENTRYPOINT ["java", "package_for_websitemonitor.Main"]
