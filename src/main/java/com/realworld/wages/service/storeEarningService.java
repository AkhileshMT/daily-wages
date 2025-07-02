package com.realworld.wages.service;

import com.realworld.wages.dto.storeEarningDto;
import com.realworld.wages.entities.storeEarning;
import com.realworld.wages.entities.users;
import com.realworld.wages.mapper.storeEarningMapper;
import com.realworld.wages.repository.storeEarningRepository;
import com.realworld.wages.repository.userRepo;
import com.realworld.wages.serviceIF.IstoreEarningService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class storeEarningService implements IstoreEarningService {

    @Autowired
    private userRepo repo;


    @Autowired
    private storeEarningRepository earningRepo;

    @Autowired
    private storeEarningRepository storeRepo;

    @Autowired
    private storeEarningMapper earningMapper;

    public storeEarning create(Long id, storeEarning earning){
    
        users user = repo.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));

        earning.setUserId(id);
        return storeRepo.save(earning);
    }

    @Override
    public List<storeEarning> findAllEarnings() {
        return (earningRepo.findAllEarning());
    }


    public List<storeEarning> findByIDEarningId(Long userId, Long storeEarningId){
       return (earningRepo.findByIdEarningId(userId, storeEarningId));
    }


    public List<storeEarning> fingByUserId(Long userId){
        return (earningRepo.findByUserId(userId));
    }

    public storeEarning updateStoreEarning(Long id ,storeEarningDto earningDto){

        storeEarning earningEntity = earningRepo.findById(id).orElseThrow();

        Long amountAdded = earningDto.getAmountAdded();
        Long currentAmount = earningEntity.getCurrentAmount();
        Long UpdateAmount = amountAdded + currentAmount;

        earningEntity.setAmountAdded(earningDto.getAmountAdded());
        earningEntity.setDescription(earningDto.getDescription());
        earningEntity.setCurrentAmount(UpdateAmount);

        return earningRepo.save(earningEntity);
    }
}
