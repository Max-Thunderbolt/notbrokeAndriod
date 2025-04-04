# NotBrokeUI - Android Financial Management Application

## Overview
NotBrokeUI is an Android application designed to help users manage their finances, track expenses, and maintain budgets. The application provides a user-friendly interface for financial management with features like transaction tracking, budget categorization, and financial insights.

## Features
- **User Authentication**: Secure login and registration system
- **Dashboard**: Overview of financial status with key metrics
- **Transaction Management**: Track income and expenses with detailed categorization
- **Budget Planning**: Set and monitor budgets for different categories
- **Visual Analytics**: Graphical representation of financial data
- **Responsive Design**: Modern UI with intuitive navigation

## Technical Details
- **Platform**: Android
- **Language**: Kotlin
- **Architecture**: MVVM (Model-View-ViewModel)
- **UI Components**: Material Design, RecyclerViews, Fragments
- **Data Storage**: Local storage with Room Database (planned)

## Project Structure
- **Activities**:
  - `MainActivity`: Entry point and login screen
  - `RegisterActivity`: User registration
  - `DashboardActivity`: Main application interface

- **Fragments**:
  - `BudgetFragment`: Budget management interface

- **Adapters**:
  - `TransactionAdapter`: Displays transaction items
  - `BudgetCategoryAdapter`: Manages budget category display

- **Models**:
  - `Transaction`: Data model for financial transactions
  - `TestData`: Sample data for development and testing

## Setup and Installation
1. Clone the repository:
   ```
   git clone https://github.com/Max-Thunderbolt/notbrokeAndriod.git
   ```

2. Open the project in Android Studio

3. Build and run the application on an Android device or emulator

## Development
- The application uses Kotlin for development
- Follow Material Design guidelines for UI components
- Implement proper error handling and user feedback
- Use appropriate logging for debugging

## Future Enhancements
- Database integration for persistent storage
- Cloud synchronization
- Advanced analytics and reporting
- Export functionality for financial data
- Multi-currency support

## Contributing
Contributions are welcome! Please feel free to submit a Pull Request.

## License
This project is licensed under the MIT License - see the LICENSE file for details.

## Contact
For any questions or suggestions, please contact the development team. 