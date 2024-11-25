CREATE TABLE Historico (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    TipoOperacao VARCHAR(10) NOT NULL, -- Tipo da operação (INSERT, UPDATE, DELETE)
    TabelaAfetada VARCHAR(50) NOT NULL, -- Nome da tabela afetada
    DadosAntigos TEXT, -- Dados antes da atualização (aplica-se apenas a UPDATE)
    DadosNovos TEXT, -- Dados após a operação (aplica-se para todos os tipos)
    DataHora TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- Data e hora da operação
);
