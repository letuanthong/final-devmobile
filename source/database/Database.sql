-- Bảng người dùng
CREATE TABLE users (
                       user_id VARCHAR(36) PRIMARY KEY,
                       user_full_name VARCHAR(255) NOT NULL,
                       user_gender VARCHAR(10) NOT NULL,
                       user_date_of_birth DATE NOT NULL,
                       user_identity_number VARCHAR(20) UNIQUE NOT NULL,
                       user_identity_issued_date DATE NOT NULL,
                       user_identity_expires_date DATE NOT NULL,
                       user_identity_issued_place VARCHAR(255) NOT NULL,
                       user_identity_expires_place VARCHAR(255) NOT NULL,
                       user_place_of_origin VARCHAR(255) NOT NULL,
                       user_place_of_residence VARCHAR(255) NOT NULL,
                       user_email VARCHAR(255) UNIQUE,
                       user_password TEXT NOT NULL,
                       user_phone_number VARCHAR(20) UNIQUE NOT NULL,
                       user_role VARCHAR(255) NOT NULL DEFAULT 'customer',
--                            ENUM('customer', 'officer')
                       user_picture TEXT,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Bảng eKYC
CREATE TABLE ekyc (
                      ekyc_id VARCHAR(36) PRIMARY KEY,
                      user_id VARCHAR(36),
                      ekyc_image_url TEXT,
                      ekyc_verified BOOLEAN DEFAULT FALSE,
                      ekyc_verified_at TIMESTAMP NULL,
                      FOREIGN KEY (user_id) REFERENCES users(user_id)
);

-- Bảng tài khoản
CREATE TABLE accounts (
                          account_id VARCHAR(36) PRIMARY KEY,
                          user_id VARCHAR(36),
                          account_type VARCHAR(255) NOT NULL,
--                               ENUM('checking', 'saving', 'mortgage')
                          account_number VARCHAR(20) UNIQUE NOT NULL,
                          account_balance DECIMAL(18,2) DEFAULT 0,
                          account_interest_rate DECIMAL(5,2),
                          account_monthly_payment DECIMAL(18,2),
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          FOREIGN KEY (user_id) REFERENCES users(user_id)
);

-- Bảng giao dịch
CREATE TABLE transactions (
                              transaction_id VARCHAR(36) PRIMARY KEY,
                              from_account_id VARCHAR(36),
                              to_account_id VARCHAR(36),
                              to_bank_name VARCHAR(255),
                              transaction_amount DECIMAL(18,2) NOT NULL,
                              transaction_type VARCHAR(255) NOT NULL,
--                                   ENUM('deposit', 'withdraw', 'transfer', 'bill_payment', 'phone_topup', 'ticket_purchase') NOT NULL,
                              transaction_status VARCHAR(255) NOT NULL DEFAULT 'pending',
--                                   ENUM('pending', 'success', 'failed')
                              otp_verified BOOLEAN DEFAULT FALSE,
                              created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                              FOREIGN KEY (from_account_id) REFERENCES accounts(account_id),
                              FOREIGN KEY (to_account_id) REFERENCES accounts(account_id)
);

-- Bảng hóa đơn / tiện ích
CREATE TABLE bills (
                       bill_id VARCHAR(36) PRIMARY KEY,
                       user_id VARCHAR(36),
                       bill_type VARCHAR(255) NOT NULL,
--                            ENUM('electricity', 'water', 'phone', 'movie', 'flight', 'hotel', 'ecommerce') NOT NULL,
                       provider_name VARCHAR(255),
                       bill_amount DECIMAL(18,2) NOT NULL,
                       bill_due_date TIMESTAMP,
                       paid BOOLEAN DEFAULT FALSE,
                       transaction_id VARCHAR(36),
                       FOREIGN KEY (user_id) REFERENCES users(user_id),
                       FOREIGN KEY (transaction_id) REFERENCES transactions(transaction_id)
);

-- Bảng chi nhánh ngân hàng
CREATE TABLE branches (
                          branch_id VARCHAR(36) PRIMARY KEY,
                          branch_name VARCHAR(255) NOT NULL,
                          branch_address TEXT,
                          branch_latitude DOUBLE,
                          branch_longitude DOUBLE
);

-- Bảng mã OTP
CREATE TABLE otp_codes (
                           otp_id VARCHAR(36) PRIMARY KEY,
                           user_id VARCHAR(36),
                           otp_code VARCHAR(10) NOT NULL,
                           otp_is_used BOOLEAN DEFAULT FALSE,
                           expires_at TIMESTAMP NOT NULL,
                           FOREIGN KEY (user_id) REFERENCES users(user_id)
);
