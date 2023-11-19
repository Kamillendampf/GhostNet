CREATE DATABASE ghostnets;
\c ghostnets;
CREATE TABLE IF NOT EXISTS nets(gn_id serial PRIMARY KEY, gn_lat  VARCHAR(250) not null, gn_lon  VARCHAR(250) not null, gn_size  INT not null, gn_editor VARCHAR(250) null, gn_reporter VARCHAR(250) not null, gn_status INT not null, unique(gn_id, gn_lat, gn_lon, gn_size, gn_reporter));
CREATE TABLE IF NOT EXISTS licens(l_name VARCHAR(250) not null, l_number VARCHAR(250) not null, l_key VARCHAR(250) not null);