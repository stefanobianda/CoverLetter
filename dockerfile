FROM openjdk:17
VOLUME /tmp
EXPOSE 9191
ARG JAR_FILE=target/coverletter-0.0.1-SNAPSHOT.jar
ARG CL_URL_DB_PROD
ARG CL_USER_DB_PROD
ARG CL_PASSWORD_DB_PROD
ENV CL_URL_DB_PROD $CL_URL_DB_PROD
ENV CL_USER_DB_PROD $CL_USER_DB_PROD
ENV CL_PASSWORD_DB_PROD ${CL_PASSWORD_DB_PROD}
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
