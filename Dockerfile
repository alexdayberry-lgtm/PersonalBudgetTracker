FROM openjdk:23

# Create app directory
WORKDIR /app

# Copy compiled class files from IntelliJ’s out/ directory
COPY out/production/YourProjectName/ /app

# Run the program
CMD ["java", "app.Main"]

