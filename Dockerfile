# Use openjdk base image
FROM amazoncorretto:latest

# Set the working directory to /app
WORKDIR /app

# Copy all files from the src directory into the container's /app directory
COPY src/ /app/

# Compile all Java files inside the /app directory
RUN javac *.java

# Set the default command to run the Java program (assuming you want to run HelloWorld)
CMD ["java", "HelloWorld"]