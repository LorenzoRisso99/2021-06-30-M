package it.polito.tdp.genes.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.genes.model.Adiacenza;
import it.polito.tdp.genes.model.Genes;
import it.polito.tdp.genes.model.Interactions;


public class GenesDao {
	
	public List<Genes> getAllGenes(){
		String sql = "SELECT DISTINCT GeneID, Essential, Chromosome FROM Genes";
		List<Genes> result = new ArrayList<Genes>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				Genes genes = new Genes(res.getString("GeneID"), 
						res.getString("Essential"), 
						res.getInt("Chromosome"));
				result.add(genes);
			}
			res.close();
			st.close();
			conn.close();
			return result;
			
		} catch (SQLException e) {
			throw new RuntimeException("Database error", e) ;
		}
	}
	
	public List<Integer> getVertici() {
		
		String sql = "SELECT DISTINCT chromosome "
				+ "FROM genes "
				+ "WHERE Chromosome > 0";
		
		List<Integer> vertici = new ArrayList<Integer>();
		

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				vertici.add(res.getInt("chromosome"));
			}
			res.close();
			st.close();
			conn.close();
			return vertici;
			
		} catch (SQLException e) {
			throw new RuntimeException("Database error", e) ;
		}
		
	}
	
	public List<Adiacenza> getArchi() {
		
		String sql = "SELECT DISTINCT g1.Chromosome AS c1, g2.Chromosome AS c2, SUM(DISTINCT(Expression_Corr)) AS somma "
				+ "FROM genes g1, genes g2, interactions i "
				+ "WHERE  g1.GeneID = i.GeneID1 AND g2.GeneID = i.GeneID2 AND g1.Chromosome <> g2.Chromosome AND g1.Chromosome <> 0 AND g2.Chromosome <> 0 "
				+ "GROUP BY g1.Chromosome, g2.Chromosome";
		
		List<Adiacenza> archi = new ArrayList<Adiacenza>();
		

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				archi.add(new Adiacenza(res.getInt("c1"), res.getInt("c2"), res.getDouble("somma")));
			}
			res.close();
			st.close();
			conn.close();
			return archi;
			
		} catch (SQLException e) {
			throw new RuntimeException("Database error", e) ;
		}
		
	}

	
}
