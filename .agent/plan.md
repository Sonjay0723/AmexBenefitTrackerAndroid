# Project Plan

Develop an Android version of the 'Amex Benefit Tracker' Windows application. The app helps users track their American Express card benefits (Platinum and Gold cards). It should feature a clean, Material 3 design with a dark theme as seen in the reference image. Key features include tracking monthly credits (like Uber Cash), semi-annual credits (like Hotel Credit), and annual credits. It should calculate the 'Effective Annual Fee' and 'Profit' based on benefits used. Refer to the GitHub repo https://github.com/Sonjay0723/AmexBenefitTracker for data structures and logic.

## Project Brief

# Project Brief: Amex Benefit Tracker (Android)

A streamlined mobile utility designed to help American Express Platinum and Gold cardholders maximize their membership value. This app migrates the core logic of the 'Amex Benefit Tracker' to a modern, adaptive Android experience, ensuring users never leave money on the table.

## Features
*   **Card Portfolio Dashboard**: A high-level overview of active cards (Platinum, Gold) displaying total utilized credit value versus the total annual fees.
*   **Dynamic Benefit Ledger**: Categorized tracking for Monthly (e.g., Uber Cash, Dining Credit), Semi-Annual (e.g., Saks Fifth Avenue), and Annual credits with visual progress indicators.
*   **Financial Impact Calculator**: Real-time logic engine that calculates the "Effective Annual Fee" and "Total Profit" based on the dollar value of benefits redeemed.
*   **Adaptive Multi-Pane Interface**: A responsive Material 3 layout that seamlessly transitions between a list of benefits and detailed credit history, optimized for both phones and tablets.

## High-Level Technical Stack
*   **Kotlin & Jetpack Compose**: Core language and modern declarative UI framework.
*   **Jetpack Navigation 3**: State-driven navigation architecture for robust screen transitions.
*   **Compose Material Adaptive**: Implementation of `ListDetailPaneScaffold` and `NavigationSuiteScaffold` to support foldable and large-screen devices.
*   **Room Database**: Local persistence for tracking benefit usage history, card enrollments, and customized benefit values.
*   **Kotlin Coroutines & Flow**: Asynchronous processing for financial calculations and reactive UI updates.

## UI Design Image
![UI Design](file:///C:/Users/jpitt/AndroidStudioProjects/AmexBenefitTracker/input_images/image_0.png)

## Implementation Steps

### Task_1_DataAndLogic: Define Room entities (Card, Benefit, UsageHistory), DAOs, and the Repository. Implement the calculator logic for 'Effective Annual Fee' and 'Profit' based on the reference GitHub repo logic.
- **Status:** IN_PROGRESS
- **Updates:** Writing data models to files.
- **Acceptance Criteria:**
  - Room DB is functional
  - Calculation logic matches requirements
  - Build passes

### Task_2_UIFoundationAndDashboard: Set up Material 3 theme (Dark/Light), Edge-to-Edge display, and Navigation 3. Implement the main Dashboard screen displaying the card portfolio overview.
- **Status:** PENDING
- **Acceptance Criteria:**
  - Dark theme active
  - Dashboard UI shows card summary
  - The implemented UI must match the design provided in C:/Users/jpitt/AndroidStudioProjects/AmexBenefitTracker/input_images/image_0.png

### Task_3_BenefitLedgerAndTracking: Implement the Benefit Ledger UI using Adaptive scaffolds. Create lists for Monthly, Semi-Annual, and Annual benefits with progress indicators. Add functionality to log benefit usage.
- **Status:** PENDING
- **Acceptance Criteria:**
  - Ledger UI functional on phone/tablet
  - Progress indicators update correctly
  - The implemented UI must match the design provided in C:/Users/jpitt/AndroidStudioProjects/AmexBenefitTracker/input_images/image_0.png

### Task_4_AssetsAndVerification: Create an adaptive app icon matching the Amex theme. Perform final UI refinements. Run and verify application stability (no crashes) and alignment with requirements.
- **Status:** PENDING
- **Acceptance Criteria:**
  - Adaptive icon present
  - App runs without crashes
  - All existing tests pass
  - Build pass

