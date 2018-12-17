FROM node:8.14.0-jessie
ENV APP_HOME /cares-ui
RUN mkdir $APP_HOME
WORKDIR $APP_HOME
COPY . $APP_HOME
RUN yarn install
CMD yarn start

