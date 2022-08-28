FROM openjdk:11
EXPOSE 8000
RUN mkdir ./app
COPY ./target/UrlShortener-1.0.jar ./app
CMD java -jar ./app/UrlShortener-1.0.jar