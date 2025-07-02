package com.realworld.wages.serviceIF;

import com.realworld.wages.dto.dailyExpensiveDto;
import com.realworld.wages.entities.dailyExpensive;

import java.util.List;

public interface IdailyExpensiveService {

    /**
     *
     * @param userId
     * @return
     */

    dailyExpensive createExpensive(long userId, dailyExpensive expensive);


    /**
     *
     * @param userId
     * @param expenseId
     * @return
     */
    List<dailyExpensive> getExpensiveById(Long userId,  Long expenseId);


    /**
     *
     * @param expenseId
     * @param userId
     * @param expenseDto
     * @return
     */
    dailyExpensive put(Long expenseId, Long userId, dailyExpensiveDto expenseDto);
}
