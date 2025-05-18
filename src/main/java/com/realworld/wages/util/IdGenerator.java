/*
 * Created by Thoughtclan Technologies
 * @author ranga JASON 2022
 */
package com.realworld.wages.util;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.annotations.IdGeneratorType;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;


/**
 * The Class IdGenerator.
 */
//@IdGeneratorType(IdGenerator.class)

public class IdGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object obj)
            throws HibernateException {

        String idProperty = session.getEntityPersister(obj.getClass().getName(), obj)
                .getIdentifierPropertyName();

        String query = String.format("select max(e.%s) from %s e", idProperty, obj.getClass().getSimpleName());

        Long maxId = 0L;
        try {
            Long result = session.createQuery(query, Long.class).getSingleResult();
            if (result != null) {
                maxId = result;
            }
        } catch (Exception e) {
            // Log or handle exception appropriately
            e.printStackTrace();
        }

        return maxId + 1;
    }
}