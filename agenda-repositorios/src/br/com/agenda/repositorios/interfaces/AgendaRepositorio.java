package br.com.agenda.repositorios.interfaces;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
//T é utilizado como parametro generico e a interface possa manipular uma lista com qualquer tipo de dado
public interface AgendaRepositorio<T> {
	//o T é utilizado como elemento generico para manipulação de qualquer tipo de dado
	
	List<T> selecionar() throws SQLException, IOException;
	void inserir(T entidade) throws IOException, SQLException;
	void atualizar(T entidade, int id) throws IOException, SQLException;
	void excluir(T entidade) throws IOException, SQLException;
	
}
