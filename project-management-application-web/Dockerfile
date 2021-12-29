#FROM node:14
#RUN npm install -g @angular/cli
#RUN mkdir -p /app
#WORKDIR /app
#COPY package.json /app
#RUN npm install
#COPY . /app
#RUN npm run build --prod
#CMD ng serve --host 0.0.0.0 --port 4200

FROM node:14 as build
WORKDIR /home/node/app
COPY package*.json ./
RUN npm ci
COPY . .
RUN npm run build

FROM nginx:stable-alpine
COPY --from=build /home/node/app/dist /usr/share/nginx/html
