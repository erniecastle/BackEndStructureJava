package com.mef.erp.util;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.hibernate.HibernateException;
import org.hibernate.JDBCException;
import org.hibernate.LazyInitializationException;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.NonUniqueResultException;
import org.hibernate.ObjectDeletedException;
import org.hibernate.PropertyValueException;
import org.hibernate.QueryException;
import org.hibernate.QueryParameterException;
import org.hibernate.SessionException;
import org.hibernate.StaleStateException;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.TransactionException;
import org.hibernate.exception.DataException;
import org.hibernate.exception.JDBCConnectionException;
import org.hibernate.exception.SQLGrammarException;
import org.hibernate.transaction.JDBCTransaction;
import org.xml.sax.SAXException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author User
 */
public class ControlErroresHibernate {

    private static final int constraintViolationException = 1;
    private static final int transactionException = 2;
    private static final int propertyValueException = 3;
    private static final int jdbcException = 4;
    private static final int lazyInitializationException = 5;
    private static final int nonUniqueObjectException = 6;
    private static final int nonUniqueResultException = 7;
    private static final int objectDeletedException = 8;
    private static final int queryException = 9;
    private static final int queryParameterException = 10;
    private static final int sessionException = 11;
    private static final int staleStateException = 12;
    private static final int dataException = 13;
    private static final int jdbcConnectionException = 14;
    private static final int sqlGrammarException = 15;
    private static final int exceptionXML = 16;
    private static final int hibernateException = 17;
    private static final int jdbcTransaction = 18;
    private static final int exception = 19;

    public static int buscaNoErrorPorExcepcion(Class classExeption) {
        System.out.println("class "+classExeption.getSimpleName());
        if (classExeption.equals(ConstraintViolationException.class)) {
            return constraintViolationException; 
        } else if (classExeption.equals(TransactionException.class)) {
            return transactionException;  
        } else if (classExeption.equals(PropertyValueException.class)) {
            return propertyValueException;
        } else if (classExeption.equals(JDBCException.class)) {
            return jdbcException;
        } else if (classExeption.equals(LazyInitializationException.class)) {
            return lazyInitializationException;
        } else if (classExeption.equals(NonUniqueObjectException.class)) {
            return nonUniqueObjectException;
        } else if (classExeption.equals(NonUniqueResultException.class)) {
            return nonUniqueResultException;
        } else if (classExeption.equals(ObjectDeletedException.class)) {
            return objectDeletedException;
        } else if (classExeption.equals(QueryException.class)) {
            return queryException;
        } else if (classExeption.equals(QueryParameterException.class)) {
            return queryParameterException;
        } else if (classExeption.equals(SessionException.class)) {
            return sessionException; 
        } else if (classExeption.equals(StaleStateException.class)) {
            return staleStateException; 
        } else if (classExeption.equals(DataException.class)) {
            return dataException; 
        } else if (classExeption.equals(JDBCConnectionException.class)) {
            return jdbcConnectionException;
        } else if (classExeption.equals(SQLGrammarException.class)) {
            return sqlGrammarException;
        } else if (classExeption.equals(Exception.class)) {
            return exception;
        } else if (classExeption.equals(SAXException.class) | classExeption.equals(IOException.class) | classExeption.equals(ParserConfigurationException.class)) {
            return exceptionXML;
        } else if (classExeption.equals(HibernateException.class)) {
            return hibernateException;
        } else if (classExeption.equals(JDBCTransaction.class)) {
            return jdbcTransaction;
        }
        return exception;
    }
}
