-- users
INSERT INTO users (user_id, user_full_name, user_email, user_password, user_phone_number, user_role, user_picture)
VALUES 
('5b4f3c3d-9f60-4e2c-908f-1c0ec7c96d8a', 'Nguyễn Văn A', 'vana@example.com', 'pass123', '0938179726', 'customer', NULL),
('41cacc8a-5b72-4e33-9b9f-5090d05f179b', 'Trần Thị B', 'thib@example.com', 'pass123', '0938179725', 'officer', NULL);

-- ekyc
INSERT INTO ekyc (ekyc_id, user_id, ekyc_image_url, ekyc_verified, ekyc_verified_at)
VALUES
('aacda8a4-e005-4e8f-9001-3b8a93bdfd3a', '5b4f3c3d-9f60-4e2c-908f-1c0ec7c96d8a', 'https://example.com/ekyc/u-001.jpg', TRUE, NOW()),
('bf34ccf7-9f95-4931-b6cf-2a69d8baf5b1', '41cacc8a-5b72-4e33-9b9f-5090d05f179b', 'https://example.com/ekyc/u-002.jpg', FALSE, NULL);

-- accounts
INSERT INTO accounts (account_id, user_id, account_type, account_number, account_balance, account_interest_rate, account_monthly_payment)
VALUES
('df03f306-1a66-4b8f-906a-5adbd682302a', '5b4f3c3d-9f60-4e2c-908f-1c0ec7c96d8a', 'checking', '1001001001', 5000000, NULL, NULL),
('18fcb7b5-2b8f-4f7b-b204-402878cf2be0', '5b4f3c3d-9f60-4e2c-908f-1c0ec7c96d8a', 'saving', '1001001002', 30000000, 3.5, NULL),
('77419537-1121-4d64-9903-e2ea5d68de4d', '41cacc8a-5b72-4e33-9b9f-5090d05f179b', 'mortgage', '1002001001', -150000000, NULL, 5000000);

-- transactions
INSERT INTO transactions (transaction_id, from_account_id, to_account_id, to_bank_name, transaction_amount, transaction_type, transaction_status, otp_verified)
VALUES
('a1f60d87-ecae-43a5-b28a-62320f5a8e56', 'df03f306-1a66-4b8f-906a-5adbd682302a', '18fcb7b5-2b8f-4f7b-b204-402878cf2be0', NULL, 1000000, 'transfer', 'success', TRUE),
('54cd2532-84d5-48d1-980e-b4375d7300c5', 'df03f306-1a66-4b8f-906a-5adbd682302a', NULL, 'ACB Bank', 500000, 'bill_payment', 'success', TRUE),
('e2cf66c4-9dbe-4ccf-992e-b5eaf842a4a2', 'df03f306-1a66-4b8f-906a-5adbd682302a', NULL, 'Momo', 100000, 'phone_topup', 'pending', FALSE);

-- bills
INSERT INTO bills (bill_id, user_id, bill_type, provider_name, bill_amount, bill_due_date, paid, transaction_id)
VALUES
('ae2f7b3e-57de-4fd3-83e7-c9f942cb4ad4', '5b4f3c3d-9f60-4e2c-908f-1c0ec7c96d8a', 'electricity', 'EVN HCM', 450000, '2025-05-15 00:00:00', TRUE, '54cd2532-84d5-48d1-980e-b4375d7300c5'),
('ec6e5823-ecfb-421a-9d0c-4913c46de9f0', '5b4f3c3d-9f60-4e2c-908f-1c0ec7c96d8a', 'phone', 'Vinaphone', 100000, '2025-05-10 00:00:00', FALSE, NULL);

-- branches
INSERT INTO branches (branch_id, branch_name, branch_address, branch_latitude, branch_longitude)
VALUES
('d13be6c0-4193-4d7f-905e-efc3894f15b1', 'Chi nhánh Tân Bình', '68 Trường Chinh, Q.Tân Bình, TP.HCM', 10.801, 106.652),
('2f27d7d1-16ff-4fd1-9f3e-3fd4bc0e4eb2', 'Chi nhánh Quận 1', '12 Nguyễn Huệ, Q.1, TP.HCM', 10.776, 106.701);

-- otp_codes
INSERT INTO otp_codes (otp_id, user_id, otp_code, otp_is_used, expires_at)
VALUES
('654ef9d7-6c84-40f6-8ec4-49b70d56c7ae', '5b4f3c3d-9f60-4e2c-908f-1c0ec7c96d8a', '123456', FALSE, DATE_ADD(NOW(), INTERVAL 5 MINUTE)),
('7b09e334-1234-44be-babc-38f0fa5101f9', '5b4f3c3d-9f60-4e2c-908f-1c0ec7c96d8a', '654321', TRUE, DATE_SUB(NOW(), INTERVAL 10 MINUTE));
