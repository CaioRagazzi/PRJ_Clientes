
import java.util.List;
import java.sql.*;
import java.util.Vector;

public class ConexaoBDA {

	private Connection conx = null;
	private Statement st = null;
	private ResultSet resultado = null;

	public ConexaoBDA() {

		try {
			Class.forName("com.mysql.jdbc.Driver");

			conx = DriverManager.getConnection("jdbc:mysql://localhost/loja_virtual?useSSL=false", "caio", "caiocaio");

			// getLista();

		} catch (Exception e) {
			System.out.println("Erro: " + e);
		}

	}

	public List<String> getLista() {

		List<String> lista = new Vector<String>();

		try {

			st = conx.createStatement();
			resultado = st.executeQuery("select * from Pessoa");

			while (resultado.next()) {

				lista.add(resultado.getInt(1) + " - " + resultado.getString(2) + " - " + resultado.getString(3) + " - " + resultado.getString(4) + " - " + resultado.getString(5) + " - " + resultado.getString(6));
			}

			resultado.beforeFirst();

		} catch (Exception e) {
			System.out.println("Erro: " + e);
		}

		return lista;

	}

	public String[] getPrimeiro() {

		try {
			resultado.first();
			String aux[] = new String[6];
			aux[0] = resultado.getString(1);
			aux[1] = resultado.getString(2);
			aux[2] = resultado.getString(3);
			aux[3] = resultado.getString(4);
			aux[4] = resultado.getString(5);
			aux[5] = resultado.getString(6);
			return aux;
		} catch (Exception e) {
			System.out.println("Erro: " + e);
		}
		return null;

	}

	public String[] getProximo() {

		try {
			resultado.next();
			String aux[] = new String[6];
			aux[0] = resultado.getString(1);
			aux[1] = resultado.getString(2);
			aux[2] = resultado.getString(3);
			aux[3] = resultado.getString(4);
			aux[4] = resultado.getString(5);
			aux[5] = resultado.getString(6);
			return aux;
		} catch (Exception e) {
			System.out.println("Erro: " + e);
		}
		return null;

	}

	public String[] getAnterior() {

		try {
			resultado.previous();
			String aux[] = new String[2];
			aux[0] = resultado.getString(1);
			aux[1] = resultado.getString(2);
			return aux;
		} catch (Exception e) {
			System.out.println("Erro: " + e);
		}
		return null;

	}

	public String[] getUltimo() {

		try {
			resultado.last();
			String aux[] = new String[2];
			aux[0] = resultado.getString(1);
			aux[1] = resultado.getString(2);
			return aux;
		} catch (Exception e) {
			System.out.println("Erro: " + e);
		}
		return null;

	}
	
	public boolean add(String aux[]){
		try {
			String sql = "insert into Pessoa (nome, data, sexo, email, telefone) values" + "('"+ aux[1] +"', '"+ aux[2] +"','"+ aux[3] +"','"+ aux[4] +"','"+ aux[5] +"')";
			st.execute(sql);
			return true;
		} catch (Exception e) {
			System.out.println("Erro: " + e);
			return false;
		}
	}
	
	public boolean remove(String aux[]){
		try {
			String sql = "delete from Pessoa where id = " + "('"+ aux[0] +"')";
			st.execute(sql);
			return true;
		} catch (Exception e) {
			System.out.println("Erro: " + e);
			return false;
		}
	}
	
	public List<String> getEspecifico(String string){
		
		List<String> lista = new Vector<String>();

		try {
			
			
			st = conx.createStatement();
			resultado = st.executeQuery("select * from Pessoa where id = " + "('"+ string +"')");

			while (resultado.next()) {

				lista.add(resultado.getString(1));
				lista.add(resultado.getString(2));
				lista.add(resultado.getString(3));
				lista.add(resultado.getString(4));
				lista.add(resultado.getString(5));
				lista.add(resultado.getString(6));
				
			}
			
			resultado.beforeFirst();

			
			System.out.println(resultado.toString());

		} catch (Exception e) {
			System.out.println("Erro: " + e);
		}

		return lista;
	}
	
	public boolean update(String aux[]){
		try {
			System.out.println(aux[0] + aux[1] + aux[2] + aux[3] + aux[4] + aux[5]);
			String sql = "update Pessoa set nome = ('"+ aux[1] +"'), data = ('"+ aux[2] +"'), sexo = ('"+ aux[3] +"'), email = ('"+ aux[4] +"'), telefone = ('"+ aux[5] +"') where id = " + aux[0];
			st.execute(sql);
			return true;
		} catch (Exception e) {
			System.out.println("Erro: " + e);
			return false;
		}
	}
}
