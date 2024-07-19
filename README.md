<img src="https://github.com/AAKULZ/Unified-Voting-Aplication/blob/main/app/src/main/res/drawable/viconh.png" alt="Logo" width="300" height="150" align="left"> Unified Voting Application

Welcome to the Unified Voting Application repository! This Android application revolutionizes the electoral process by providing a secure, user-friendly platform for managing and participating in elections. Developed using Java and XML, it incorporates cutting-edge technologies such as multifactor authentication, RAID 6+ database security, and Firebase for storage and RDBMS.

![Voting Animation](https://github.com/AAKULZ/Unified-Voting-Aplication/blob/main/app/src/main/res/drawable/imrs.gif)

## Table of Contents

1. [Introduction](#introduction)
2. [Features](#features)
3. [Installation](#installation)
4. [Usage](#usage)
5. [Multifactor Authentication](#multifactor-authentication)
6. [Database Security](#database-security)
7. [Firebase Integration](#firebase-integration)
8. [Admin Mode](#admin-mode)
9. [User Mode](#user-mode)
10. [Autonomous Operations](#autonomous-operations)
11. [Real-time Updates](#real-time-updates)
12. [Contributing](#contributing)
13. [License](#license)

## Introduction

The Unified Voting Application is designed to streamline and secure the voting process for any organization. By leveraging modern technologies, it ensures the integrity and reliability of elections, from candidate nomination to result declaration.

## Features

- **Multifactor Authentication (MFA):** Ensures secure access through OTP verification.
- **RAID 6+ Database Security:** Provides high availability and data protection.
- **Firebase Integration:** Utilizes Firebase for storage and RDBMS.
- **Admin Mode:** Comprehensive tools for managing elections, including creation, deletion, authority delegation, and form releases.
- **User Mode:** Enables users to vote, fill candidature forms, and view results.
- **Autonomous Operations:** Automatically handles scheduling and execution of elections.
- **Real-time Updates:** Synchronizes data and updates across all user interfaces.

## Installation

To install and set up the Unified Voting Application, follow these steps:

1. Clone the repository:
    ```sh
    git clone https://github.com/yourusername/unified-voting-app.git
    ```

2. Open the project in Android Studio.

3. Sync the project with Gradle files.

4. Configure Firebase:
    - Go to the [Firebase Console](https://console.firebase.google.com/).
    - Create a new project and add your Android app.
    - Download the `google-services.json` file and place it in the `app` directory.

5. Build and run the application on your Android device or emulator.

## Usage

### Admin Mode

Admins have access to a range of features for managing elections:

1. **Create Election:**
    - Navigate to the "Create Election" section.
    - Fill in details such as election name, dates, and candidates.
    - Click "Create" to save the election.

2. **Delete Election:**
    - Select an existing election from the list.
    - Click "Delete" to remove it.

3. **Authority Delegation:**
    - Assign temporary admin rights to other users.
    - Set expiration dates for these rights.

4. **Form Release:**
    - Create and release forms for candidature and polling.
    - Manage submission deadlines.

### User Mode

Users can participate in elections through the following features:

1. **Vote:**
    - View active elections.
    - Select candidates and cast votes securely.

2. **Fill Candidature Forms:**
    - Access and fill out forms for election candidacy.
    - Submit forms before the deadline.

3. **View Results:**
    - Access election results once they are declared.
    - Results are updated in real-time.

## Multifactor Authentication

The application implements multifactor authentication (MFA) using OTP for secure user access. Upon login, users receive a one-time password (OTP) via SMS or email, which they must enter to complete the authentication process.

## Database Security

To ensure data integrity and availability, the application employs a RAID 6+ database system. This setup provides advanced redundancy and fault tolerance, protecting election data from loss or corruption.

## Firebase Integration

Firebase is utilized for both storage and RDBMS, offering real-time database capabilities and seamless data synchronization. This ensures that all users have access to the most current information at all times.

## Admin Mode

Admin mode offers a comprehensive suite of tools for managing elections. Admins can create, delete, and modify elections, delegate authority, release forms, and oversee the entire electoral process.

![Admin Mode Animation](https://media.giphy.com/media/xT0xeJpnrWC4XWblEk/giphy.gif)

### Key Admin Features:

- **Election Creation and Deletion**
- **Authority Delegation**
- **Form Management**
- **Polling Management**

## User Mode

User mode is designed to facilitate easy participation in elections. Users can securely vote, submit candidature forms, and view results, all within the application.

### Key User Features:

- **Voting**
- **Candidature Form Submission**
- **Result Viewing**

## Autonomous Operations

The application autonomously handles election scheduling and execution. Admins can set specific dates and times for various stages of the election, including form releases, polling, and result declaration.

![Autonomous Operations Animation](https://media.giphy.com/media/l0HlBO7eyXzSZkJri/giphy.gif)

## Real-time Updates

Real-time synchronization ensures that all users have access to the latest information. Any changes made by admins are immediately reflected across the application, providing a seamless user experience.

## Contributing

We welcome contributions to the Unified Voting Application! To contribute, please follow these steps:

1. Fork the repository.
2. Create a new branch:
    ```sh
    git checkout -b feature/YourFeatureName
    ```
3. Make your changes and commit them:
    ```sh
    git commit -m 'Add some feature'
    ```
4. Push to the branch:
    ```sh
    git push origin feature/YourFeatureName
    ```
5. Open a pull request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

Thank you for using the Unified Voting Application! If you have any questions or need further assistance, please open an issue or contact us. Happy voting!

![Happy Voting](https://media.giphy.com/media/3oEjI6SIIHBdRxXI40/giphy.gif)
