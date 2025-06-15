package com.realworld.wages.serviceIF;

import com.realworld.wages.dto.storeEarningDto;
import com.realworld.wages.entities.storeEarning;
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

    /**
     *
     * @param userId
     * @return
     */
    List<storeEarning> fingByUserId(Long userId);


    /**
     *
     * @param earning
     * @return
     */

    public storeEarning updateStoreEarning(Long id , storeEarningDto earning);



}


