package lab2.com.data.dao;

import lab2.com.data.entities.Kitty;
import lab2.com.data.entities.Owner;
import lab2.com.data.utils.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class KittyDaoImp implements KittyDao {
    @Override
    public void addKitty(Kitty kitty) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.persist(kitty);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Не получилось сохранить", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    @Override
    public void updateKitty(Kitty kitty) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.merge(kitty);
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Не получилось обноваить", e);
        }
        finally {
            if (session != null) {
                session.close();
            }
        }

    }
    @Override
    public void deleteKittyById(Long id) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Kitty kitty = session.get(Kitty.class, id);
            if (kitty != null) {
                session.delete(kitty);
            } else {
                throw new RuntimeException("Кот не найден");
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Не получилось удалить кота " + id, e);
        }
    }
    @Override
    public Owner getOwner(Long kittyId) {
        Session session = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            Kitty kitty = session.get(Kitty.class, kittyId);
            Owner owner = kitty.getOwner();
            session.close();
            return owner;
        } catch (Exception e) {
            throw new RuntimeException("Не получилось найти хозяина", e);
        }


    }
    @Override
    public List<Kitty> getAllKitties() {
        Session session = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            List<Kitty> kitties = session.createQuery("FROM Kitty", Kitty.class).list();
            session.close();
            return kitties;
        } catch (Exception e) {
            throw new RuntimeException("Не получилось найти хозяина", e);
        }
    }

    @Override
    public void deleteAllKitties() {
        Session session = null;
        Transaction transaction = null;
        try  {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.createQuery("DELETE FROM Kitty").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Не получилось найти хозяина", e);
        }
    }

    @Override
    public Kitty getKittyById(Long id) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            return session.get(Kitty.class, id);
        }
        catch (Exception e) {
            throw new RuntimeException("Не получилось найти кота " + id, e);
        }
    }

}


