package aplicacao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dominio.Pessoa;

public class Programa {

	public static void main(String[] args) {

		Pessoa p1 = new Pessoa(null, "Charles", "chl@email.com");
		Pessoa p2 = new Pessoa(null, "Carlitos", "ca@email.com");
		Pessoa p3 = new Pessoa(null, "Chaplin", "ch@email.com");

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa");
		EntityManager em = emf.createEntityManager();

		//criaInstanciasNoDB(emf, em, p1, p2, p3);
		
		buscaPessoa(emf, em);
		
		em.close();
		emf.close();

		System.out.println("Pronto!");

	}

	private static void criaInstanciasNoDB(EntityManagerFactory emf, EntityManager em, Pessoa... pessoas) {
		em.getTransaction().begin();

		for (int i = 0; i < pessoas.length; i++) {
			em.persist(pessoas[i]);
			
		}
		
		em.getTransaction().commit();
	}
	
	private static void buscaPessoa(EntityManagerFactory emf, EntityManager em) {
		Pessoa p = em.find(Pessoa.class, 2);
		
		System.out.println(p);
	}
}
