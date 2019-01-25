FROM cwds/javajdk
RUN mkdir -p /opt/cares-rest-api
ARG VERSION
ADD build/libs/cares-rest-api-${VERSION}.jar /opt/cares-rest-api/cares-rest-api-boot.jar
EXPOSE 8090
WORKDIR /opt/cares-rest-api
CMD ["java", "-jar", "cares-rest-api-boot.jar"]
