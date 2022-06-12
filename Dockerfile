FROM openjdk:17-oracle
ADD build/libs/alfabank-1.jar alfabank-1.jar
EXPOSE 8189
CMD ["java", "-jar", "/alfabank-1.jar"]
