package com.little.g.springcloud.mall.api;

import com.little.g.springcloud.mall.dto.LitemallAddressDTO;

import java.util.List;

public interface LitemallAddressService {

    List<LitemallAddressDTO> queryByUid(Integer uid);

    LitemallAddressDTO query(Integer userId, Integer id);

    int add(LitemallAddressDTO address);

    int update(LitemallAddressDTO address);

    void delete(Integer id);

    LitemallAddressDTO findDefault(Integer userId);

    void resetDefault(Integer userId);

    List<LitemallAddressDTO> querySelective(Integer userId, String name, Integer page,
                                            Integer limit, String sort, String order);

}
