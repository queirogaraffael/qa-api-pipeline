FROM node:20 AS build

WORKDIR /app

COPY backend/package*.json ./

RUN npm install

COPY backend/ ./

FROM node:20 AS runtime

WORKDIR /app

COPY --from=build /app /app

EXPOSE 3000

CMD ["npm", "run", "start"]

