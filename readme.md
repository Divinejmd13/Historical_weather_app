
# Weather History App

The Weather History App is designed for users who want to download and store historical weather data for a foreign country. It provides functionality to retrieve weather information using a free weather API, input specific dates and years, and display maximum and minimum temperatures for those dates. Additionally, the app allows users to store weather data locally using **ROOM database**, ensuring access even without an internet connection.
## ScreenShot
![image](https://github.com/DIvineJMd/Historical_weather_app/assets/101663425/1c8e9f91-8820-453a-8258-669d2f18c035)![image](https://github.com/DIvineJMd/Historical_weather_app/assets/101663425/0acb5365-bfd6-49d4-8521-1da470b24811)
![image](https://github.com/DIvineJMd/Historical_weather_app/assets/101663425/aa912af3-1d90-49bb-974f-fae945ce0ead)![image](https://github.com/DIvineJMd/Historical_weather_app/assets/101663425/f4082e5a-91c8-4a94-80be-386d4d923596)![image](https://github.com/DIvineJMd/Historical_weather_app/assets/101663425/429c5d11-4087-4cff-91f0-cfcccda64e4c)
![image](https://github.com/DIvineJMd/Historical_weather_app/assets/101663425/ff5d8caa-4d74-4e58-ba52-b3f413eab138)




## Features

### Download Historical Weather Data

- Utilizes a free weather API to retrieve historical weather data in JSON format.
- Parses the JSON response to extract relevant weather information.

### Input Date and Year

- Users can input a specific date and year to view the maximum and minimum temperature for that date.

### Local Database Storage (Using ROOM)

- The app uses **ROOM database** for local storage.
- Upon initialization, it creates the necessary database schema and tables.
- Weather data can be inserted into the database and retrieved as needed.

### Handling Future Dates

- In cases where the input date is in the future, the app calculates the average of the last 10 available years' temperatures.

## Getting Started

To start using the Weather History App, follow these steps:

1. **Prerequisites:**
   - Ensure you have Android Studio installed on your computer.
   - An internet connection is required to download dependencies and access the weather API.

2. **Installation:**
   - Clone this repository to your local machine using the command:
     ```
     [git clone https://github.com/yourusername/weather-history-app.git](https://github.com/DIvineJMd/Historical_weather_app.git)
     ```
   - Open the project in Android Studio.
   - Build the project to download necessary dependencies.
   - Run the app on an Android emulator or physical device.

## Usage

1. **Input Date and Year:**
   - Upon launching the app, you'll see an option to input a date and year.
   - Enter the desired date and year, then click the "Search" button.

2. **View Temperature Data:**
   - The app will retrieve and display the maximum and minimum temperature for the specified date.

3. **Local Storage (Optional):**
   - Click the "Save" button to store the weather data locally using **ROOM database**.

## Implementation Details

### Weather API Integration

- Utilizes a free weather API to download historical weather data in JSON format.
- Parses the JSON response to extract relevant weather information.

### ROOM Database Integration

- Uses ROOM for local storage.
- Creates necessary database schema and tables during app initialization.
- Inserts weather data into the database and retrieves it when needed.

### UI Design

- Designed a user-friendly interface using Compose UI toolkit.
- Validates user input and displays appropriate error messages.
- Provides smooth navigation and intuitive controls.

