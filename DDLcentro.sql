CREATE TABLE Evento (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    Data DATE NOT NULL,
    Nome VARCHAR(100) NOT NULL,
    Locatario VARCHAR(100)
);

CREATE TABLE Espaco (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    Nome VARCHAR(100) NOT NULL,
    Disponibilidade INT NOT NULL,
    Preco DECIMAL(10, 2) NOT NULL,
    Capacidade INT NOT NULL
);

CREATE TABLE Atracao (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    Nome VARCHAR(100) NOT NULL,
    Cache DECIMAL(10, 2),
    Data DATE
);

CREATE TABLE Equipamento (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    Nome VARCHAR(100) NOT NULL,
    Disponibilidade INT NOT NULL
);

CREATE TABLE Evento_Espaco (
    EventoID INT NOT NULL,
    EspacoID INT NOT NULL,
    PRIMARY KEY (EventoID, EspacoID),
    FOREIGN KEY (EventoID) REFERENCES Evento(ID),
    FOREIGN KEY (EspacoID) REFERENCES Espaco(ID)
);

CREATE TABLE Evento_Atracao (
    EventoID INT NOT NULL,
    AtracaoID INT NOT NULL,
    PRIMARY KEY (EventoID, AtracaoID),
    FOREIGN KEY (EventoID) REFERENCES Evento(ID),
    FOREIGN KEY (AtracaoID) REFERENCES Atracao(ID)
);

CREATE TABLE Evento_Equipamento (
    EventoID INT NOT NULL,
    EquipamentoID INT NOT NULL,
    PRIMARY KEY (EventoID, EquipamentoID),
    FOREIGN KEY (EventoID) REFERENCES Evento(ID),
    FOREIGN KEY (EquipamentoID) REFERENCES Equipamento(ID)
);
