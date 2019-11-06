INSERT INTO student (id, email, name, password) VALUES ('fa8789f1-17a8-4937-b7db-5910471cc61b', 'isaias_neto@discente.ufg.br', 'Isaias Tavares da Silva Neto', '1e9f258f838afe310eb0da6501e3c354');

INSERT INTO classroom (id, instructor_id, name, subject_code, code) VALUES ('bf25d482-7425-4d13-9ac3-f3638af0cbc1', '5da3453a5718e904108acc25', 'Engenharia Econômica 2018-1', 'INF0233', 'LCF5540');
INSERT INTO classroom (id, instructor_id, name, subject_code, code) VALUES ('dd4164a8-4cf1-467a-af34-0d48d9d2b699', '5da3453a5718e904108acc25', 'Integração 2 2018-1', 'INF0089', 'CWS4558');

INSERT INTO enrollment (id, classroom_id, student_id) VALUES ('f20c113c-75d2-422d-89a1-c63fa7ba846b', 'dd4164a8-4cf1-467a-af34-0d48d9d2b699', 'fa8789f1-17a8-4937-b7db-5910471cc61b');