# Use an official OpenJDK 21 image as the base image
FROM openjdk:21

# Create an application directory
RUN mkdir /app

# Copy compiled Java files into the container
COPY out/production/HelloWorldDocker/ /app

# Set working directory
WORKDIR /app

# Run the main class
CMD ["java", "HelloWorld"]