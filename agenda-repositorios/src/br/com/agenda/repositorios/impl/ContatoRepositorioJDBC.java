package br.com.agenda.repositorios.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.agenda.entendidades.Contato;
import br.com.agenda.fabricas.FabricaConexaoJDBC;
import br.com.agenda.repositorios.interfaces.AgendaRepositorio;

public class ContatoRepositorioJDBC implements AgendaRepositorio<Contato> {

	@Override
	public List<Contato> selecionar() throws SQLException, IOException {
		List<Contato> contatos = new ArrayList<Contato>();
		// passando a string de conexão com o banco de dados
		try(Connection conexao = FabricaConexaoJDBC.criarConexao()) { //criando a conexão dentro do try para ela automatica ser fechada no fim do escopo do código, try-with-resources
			// criando a o objeto Statement que é responsavel por executar instruções SQL
			Statement comando = conexao.createStatement(); 
			ResultSet rs = comando.executeQuery("SELECT * FROM contatos;");// criando o resultSet para obter o resultado														// da execução do comando sql
			while (rs.next()) { // next para percorrer enquanto existir dados no retorno do comando
				Contato contato = new Contato();
				contato.setId(rs.getInt("id")); // lendo linha por linha e preenchendo os dados do contato
				contato.setIdade(rs.getInt("idade"));
				contato.setNome(rs.getString("nome"));
				contato.setTelefone(rs.getString("telefone"));
				contatos.add(contato);
			}
		} 
		return contatos;
	}

	@Override
	public void inserir(Contato entidade) throws IOException, SQLException {
		try (Connection conexao = FabricaConexaoJDBC.criarConexao()){
			PreparedStatement comando = conexao.prepareStatement("INSERT INTO Contatos (nome, idade, telefone) VALUES (?, ?, ?)");
			comando.setString(1, entidade.getNome());
			comando.setInt(2, entidade.getIdade());
			comando.setString(3, entidade.getTelefone());
			comando.execute();
		}
	}

	@Override
	public void atualizar(Contato entidade, int id) throws IOException, SQLException {
		try (Connection conexao = FabricaConexaoJDBC.criarConexao()){		
		PreparedStatement comando = conexao.prepareStatement("UPDATE contatos SET nome = ?, idade = ?, telefone = ? WHERE id = ?");
		comando.setString(1, entidade.getNome());
		comando.setInt(2, entidade.getIdade());
		comando.setString(3, entidade.getTelefone());
		comando.setInt(4, id);
		comando.execute();
		}
	}

	@Override
	public void excluir(Contato entidade) throws IOException, SQLException {
		try (Connection conexao = FabricaConexaoJDBC.criarConexao()){
			PreparedStatement comando = conexao.prepareStatement("DELETE FROM contatos WHERE id = ?");
			comando.setInt(1, entidade.getId());
			comando.execute();
		}
	}

}
