package com.scaler.demo.project.controller;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.SqlTypes;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

/**
 * Custom Hibernate UserType for handling encryption and decryption of sensitive data.
 * This generic implementation can handle various data types.
 *
 * @param <T> The type of the entity attribute.
 */
public class EncryptedType<T> implements UserType<T> {

    public Class<T> typeClass;

    private static final Map<Class<?>, Function<Object, String>> typeToStringMap = new HashMap<>();
    private static final Map<Class<?>, Function<String, Object>> stringToTypeMap = new HashMap<>();

    static {
        typeToStringMap.put(String.class, String.class::cast);
        typeToStringMap.put(Integer.class, value -> Integer.toString((Integer) value));
        typeToStringMap.put(Long.class, value -> Long.toString((Long) value));
        typeToStringMap.put(Double.class, value -> Double.toString((Double) value));
        typeToStringMap.put(LocalDate.class, Object::toString);

        stringToTypeMap.put(String.class, value -> value);
        stringToTypeMap.put(Integer.class, Integer::valueOf);
        stringToTypeMap.put(Long.class, Long::valueOf);
        stringToTypeMap.put(Double.class, Double::valueOf);
        stringToTypeMap.put(LocalDate.class, LocalDate::parse);
    }
    /**
     * Constructor to initialize the type class.
     *
     * @param typeClass The class type of the entity attribute.
     */
    public EncryptedType(Class<T> typeClass) {
        this.typeClass = typeClass;
    }

    @Override
    public int getSqlType() {
        return SqlTypes.VARCHAR;
    }

    /**
     * Returns the class type of the entity attribute.
     *
     * @return The class type.
     */
    @Override
    public Class<T> returnedClass() {
        return typeClass;
    }

    /**
     * Checks if two objects are equal.
     *
     * @param x The first object.
     * @param y The second object.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object x, Object y) {
        return Objects.equals(x, y);
    }

    /**
     * Returns the hash code of the object.
     *
     * @param x The object.
     * @return The hash code.
     */
    @Override
    public int hashCode(Object x) {
        return x != null ? x.hashCode() : 0;
    }

    /**
     * @param resultSet
     * @param i
     * @param sharedSessionContractImplementor
     * @param o
     * @return
     * @throws SQLException
     */
    @Override
    public T nullSafeGet(ResultSet resultSet, int i, SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws SQLException {

//      throws SQLException {
        String value = resultSet.getString(i);
       // if (!StringUtils.isBlank(value)) {
            String decryptedValue = PIIEncryptionUtils.decrypt(value);
            return convertStringToType(decryptedValue);
       // }
       // return (T) value;
    }

    @Override
    public T deepCopy(T t) {
        return t;
    }

    /**
     * @param resultSet
     * @param i
     * @param sharedSessionContractImplementor
     * @param o
     * @return
     * @throws SQLException
     */

    /**
     * Retrieves the value from the ResultSet and decrypts it.
     *
     * @param rs      The ResultSet.
     * @param names   The column names.
     * @param session The session.
     * @param owner   The entity.
     * @return The decrypted value.
     * @throws SQLException If there is an error retrieving the value.
    //   */
//  @Override
//  public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner)
//      throws SQLException {
//      String value = rs.getString(names[0]);
//      if (!StringUtils.isBlank(value)) {
//        String decryptedValue = PIIEncryptionUtils.decrypt(value);
//        return convertStringToType(decryptedValue);
//      }
//      return value;
//  }

    /**
     * Encrypts the value and sets it in the PreparedStatement.
     *
     * @param st      The PreparedStatement.
     * @param value   The value to encrypt.
     * @param index   The column index.
     * @param session The session.
     * @throws SQLException If there is an error setting the value.
     */
    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session)
            throws SQLException {
        if (value != null) {
            String encryptedValue = PIIEncryptionUtils.encrypt(convertTypeToString(value));
            st.setString(index, encryptedValue);
        } else {
            st.setNull(index, Types.VARCHAR);
        }
    }

    /**
     * Creates a deep copy of the value.
     *
     * @param value The value to copy.
     * @return The copied value.
     */
    /**
     * Checks if the value is mutable.
     *
     * @return False as the value is immutable.
     */
    @Override
    public boolean isMutable() {
        return false;
    }


    /**
     * Disassembles the value for caching.
     *
     * @param value The value to disassemble.
     * @return The disassembled value.
     */
    @Override
    public Serializable disassemble(Object value) {
        return (Serializable) value;
    }

    /**
     * Assembles the value from the cached state.
     *
     * @param cached The cached value.
     * @param owner  The entity.
     * @return The assembled value.
     */
    @Override
    public T assemble(Serializable cached, Object owner) {
        return (T) cached;
    }

    @Override
    public T replace(T detached, T managed, Object owner) {
        return UserType.super.replace(detached, managed, owner);
    }

    /**
     * Replaces the original value with the target value.
     *
     * @param original The original value.
     * @param target   The target value.
     * @param owner    The entity.
     * @return The original value.
     */

    /**
     * Converts the value to a string for encryption.
     *
     * @param value The value to convert.
     * @return The string representation of the value.
     */
    private String convertTypeToString(Object value) {
        Function<Object, String> converter = typeToStringMap.get(typeClass);
      //  if (converter != null) {
            return converter.apply(value);
       // } else {
           // throw new PIIDataException("Unsupported type: " + typeClass.getName());
       // }
    }

    /**
     * Converts the string to the specified type after decryption.
     *
     * @param value The string value to convert.
     * @return The converted value.
     */
    private T convertStringToType(String value) {
        Function<String, Object> converter = stringToTypeMap.get(typeClass);
      //  if (converter != null) {
            return typeClass.cast(converter.apply(value));
       // } else {
         //   throw new PIIDataException("Unsupported type: " + typeClass.getName());
       // }
    }
}