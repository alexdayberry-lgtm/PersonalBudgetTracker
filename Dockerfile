# Build stage
FROM openjdk:24 AS build

WORKDIR /app
COPY src/ /app/src/

# compile all java sources into /app/out
RUN javac -d out $(find src -name "*.java")

# Runtime stage
FROM openjdk:23-jdk
WORKDIR /app
COPY --from=build /app/out /app/

# make data directory and allow mapping from host
VOLUME ["/app/data"]

# run main
CMD ["java", "app.Main"]
