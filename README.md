# EventSync – IBM Internship Exercise

EventSync is a lightweight Spring Boot application designed for the IBM internship challenge.  
It allows users to create events, submit written feedback, and automatically analyze sentiment using the Hugging Face Inference API.  
All data is stored in an **in-memory H2 database**, so no external setup is required.

## Requirements

Before running EventSync, you must provide your Hugging Face API token so the application can classify feedback as **positive**, **neutral**, or **negative**.
The application expects an environment variable:
If this variable is not set, the system falls back to a dummy token and all feedback will appear as **neutral**.

### Required Variable

- `HF_API_TOKEN` — your Hugging Face API token.

## Configuring the HF_API_TOKEN

### Setting Environment Variables on Windows

1. Open  
   **System Properties → Advanced → Environment Variables**
2. Create a new variable:
   - *Name:* `HF_API_TOKEN`
   - *Value:* your Hugging Face token
3. Restart your terminal or IDE.

### Setting Environment Variables on macOS/Linux

Add this to your shell config:

  ```sh
  echo 'export HF_API_TOKEN=your_api_token' >> ~/.bashrc
  source ~/.bashrc
  ```
### Setting Environment Variables in IntelliJ IDEA (Alternative)

1. Open IntelliJ IDEA and go to `Run` → `Edit Configurations`.
2. Select your Spring Boot run configuration.
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


The interface lets you:

Create new events

Submit participant feedback

View feedback sentiment (POSITIVE / NEUTRAL / NEGATIVE)

See automatic event sentiment summaries
