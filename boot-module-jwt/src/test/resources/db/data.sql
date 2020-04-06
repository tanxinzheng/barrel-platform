DELETE FROM t_user;

INSERT INTO t_user (id, username, password, salt, name, age, email) VALUES
(6, 'admin', '939e3704e692394a35f64638b5ff08d7', '3242343', 'admin', 18, 'admin@test.com'),
(1, 'Jone', '939e3704e692394a35f64638b5ff08d7', '42321', 'Jone', 18, 'test1@baomidou.com'),
(2, 'Jack', '939e3704e692394a35f64638b5ff08d7', '42321', 'Jack', 20, 'test2@baomidou.com'),
(3, 'Tom', '939e3704e692394a35f64638b5ff08d7', '42321', 'Tom', 28, 'test3@baomidou.com'),
(4, 'Sandy', '939e3704e692394a35f64638b5ff08d7', '42321',  'Sandy', 21, 'test4@baomidou.com'),
(5, 'Billie', '939e3704e692394a35f64638b5ff08d7', '42321', 'Billie', 24, 'test5@baomidou.com');