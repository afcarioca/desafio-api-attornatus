
CREATE TABLE TB_ENDERECOS(
ENDERECO_ID BIGINT NOT NULL  AUTO_INCREMENT,
ENDERECO_EXTERNAL_ID UUID UNIQUE NOT NULL,
LOGRADOURO VARCHAR(255) NOT NULL,
CEP VARCHAR(9) NOT NULL,
NUMERO VARCHAR(6) NOT NULL,
CIDADE VARCHAR(255) NOT NULL,
DATA_DA_CRIACAO TIMESTAMP,
DATA_DA_ULTIMA_ATUALIZACAO TIMESTAMP,
PRIMARY KEY(ENDERECO_ID)

);