# Console Finance Tracker 💰

A robust, object-oriented Java command-line interface (CLI) application for logging and reviewing daily financial expenses. Data is securely persisted locally using a standard CSV architecture.

## Features
- **Object-Oriented Design:** Clean separation of data models and execution logic.
- **Persistent Storage:** Automatically writes and reads financial records via a flat-file (`expenses.csv`) system.
- **Formatted Reporting:** Generates dynamic, table-formatted summaries of all past transactions along with total lifetime spending.
- **Data Validation:** Catches and prevents application crashes from invalid user input (e.g., entering text instead of numeric values).

## Usage

1. Clone this repository or download `ExpenseTracker.java`.
2. Compile the Java file from your terminal:
   ```bash
   javac ExpenseTracker.java
