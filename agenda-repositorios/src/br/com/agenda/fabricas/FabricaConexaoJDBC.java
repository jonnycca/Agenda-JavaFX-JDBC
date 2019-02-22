package br.com.agenda.fabricas;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class FabricaConexaoJDBC {

	public static Connection criarConexao() throws IOException, SQLException {
		// criando um objetivo InputStream para manipular o arquivo de propriedades e
		// usando o getClass para pegar o momento em que a class é instanciada
		InputStream is = FabricaConexaoJDBC.class.getClassLoader().getResourceAsStream("application.properties");
		if (is == null) {// se o arquivo não foi encontrado
			throw new FileNotFoundException("O arquivo de configuração de banco de dados não foi encontrado.");
		}
		Properties props = new Properties(); // criando um objeto para armazenar as propriedades
		props.load(is); // lendo o arquivo de configuração
		Connection conexao = DriverManager.getConnection(props.getProperty("urlConexao"), props.getProperty("usuarioConexao"), props.getProperty("senhaConexao"));
		return conexao;
	}
}
