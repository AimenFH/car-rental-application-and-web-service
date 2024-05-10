# Base image
FROM node:16

# Set working directory
WORKDIR /app

# Copy application dependencies
COPY package.json package-lock.json ./

# Install dependencies
RUN npm install

# Copy all application files
COPY . .

# Build the Angular application
RUN npm run build

# Expose the port used by `ng serve`
EXPOSE 4200

# Run the Angular development server
CMD ["npm", "start"]
