#
# Package stage
#
FROM openjdk:18-alpine
ADD ./target/badge-pool-1.0-SNAPSHOT-jar-with-dependencies.jar badge-pool-1.0.jar
RUN ls
#RUN echo "'about to copy\n'"
#COPY --from=build ./badge-pool-1.0.jar /usr/local/lib/badge-pool-1.0.jar
#RUN echo "ho copiato..\n"
EXPOSE 7070
ENTRYPOINT ["java","-jar","./badge-pool-1.0.jar"]