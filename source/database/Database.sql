CREATE TABLE users (
    user_id VARCHAR(12) PRIMARY KEY,
    user_fullname VARCHAR(255) NOT NULL,
    user_email VARCHAR(255) UNIQUE NOT NULL,
    user_phonenumber VARCHAR(20) UNIQUE NOT NULL,
    user_address TEXT,
    user_role ENUM('Customer', 'BankOfficer') NOT NULL,
    user_password VARCHAR(255) NOT NULL,
    user_bio BLOB,
    IssuedDate DATE NOT NULL,
    ExpirationDate DATE,
    IssuedPlace VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE checking (
    checking_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id VARCHAR(12) NOT NULL,
    checking_balance DECIMAL(15,2) DEFAULT 0.00,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

CREATE TABLE saving (
    saving_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id VARCHAR(12) NOT NULL,
    saving_balance DECIMAL(15,2) DEFAULT 0.00,
    saving_interest_rate DECIMAL(5,2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

CREATE TABLE mortgage (
    mortgage_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id VARCHAR(12) NOT NULL,
    mortgage_loan_amount DECIMAL(15,2) NOT NULL,
    mortgage_balance DECIMAL(15,2) NOT NULL,
    mortgage_monthly_payment DECIMAL(15,2) NOT NULL,
    mortgage_loan_term INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

CREATE TABLE mortgage_detail (
    mortgage_detail_id INT PRIMARY KEY AUTO_INCREMENT,
    mortgage_id INT NOT NULL,
    mortgage_detail_due_date DATE NOT NULL,
    mortgage_detail_amount DECIMAL(15,2) NOT NULL,
    mortgage_detail_status ENUM('Pending', 'Paid', 'Overdue') DEFAULT 'Pending',
    mortgage_detail_payment_date TIMESTAMP NULL,
    FOREIGN KEY (mortgage_id) REFERENCES mortgage(mortgage_id) ON DELETE CASCADE
);

CREATE TABLE Transactions (
    TransactionID INT PRIMARY KEY AUTO_INCREMENT,
    AccountID INT NOT NULL,
    TransactionType ENUM('Deposit', 'Withdrawal', 'Transfer', 'Payment') NOT NULL,
    Amount DECIMAL(15,2) NOT NULL,
    Status ENUM('Pending', 'Completed', 'Failed') DEFAULT 'Pending',
    Timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE TwoFactorAuth (
    AuthID INT PRIMARY KEY AUTO_INCREMENT,
    UserID VARCHAR(12) NOT NULL,
    OTPCode VARCHAR(6) NOT NULL,
    Expiration TIMESTAMP NOT NULL,
    Status ENUM('Unused', 'Used') DEFAULT 'Unused',
    FOREIGN KEY (UserID) REFERENCES Users(UserID) ON DELETE CASCADE
);

CREATE TABLE Payments (
    PaymentID INT PRIMARY KEY AUTO_INCREMENT,
    UserID VARCHAR(12) NOT NULL,
    PaymentType ENUM('Electricity', 'Water', 'PhoneTopUp', 'FlightTicket', 'MovieTicket', 'HotelBooking', 'Ecommerce') NOT NULL,
    Amount DECIMAL(15,2) NOT NULL,
    Status ENUM('Pending', 'Completed', 'Failed') DEFAULT 'Pending',
    Timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (UserID) REFERENCES Users(UserID) ON DELETE CASCADE
);

CREATE TABLE BankBranches (
    BranchID INT PRIMARY KEY AUTO_INCREMENT,
    BranchName VARCHAR(255) NOT NULL,
    Address TEXT NOT NULL,
    Latitude DECIMAL(10,8),
    Longitude DECIMAL(11,8)
);