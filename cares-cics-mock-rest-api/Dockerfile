FROM cwds/javajdk
RUN mkdir -p /opt/cares-cics-mock-rest-api
ARG VERSION
ADD build/libs/cares-cics-mock-rest-api-${VERSION}.jar /opt/cares-cics-mock-rest-api/cares-cics-mock-rest-api-boot.jar
EXPOSE 8093
WORKDIR /opt/cares-cics-mock-rest-api
CMD ["java", "-jar", "cares-cics-mock-rest-api-boot.jar"]
