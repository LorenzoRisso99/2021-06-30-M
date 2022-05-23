package it.polito.tdp.genes.db;

import java.util.List;

import it.polito.tdp.genes.model.Genes;

public class TestDao {

	public static void main(String[] args) {

		GenesDao dao = new GenesDao();
		List<Integer> list = dao.getVertici();

		for (Integer i : list) {
			System.out.println(i);
		}
		
	}

}
