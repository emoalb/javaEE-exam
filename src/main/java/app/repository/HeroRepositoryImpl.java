package app.repository;

import app.domain.entities.Hero;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class HeroRepositoryImpl implements HeroRepository {
    private final EntityManager entityManager;
    private final TransactionUtil transactionUtil;


    @Inject
    public HeroRepositoryImpl(EntityManager entityManager, TransactionUtil transactionUtil) {
        this.entityManager = entityManager;
        this.transactionUtil = transactionUtil;
    }

    @Override
    public void save(Hero hero) {
        this.transactionUtil.createTransaction(hero);
    }

    @Override
    public List<Hero> findAllHeroes() {
        return this.entityManager.createQuery("SELECT h from Hero h ORDER BY h.level DESC", Hero.class).getResultList();
    }

    @Override
    public Hero findById(String id) {
        List<Hero> heroes = this.entityManager.createQuery("SELECT h from Hero h WHERE h.id = :id", Hero.class).setParameter("id", id).getResultList();
        if (heroes.isEmpty()) {
            return null;
        }
        return heroes.get(0);
    }

    @Override
    public void deleteById(String id) {
        Hero hero = findById(id);
        this.entityManager.getTransaction().begin();
        this.entityManager.remove(hero);
        this.entityManager.getTransaction().commit();
        System.out.println();
    }
}
