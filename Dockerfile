FROM node:20 AS build

WORKDIR /app

COPY nestjs-cinema/package*.json ./

RUN npm install

COPY nestjs-cinema/ ./

FROM node:20 AS runtime

WORKDIR /app

COPY --from=build /app /app

EXPOSE 3000

CMD ["npm", "run", "start"]

