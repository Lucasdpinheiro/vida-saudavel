CREATE SCHEMA IF NOT EXISTS saude AUTHORIZATION sa;

DROP TABLE IF EXISTS saude.users;
CREATE TABLE IF NOT EXISTS saude.users (
   id INT NOT NULL GENERATED ALWAYS AS IDENTITY,
   full_name VARCHAR(250) NOT NULL,
   birth_day VARCHAR(50) NOT NULL,
   weight INT NOT NULL,
   height INT NOT NULL,
   goal VARCHAR(20),

      CONSTRAINT user_fk_goal
      FOREIGN KEY(goal)
      REFERENCES saude.goals(id),

   PRIMARY KEY(id)
);

DROP TABLE IF EXISTS saude.goals;
CREATE TABLE IF NOT EXISTS saude.goals (
    id INT NOT NULL,
    goal VARCHAR(20),

);

INSERT INTO saude.goals (id, user_fk_goal) VALUES
   (1, 'WEIGHTLOSS'),
   (2, 'HYPERTROPHY');