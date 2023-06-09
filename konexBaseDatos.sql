CREATE TABLE medicament(
  id NUMBER GENERATED BY DEFAULT AS IDENTITY,
  name VARCHAR2(50),
  laboratorie VARCHAR2(50),
  date_manufacture TIMESTAMP(8),
  date_expiration TIMESTAMP(8),
  quantity_stock NUMBER,
  unit_value NUMBER,
  PRIMARY KEY (id),
  CONSTRAINT UQ_medicamentos UNIQUE (name)
);

CREATE TABLE sale(
  id NUMBER GENERATED BY DEFAULT AS IDENTITY,
  date_sale TIMESTAMP(8),
  time_sale VARCHAR2(8),
  medicamentid NUMBER,
  quantity NUMBER,
  unit_value NUMBER(10,2),
  total_value NUMBER(10,2),
  PRIMARY KEY (id),
  FOREIGN KEY (medicamentid) REFERENCES medicament(id)
);