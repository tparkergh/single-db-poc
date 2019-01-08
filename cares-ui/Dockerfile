FROM node:8.14.0-jessie
ENV APP_HOME /cares-ui
RUN mkdir $APP_HOME
WORKDIR $APP_HOME
COPY . $APP_HOME
WORKDIR engine
RUN yarn install
RUN yarn build
WORKDIR $APP_HOME
RUN yarn install
EXPOSE 5000
CMD yarn start