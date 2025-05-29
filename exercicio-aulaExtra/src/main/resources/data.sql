--inserir dados na tabela paciente
insert into paciente (nome) values('João');
insert into paciente (nome) values('José');
insert into paciente (nome) values('Maria');
--inserir dados na tabela medico
insert into medico (nome) values('Maria de Fátima');
insert into medico (nome) values('Joana');
--inserir dados na tabela exame
insert into exame (tipo, valor) values('Sangue', 50);
insert into exame (tipo, valor) values('Raio X', 100);
insert into exame (tipo, valor) values('Urina',25);
insert into exame (tipo, valor) values('Ultrasom',300);
--inserir dados na tabela consulta
insert into consulta (data_consulta, id_paciente, id_medico) values('2021-10-10',1,1);
insert into consulta (data_consulta, id_paciente, id_medico) values('2021-10-11',2,1);
--inserir dados na tabela procedimento
insert into procedimentos (quantidade,valor_procedimento,id_consulta,id_exame) values(1,50,1,1);
insert into procedimentos (quantidade,valor_procedimento,id_consulta,id_exame) values(1,100,1,2);
insert into procedimentos (quantidade,valor_procedimento,id_consulta,id_exame) values(2,250,1,4);
insert into procedimentos (quantidade,valor_procedimento,id_consulta,id_exame) values(2,50,2,1);
insert into procedimentos (quantidade,valor_procedimento,id_consulta,id_exame) values(1,100,2,2);
insert into procedimentos (quantidade,valor_procedimento,id_consulta,id_exame) values(1,200,2,3);