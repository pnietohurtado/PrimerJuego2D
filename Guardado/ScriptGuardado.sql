-- Guardado de partida de Mi primer juego 2D 

DROP DATABASE IF EXISTS guardado; 
CREATE DATABASE IF NOT EXISTS guardado; 
USE guardado; 

CREATE TABLE jugador(
	ID INT NOT NULL PRIMARY KEY, 
    Pos_X INT NOT NULL,
    POS_Y INT NOT NULL
)