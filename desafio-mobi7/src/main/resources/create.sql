CREATE TABLE ponto_de_interesse
(
    id        UUID    NOT NULL,
    nome      VARCHAR(255) NULL,
    raio      INT NOT NULL,
    latitude  DECIMAL(8,6)   NOT NULL,
    longitude DECIMAL(9,6)   NOT NULL,
    CONSTRAINT ponto_de_interesse_pkey PRIMARY KEY (id)
);

CREATE TABLE posicoes_veiculo
(
    id           UUID      NOT NULL,
    placa        INT   NOT NULL,
    data_posicao TIMESTAMP NOT NULL,
    velocidade   INT   NOT NULL,
    longitude    DECIMAL(8,6) NULL,
    latitude     DECIMAL(9,6) NULL,
    ignicao      boolean,
    CONSTRAINT posicoes_veiculo_pkey PRIMARY KEY (id)
)