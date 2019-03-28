package gov.ca.cwds.cares.persistence.entity;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;
import org.hibernate.usertype.UserType;

/**
 * CWDS J Team
 */
public class TrimmingStringType implements UserType, Serializable {
  private static final long serialVersionUID = 2808892159969071311L;

  private static final int[] SQL_TYPES = new int[] {Types.CHAR};

  private Type standardBasicType = StandardBasicTypes.STRING;

  @Override
  public int[] sqlTypes() {
    return SQL_TYPES;
  }

  @Override
  public Class returnedClass() {
    return String.class;
  }

  @Override
  public boolean equals(Object x, Object y) throws HibernateException {
    if (x == null) {
      return y == null;
    }

    return x.equals(y);
  }

  @Override
  public int hashCode(Object object) throws HibernateException {
    return object.hashCode();
  }

  @Override
  public Object nullSafeGet(ResultSet resultSet, String[] names, SharedSessionContractImplementor session, Object owner) throws HibernateException, SQLException {
    Object value = standardBasicType.nullSafeGet(resultSet, names, session, owner);
    if (value == null) {
      return null;
    }
    return value.toString().trim();
  }

  @Override
  public void nullSafeSet(PreparedStatement preparedStatement, Object value, int index, SharedSessionContractImplementor session) throws HibernateException, SQLException {
    standardBasicType.nullSafeSet(preparedStatement, value, index, session);
  }

  @Override
  public Object deepCopy(Object value) throws HibernateException {
    return value;
  }

  @Override
  public boolean isMutable() {
    return false;
  }

  @Override
  public Serializable disassemble(Object value) throws HibernateException {
    return (Serializable) value;
  }

  @Override
  public Object assemble(Serializable cached, Object value) throws HibernateException {
    return cached;
  }

  @Override
  public Object replace(Object original, Object target, Object owner) throws HibernateException {
    return original;
  }

  void setStandardBasicType(Type standardBasicType) {
    this.standardBasicType = standardBasicType;
  }
}