package com.realworld.wages.serviceIF;

import com.realworld.wages.entities.dailyExpensive;

public interface IdailyExpensiveService {

    /**
     *
     * @param userId
     * @return
     */

    dailyExpensive createExpensive(long userId, dailyExpensive expensive);
}
