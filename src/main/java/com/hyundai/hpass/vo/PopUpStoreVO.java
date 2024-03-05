package com.hyundai.hpass.vo;

import com.hyundai.hpass.dto.PopUpStoreDTO;
import lombok.Data;

import java.util.List;

@Data
public class PopUpStoreVO {
    private String date;
    private List<PopUpStoreDTO> result;
}
