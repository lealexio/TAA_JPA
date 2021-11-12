package dao;

import entities.Individual;

/**
 * Dao for Individual
 */
public class IndividualDao extends AbstractJpaDao<Long, Individual> {

    public IndividualDao() {
        super(Individual.class);
    }

    /**
     * Find Individual by Tel
     * @param tel of Individual
     * @return Indivual associated to tel
     */
    private Individual findByTel(String tel) {
        return this.entityManager.createNamedQuery("findByTel", Individual.class).setParameter("tel", tel).getSingleResult();
    }

}
