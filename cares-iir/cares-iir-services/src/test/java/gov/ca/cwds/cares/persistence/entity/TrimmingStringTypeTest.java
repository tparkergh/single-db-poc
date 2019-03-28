package gov.ca.cwds.cares.persistence.entity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.Type;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * CWDS J Team
 */
public class TrimmingStringTypeTest {

  private TrimmingStringType trimmingStringType;

  @Before
  public void setUp() {
    trimmingStringType = new TrimmingStringType();
  }

  @Test
  public void shouldReturnCharTypeWhenSqlTypes() throws Exception {
    assertEquals(1, trimmingStringType.sqlTypes().length);
    assertEquals(Types.CHAR, trimmingStringType.sqlTypes()[0]);
  }

  @Test
  public void shouldReturnStringWhenReturnedClass() throws Exception {
    assertEquals(String.class, trimmingStringType.returnedClass());
  }

  @Test
  public void shouldReturnTrueWhenEqualsWithEqualParams() throws Exception {
    assertTrue(trimmingStringType.equals("a", "a"));
  }

  @Test
  public void shouldReturnTrueWhenEqualsWithNullParams() throws Exception {
    assertTrue(trimmingStringType.equals(null, null));
  }

  @Test
  public void shouldReturnFalseWhenEqualsWithDifferentParams() throws Exception {
    assertFalse(trimmingStringType.equals("a", null));
  }

  @Test
  public void shouldReturnParamHashcodeWhenHashCode() throws Exception {
    String obj = "a";
    assertEquals(obj.hashCode(), trimmingStringType.hashCode(obj));
  }

  @Test
  public void shouldReturnTrimmedStringWhenNullSafeGetHasLoadedValue() throws Exception {
    assertNullSafeGet("a", " a ");
  }

  @Test
  public void shouldBeNullSafeWhenNullSafeGetLoadedValueIsNull() throws Exception {
    assertNullSafeGet(null, null);
  }

  private void assertNullSafeGet(String expected, String loaded) throws SQLException {
    Type standardBasicType = mock(Type.class);
    trimmingStringType.setStandardBasicType(standardBasicType);

    ResultSet resultSet = mock(ResultSet.class);
    String[] names = new String[]{};
    SharedSessionContractImplementor session = mock(SharedSessionContractImplementor.class);
    Object owner = new Object();
    when(standardBasicType.nullSafeGet(resultSet, names, session, owner)).thenReturn(loaded);

    assertEquals(expected, trimmingStringType.nullSafeGet(resultSet, names, session, owner));
  }

  @Test
  public void shouldPropagateToBasicTypeWhenNullSafeSet() throws Exception {
    Type standardBasicType = mock(Type.class);
    trimmingStringType.setStandardBasicType(standardBasicType);

    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    Object value = new Object();
    int index = -1;
    SharedSessionContractImplementor session = mock(SharedSessionContractImplementor.class);

    trimmingStringType.nullSafeSet(preparedStatement, value, index, session);
    verify(standardBasicType).nullSafeSet(preparedStatement, value, index, session);
  }

  @Test
  public void returnValueWhenDeepCopy() throws Exception {
    assertEquals(" a ", trimmingStringType.deepCopy(" a "));
  }

  @Test
  public void shouldBeImmutable() throws Exception {
    assertFalse(trimmingStringType.isMutable());
  }

  @Test
  public void shouldReturnValueWhenDisassemble() throws Exception {
    assertEquals(" a ", trimmingStringType.disassemble(" a "));

  }

  @Test
  public void shouldReturnCachedWhenAssemble() throws Exception {
    assertEquals(" cached ", trimmingStringType.assemble(" cached ", " value "));
  }

  @Test
  public void shouldReturnOriginalWhenReplace() throws Exception {
    assertEquals(" original ", trimmingStringType.replace(" original ", " target ", " owner "));
  }
}