
---- -----------------------------------------------------
---- Clientes
---- -----------------------------------------------------
INSERT INTO tb_cliente(nome_completo, email, cpf ) VALUES ('Katy Perry', 'perry@gmail.com', '150.746.840-72');
INSERT INTO tb_cliente(nome_completo, email, cpf ) VALUES ('Lana Del Rey', 'delrey@gmail.com', '246.187.180-99');
INSERT INTO tb_cliente(nome_completo, email, cpf ) VALUES ('Brad Pitt', 'pitt@gmail.com', '518.083.230-68');
INSERT INTO tb_cliente(nome_completo, email, cpf ) VALUES ('Gigi Hadid', 'hadid@gmail.com', '512.919.430-64');
INSERT INTO tb_cliente(nome_completo, email, cpf ) VALUES ('Nina Dobrev', 'dobrev@gmail.com', '095.048.080-00');
INSERT INTO tb_cliente(nome_completo, email, cpf ) VALUES ('Adam Richard', 'richard@gmail.com', '975.842.790-31');

---- -----------------------------------------------------
---- Funcionários
---- -----------------------------------------------------
INSERT INTO tb_funcionario(nome_completo, cargo, cep) VALUES ('Paulo Santana Andrade', 'Bibliotecario', '29153-643');
INSERT INTO tb_funcionario(nome_completo, cargo, cep) VALUES ('Ana de Oliveira Duarte', 'Servicos Gerais', '29152-874');
INSERT INTO tb_funcionario(nome_completo, cargo, cep) VALUES ('Sueli Vieira Santos', 'Secretária', '29141-52');
INSERT INTO tb_funcionario(nome_completo, cargo, cep) VALUES ('Paulo Santana Silva', 'Servicos Gerais', '34001-090');
INSERT INTO tb_funcionario(nome_completo, cargo, cep) VALUES ('João Francisco Costa', 'Atendente', '29159-060');
INSERT INTO tb_funcionario(nome_completo, cargo, cep) VALUES ('Miguel Ivo Silveira', 'Secretário', '29157-521');
INSERT INTO tb_funcionario(nome_completo, cargo, cep) VALUES ('Fabrício Dutra Menezes', 'Arquivologista', '29157-521');
INSERT INTO tb_funcionario(nome_completo, cargo, cep) VALUES ('Bruna Fernanda Rodrigues', 'Bibliotecário', '29157-521');

---- -----------------------------------------------------
---- Endereços
---- -----------------------------------------------------

INSERT INTO tb_endereco(cep, logradouro, complemento, bairro, localidade, uf, cliente_id) VALUES ('34001-090','Rua Levy Firmino Alves','casa', 'Parque Santo Antônio', 'Nova Lima','MG', 1)

