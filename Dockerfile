# Use a stable Java runtime
FROM eclipse-temurin:21-jdk

# Set working directory
WORKDIR /app

# Copy your entire src directory
COPY src/ /app/src

# Create directory for compiled classes
RUN mkdir -p /app/classes

# Compile the security package first
RUN javac -d /app/classes /app/src/security/*.java

# Compile the budget package, pointing to the already compiled security classes
RUN javac -cp /app/classes -d /app/classes /app/src/budget/*.java

# Run Main.java with correct classpath
ENTRYPOINT ["sh", "-c", "java -cp /app/classes budget.Main"]

