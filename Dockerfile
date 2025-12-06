# Use stable OpenJDK
FROM openjdk:24-ea

# Set working directory
WORKDIR /app

# Copy all source code
COPY src/ /app/src

# Create output classes directory
RUN mkdir -p /app/classes

# 1️ Compile security package first
RUN javac -d /app/classes /app/src/security/*.java

# 2️ Compile budget package, referencing compiled classes
RUN javac -d /app/classes -cp /app/classes /app/src/budget/*.java

# Set runtime working directory
WORKDIR /app/classes

# Run the main class
CMD ["java", "budget.Main"]
