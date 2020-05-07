INSERT INTO person(id, name, lastName, identification_type, identification_number, birth_date, gender, deceased, blood_type)
VALUES (1, 'Jhonny Alexander', 'Parra Barrera', 'CC', 123, '1999-04-14', 'M', false, 'O+');

INSERT INTO person(id, name, lastName, identification_type, identification_number, birth_date, gender, deceased, blood_type)
VALUES (2, 'Laura Milena', 'Donado Yance', 'CC', 321, '1999-04-14', 'F', false, 'O+');

INSERT INTO person(id, name, lastName, identification_type, identification_number, birth_date, gender, deceased, blood_type)
VALUES (3, 'Omar Steck', 'Espinel Santamaria', 'CC', 213, '1999-04-14', 'M', false, 'O+');

INSERT INTO person(id, name, lastName, identification_type, identification_number, birth_date, gender, deceased, blood_type)
VALUES (4, 'Pedro Santiago', 'Lizarazo', 'CC', 132, '1999-04-14', 'M', false, 'O+');


INSERT INTO health_entity(id, name)
VALUES (1, 'Clinica A');

INSERT INTO health_entity(id, name)
VALUES (2, 'Clinica B');

INSERT INTO health_entity(id, name)
VALUES (3, 'Clinica C');


INSERT INTO health_user(id, password, fingerprint, person_id)
VALUES (1, '123', 'fingerprint_test', 1);

INSERT INTO health_user(id, password, fingerprint, person_id)
VALUES (2, '123', 'fingerprint_test', 2);

INSERT INTO health_user(id, password, fingerprint, person_id)
VALUES (3, '123', 'fingerprint_test', 3);

INSERT INTO health_user(id, password, fingerprint, person_id)
VALUES (4, '123', 'fingerprint_test', 4);


INSERT INTO qualification(id, name, start_date, reporting_entity, user_id)
VALUES (1, 'Ingeniero de sistemas', '1999-04-05', 'Pontificia Universidad Javeriana', 1); 

INSERT INTO qualification(id, name, start_date, reporting_entity, user_id)
VALUES (2, 'Médico', '1999-05-05', 'Escuela de médicos', 1);

INSERT INTO health_authorization(user_id, health_entity_id, role)
VALUES (1, 1, 'ROLE_ADMINISTRATOR');

INSERT INTO health_authorization(user_id, health_entity_id, role)
VALUES (1, 2, 'ROLE_ADMINISTRATOR');

INSERT INTO health_authorization(user_id, health_entity_id, role)
VALUES (1, 3, 'ROLE_ADMINISTRATOR');

--INSERT INTO health_authorization(user_id, health_entity_id, role)
--VALUES (1, 1, 'ROLE_NURSE');

--INSERT INTO health_authorization(user_id, health_entity_id, role)
--VALUES (1, 1, 'ROLE_ADMINISTRATIVE_ASSISTANT');

--INSERT INTO health_authorization(user_id, health_entity_id, role)
--VALUES (1, 1, 'ROLE_PATIENT');

--INSERT INTO health_authorization(user_id, health_entity_id, role)
--VALUES (1, 1, 'ROLE_ADMINISTRATOR');



