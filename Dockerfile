From openjdk:8
copy ./target/mbrdi-assignment-0.0.1-SNAPSHOT.jar mbrdi-assignment-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","mbrdi-assignment-0.0.1-SNAPSHOT.jar"]