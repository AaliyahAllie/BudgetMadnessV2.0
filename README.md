### Budget Madness

### Links
GitHub Link:
YouTube Link:
#### Introduction
This repository contains the code for Budget Madness, developed as part of our Part 2 assignment in the Open Source Coding course. The app is designed to help users effectively manage their finances by tracking expenses, monitoring income, and planning for future financial goals.

#### Contributors
* Aaliyah Allie
* Amaan Williams
* Aadam Naidoo
* Kenneth Michael Remmitz
* Moghammad Saadiq Jaatiem

### Programming Language
This application is built using Kotlin, leveraging our knowledge of the language for Android development.

### Colour Scheme
The app utilizes a color scheme of green, white, and black for a clean, modern look.

### Database Storage
For this part of the application, we are using SQLite (RoomDB) to store data locally. This is a requirement of the project outline, and in the future, we plan to migrate the data to an online database such as Firebase.

### App Functionality
### 1. App Launcher Screen
* Opens with a greeting, app logo, and options to either register or log in.
### 2. Register Screen
* Allows users to create a profile by providing their first name, last name, username, email, phone number, and password. All details are saved to the database.
### 3. Login Screen
* Allows users to log in based on their username and password, which are verified against the stored data in the database.
### 4. Get Started Screen
* Displays a singular logo and a button that navigates to the home screen.
### 5. Home Screen
* Displays user information:
+ Recently added expenses (highlighted in an orange block)
+ Recently added income (highlighted in a green block)
+ Balance (displayed in a black block)
+ A "Coming Soon" feature block (to be introduced in Part 3)
+ Bottom navigation for navigating to the Income screen, Home screen, Expenses screen, and Menu.
### 6. Profile Screen
+ Allows users to update their details and add a profile image.
### 7. Add Expenses Screen
+ Users can add their expenses by providing the following details:
+ Expense name
+ Amount
+ Date
+ Category
+ Payment method (Cash or Card)
+ Receipt image (optional)
### 8. Income Screen
+ Allows users to add their income, specifying the payment method (Cash or Card).
### 9. Balance Screen
+ Displays the total balance, along with the history of income and expense transactions.
### 10. View Expenses Screen
+ Shows the history of expenses within a selected date range.
### 11. Budget Screen
+ Allows users to set a maximum and minimum budget for each month. This feature will later display as a graph on the home screen in Part 3 of the project.
### 12. Bottom Nav
+ Bottom navigation for navigating to the Income screen, Home screen, Expenses screen, and Menu.
### 13.Menu
+ Navigates to all pages mentioned above

We hope this app provides valuable financial management tools for users!
