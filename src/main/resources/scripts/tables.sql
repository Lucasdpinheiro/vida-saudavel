CREATE SCHEMA IF NOT EXISTS saude AUTHORIZATION sa;

CREATE TABLE IF NOT EXISTS saude.users (
   id INT NOT NULL GENERATED ALWAYS AS IDENTITY,
   full_name VARCHAR(250) NOT NULL,
   birth_day DATE NOT NULL,
   weight_ INT NOT NULL,
   height FLOAT NOT NULL,
   goal INT NOT NULL,

      CONSTRAINT user_fk_goal
      FOREIGN KEY(goal)
      REFERENCES saude.goals(id),

   PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS saude.goals (
    id INT NOT NULL GENERATED ALWAYS AS IDENTITY,
    hydration INT NOT NULL,
    weightloss FLOAT NOT NULL;
);