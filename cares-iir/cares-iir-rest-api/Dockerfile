FROM cwds/javajdk
RUN mkdir -p /opt/cares-iir-rest-api
ARG VERSION
ADD build/libs/cares-iir-rest-api-${VERSION}.jar /opt/cares-iir-rest-api/cares-iir-rest-api-boot.jar
EXPOSE 8090
WORKDIR /opt/cares-iir-rest-api
CMD ["java", "-jar", "cares-iir-rest-api-boot.jar"]
