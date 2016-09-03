package br.edu.ifpb.restdelivery.util.jpa;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Classe que fornece a instância e destroe o EntityManager
 * 
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 *
 */
@ApplicationScoped
public class EntityManagerProducer {

	private EntityManagerFactory factory = null;

	public EntityManagerProducer() {
		this.factory = Persistence.createEntityManagerFactory("post");
	}

	@Produces
	@RequestScoped
	public EntityManager create() {
		return this.factory.createEntityManager();
	}

	public void close(@Disposes EntityManager manager) {
		manager.close();
	}

}