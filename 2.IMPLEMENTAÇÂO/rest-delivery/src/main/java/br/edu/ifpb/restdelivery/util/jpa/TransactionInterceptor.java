package br.edu.ifpb.restdelivery.util.jpa;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 * Implementação do interceptador para transações entre o banco de dados e aplicação
 * 
 * @author rafaelfeitosa - <a href="https://github.com/JoseRafael97 ></a>
 *
 */
@Interceptor
@Transactional
public class TransactionInterceptor implements Serializable {

	private static final long serialVersionUID = 1L;

	private @Inject EntityManager em;

	/**
	 * Método que controla as transações.
	 * @param ctx
	 * @return
	 * @throws Exception
	 */
	@AroundInvoke
	public Object intercept(InvocationContext ctx) throws Exception {

		boolean criador = false;
		EntityTransaction transaction = em.getTransaction();

		try {
			if (!transaction.isActive()) {
				transaction.begin();
				transaction.rollback();

				transaction.begin();
				
				criador = true;
			}
			return ctx.proceed();
		} catch (Exception pe) {
			
			if(transaction != null && criador){
				transaction.rollback();
			}
			
			throw pe;
		}finally {
			if(transaction != null && transaction.isActive() && criador){
				transaction.commit();
			}
		}

	}

}