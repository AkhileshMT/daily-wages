package com.realworld.wages.serviceIF;

import com.realworld.wages.entities.storeEarning;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface IstoreEarningService {

    /**
     * Create SoteEarning
     *
     * @param id
     * @param earning
     * @return
     */


    public storeEarning create(Long id, storeEarning earning);

    /**
     *
     * Find All Earnings
     *
     * @return
     *
     */
    List<storeEarning> findAllEarnings();


    /**
     *
     * @param userId
     * @param storeEarningId
     * @return
     */

    List<storeEarning> findByIDEarningId(Long userId, Long storeEarningId);



}


