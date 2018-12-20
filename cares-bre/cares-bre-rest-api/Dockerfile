FROM cwds/javajdk
RUN mkdir -p /opt/cares-bre-rest-api
ARG VERSION
ADD build/libs/cares-bre-rest-api-${VERSION}.jar /opt/cares-bre-rest-api/cares-bre-rest-api-boot.jar
EXPOSE 8091
WORKDIR /opt/cares-bre-rest-api
CMD ["java", "-jar", "cares-bre-rest-api-boot.jar"]
