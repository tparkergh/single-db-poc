package gov.ca.cwds.data.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gov.ca.cwds.data.model.ClientEntity;


@Repository
@Transactional
public class ClientDao {
  @Autowired
  private SessionFactory sessionFactory;

  private Session getSession() {
    return sessionFactory.getCurrentSession();
  }

  public ClientEntity findById(String id) {
    ClientEntity client = getSession().get(ClientEntity.class, id);
    return client;
  }

  @SuppressWarnings("unchecked")
  public List<ClientEntity> findAllClients() {
    List<ClientEntity> clients = getSession().createQuery("from ClientCounty").list();
    return clients;
  }



}
