package br.edu.ifpb.restdelivery.dao;


import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * Super tipo DAO que dar suporte com a injeção do ENTYTYMANAGER para os demais.
 * 
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 *
 */
public abstract class DAO{

	@Inject
	protected EntityManager em;

	
}