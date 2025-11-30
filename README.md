# EventSync – IBM Internship Exercise

This project uses an **in-memory H2 database**, so no external database setup is required.

## Setting Up Environment Variables

EventSync uses the Hugging Face Inference API for sentiment analysis.
You must configure the following environment variable:

### Required Variable

- `HF_API_TOKEN` — your Hugging Face API token.

### Setting Environment Variables on Windows

1. Open System Properties → Advanced → Environment Variables.
2. Create a new variable:
    - Name: `HF_API_TOKEN`
    - Value: `your_api_token`
3. Save and restart your terminal/IDE.

### Setting Environment Variables on macOS/Linux

Add this to your shell config:

  ```sh
  echo 'export HF_API_TOKEN=your_api_token' >> ~/.bashrc
  source ~/.bashrc
  ```
### Setting Environment Variables in IntelliJ IDEA (Alternative)

1. Open IntelliJ IDEA and go to `Run` → `Edit Configurations`.
2. Select your Spring Boot\ Maven run configuration.
3. In the `Environment Variables` field, click the `...` button.
4. Add: HF_API_TOKEN=your_api_token.
5. Click `OK` to save the configuration.****

## Running 

Run the application:

    ```sh
    ./mvnw spring-boot:run
    ```
## Accessing the Application UI

Open your browser at:
http://localhost:8080
