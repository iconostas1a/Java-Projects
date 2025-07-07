package lab2.com.data.dao;

import lab2.com.data.entities.Kitty;
import lab2.com.data.entities.Owner;
import lab2.com.data.utils.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class OwnerDaoImp implements OwnerDao {
    public void addOwner(Owner owner) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.persist(owner);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Не удалось добавить владельца", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public Owner getOwnerById(Long id) {
        Session session = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            return session.get(Owner.class, id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch owner with id = " + id, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void updateOwner(Owner owner) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.merge(owner);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Failed to update kitty", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void addKittyToOwner(Long kittyId, Long ownerId) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Owner owner = session.get(Owner.class, ownerId);
            Kitty kitty = session.get(Kitty.class, kittyId);
            owner.getKitties().add(kitty);
            session.merge(owner);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Не получилось добавить кота", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void deleteOwnerById(Long id) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            Owner owner = session.get(Owner.class, id);
            if (owner != null) {
                session.delete(owner);
            } else {
                throw new RuntimeException("Владелец с  " + id + " не найден");
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Не получилось найти владельца " + id, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
