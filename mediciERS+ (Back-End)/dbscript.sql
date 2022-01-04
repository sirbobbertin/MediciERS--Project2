CREATE TABLE user_details(
				user_id INT GENERATED ALWAYS AS IDENTITY,
				user_password VARCHAR(20),
				user_first_name VARCHAR(20),
				user_last_name VARCHAR(20),
				user_address VARCHAR(100),
				user_contact VARCHAR(50),
				user_type VARCHAR(20),
				user_removed BOOLEAN,
				PRIMARY KEY(user_id));

INSERT INTO user_details(user_password, user_first_name, user_last_name, user_address, user_contact, user_type, user_removed) 
VALUES(123456, 'Michelle', 'Ng', 'California', '555-555-5555', 'Manager', FALSE);

INSERT INTO user_details(user_password, user_first_name, user_last_name, user_address, user_contact, user_type, user_removed) 
VALUES(123456, 'John', 'Doe', 'Texas', '333-333-3333', 'Employee', FALSE);

INSERT INTO user_details(user_password, user_first_name, user_last_name, user_address, user_contact, user_type, user_removed) 
VALUES(123456, 'Jane', 'Doe', 'Arizona', '222-222-2222', 'Employee', FAlSE);

CREATE TABLE reimbursement_details(
				rb_id INT GENERATED ALWAYS AS IDENTITY,
				user_id INT,
				rb_date DATE,
				rb_amount INT,
				rb_status VARCHAR(20),
				rb_resolved BOOLEAN,
				rb_removed BOOLEAN,
				PRIMARY KEY(rb_id),
				FOREIGN KEY(user_id) REFERENCES user_details(user_id));

INSERT INTO reimbursement_details(user_id, rb_date, rb_amount, rb_status, rb_resolved, rb_removed)
VALUES(2, '2021-12-03', 500, 'pending', FALSE, FALSE);


CREATE TABLE file_details(
				file_id varchar, 
				file_name varchar, 
				file_type varchar, 
				rb_id int, 
				file_data oid, 
				foreign key(rb_id) 
				REFERENCES reimbursement_details(rb_id) on delete cascade);
