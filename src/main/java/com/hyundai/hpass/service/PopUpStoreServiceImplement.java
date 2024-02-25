package com.hyundai.hpass.service;

import com.hyundai.hpass.dto.PopUpStoreDTO;
import com.hyundai.hpass.mapper.PopUpStoreMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class PopUpStoreServiceImplement implements PopUpStoreService {

    @Autowired
    PopUpStoreMapper popUpStoreMapper;

    @Override
    public List<PopUpStoreDTO> getAllPopUpStore() {
       log.info("aaaaaaaaa");
        return popUpStoreMapper.getAllPopUpStore();
    }
}
