FROM node:20.11.0-alpine

WORKDIR /app

# Copy the contents of the dist directory into the container's working directory
COPY dist/front-end-emploi-connect/* .
#RUN ng build --configuration=production

EXPOSE 4200

# Install the HTTP server globally
RUN npm install -g http-server

#Run the HTTP server http-server on port 4200
CMD ["http-server", "-p", "4200"]
