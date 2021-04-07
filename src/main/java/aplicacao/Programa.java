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
		
		p1 = buscaPessoa(emf, em, 1);
		p2 = buscaPessoa(emf, em, 2);
		p3 = buscaPessoa(emf, em, 3);
		
		if (removePessoa(emf, em, p1)) {
			System.out.printf("Pessoa removida : %s", p1);
		}
		
		em.close();
		emf.close();

		System.out.println("Pronto!");

	}
	
	private static boolean removePessoa(EntityManagerFactory emf, EntityManager em, Pessoa p) {
		
		boolean removed = false;
		try {
			// Entidade no contexto de transação.
			em.getTransaction().begin();
			em.remove(p);
			em.getTransaction().commit();
			
			removed = true;
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
			
		} 

		return removed;		
	}
	
	
	private static Pessoa buscaPessoa(EntityManagerFactory emf, EntityManager em, int id) {
		Pessoa p = em.find(Pessoa.class, id);
		
		return p;
	}

	private static void criaInstanciasNoDB(EntityManagerFactory emf, EntityManager em, Pessoa... pessoas) {
		em.getTransaction().begin();

		for (int i = 0; i < pessoas.length; i++) {
			em.persist(pessoas[i]);
			
		}
		
		em.getTransaction().commit();
	}
	
	
}
