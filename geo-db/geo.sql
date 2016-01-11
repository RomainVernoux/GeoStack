-- NEW SCHEMA
CREATE SCHEMA geo;

ALTER SCHEMA geo OWNER TO postgres;



-- USER TABLE
CREATE TABLE geo.t_user_us
(
  us_id bigserial NOT NULL,
  us_name text,
  us_gcm_token text,
  CONSTRAINT pk_us PRIMARY KEY (us_id)
);

ALTER TABLE geo.t_user_us
  OWNER TO postgres;

INSERT INTO geo.t_user_us (us_name, us_gcm_token)
VALUES ('Romain Vernoux', 1, NULL);



-- LOCATION TABLE
CREATE TABLE geo.t_position_po
(
  po_id bigserial NOT NULL,
  us_id bigint,
  po_time timestamp with time zone,
  po_position geometry,
  CONSTRAINT pk_po PRIMARY KEY (po_id),
  CONSTRAINT t_position_po_us_id_fkey FOREIGN KEY (us_id)
      REFERENCES geo.t_user_us (us_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

ALTER TABLE geo.t_position_po
  OWNER TO postgres;



-- FENCE TABLE
CREATE TABLE geo.t_fence_fe
(
  fe_id bigserial NOT NULL,
  fe_name text,
  fe_geometry geometry,
  CONSTRAINT pk_fe PRIMARY KEY (fe_id)
);

ALTER TABLE geo.t_fence_fe
  OWNER TO postgres;

INSERT INTO geo.t_fence_fe (fe_id, fe_name, fe_geometry) VALUES 
(1, 'Place de l''Etoile', ST_Buffer(ST_MakePoint(2.295028, 48.873768), 0.0009, 'quad_segs=8')),
(2, 'George V', ST_Buffer(ST_MakePoint(2.300538, 48.872035), 0.0003, 'quad_segs=8'));
