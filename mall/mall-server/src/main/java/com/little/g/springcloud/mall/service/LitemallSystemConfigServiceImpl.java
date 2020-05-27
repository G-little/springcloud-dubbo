package com.little.g.springcloud.mall.service;

import com.little.g.springcloud.common.utils.DTOUtil;
import com.little.g.springcloud.mall.api.LitemallSystemConfigService;
import com.little.g.springcloud.mall.dto.LitemallSystemDTO;
import com.little.g.springcloud.mall.mapper.LitemallSystemMapper;
import com.little.g.springcloud.mall.model.LitemallSystem;
import com.little.g.springcloud.mall.model.LitemallSystemExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LitemallSystemConfigServiceImpl implements LitemallSystemConfigService {

    @Resource
    private LitemallSystemMapper systemMapper;

    @Override
    public Map<String, String> queryAll() {
        LitemallSystemExample example = new LitemallSystemExample();
        example.or().andDeletedEqualTo(false);

        return getSystemConfigMapByExample(example);
    }

    private Map<String, String> getSystemConfigMapByExample(
            LitemallSystemExample example) {
        List<LitemallSystemDTO> systemList = DTOUtil.convert2List(
                systemMapper.selectByExample(example), LitemallSystemDTO.class);
        Map<String, String> systemConfigs = new HashMap<>();
        for (LitemallSystemDTO item : systemList) {
            systemConfigs.put(item.getKeyName(), item.getKeyValue());
        }

        return systemConfigs;
    }

    @Override
    public Map<String, String> listMail() {
        LitemallSystemExample example = new LitemallSystemExample();
        example.or().andKeyNameLike("litemall_mall_%").andDeletedEqualTo(false);
        return getSystemConfigMapByExample(example);
    }

    @Override
    public Map<String, String> listWx() {
        LitemallSystemExample example = new LitemallSystemExample();
        example.or().andKeyNameLike("litemall_wx_%").andDeletedEqualTo(false);
        return getSystemConfigMapByExample(example);
    }

    @Override
    public Map<String, String> listOrder() {
        LitemallSystemExample example = new LitemallSystemExample();
        example.or().andKeyNameLike("litemall_order_%").andDeletedEqualTo(false);
        return getSystemConfigMapByExample(example);
    }

    @Override
    public Map<String, String> listExpress() {
        LitemallSystemExample example = new LitemallSystemExample();
        example.or().andKeyNameLike("litemall_express_%").andDeletedEqualTo(false);
        return getSystemConfigMapByExample(example);
    }

    @Override
    public void updateConfig(Map<String, String> data) {
        for (Map.Entry<String, String> entry : data.entrySet()) {
            LitemallSystemExample example = new LitemallSystemExample();
            example.or().andKeyNameEqualTo(entry.getKey()).andDeletedEqualTo(false);

            LitemallSystem system = new LitemallSystem();
            system.setKeyName(entry.getKey());
            system.setKeyValue(entry.getValue());
            system.setUpdateTime(LocalDateTime.now());
            systemMapper.updateByExampleSelective(system, example);
        }

    }

    @Override
    public void addConfig(String key, String value) {
        LitemallSystem system = new LitemallSystem();
        system.setKeyName(key);
        system.setKeyValue(value);
        system.setAddTime(LocalDateTime.now());
        system.setUpdateTime(LocalDateTime.now());
        systemMapper.insertSelective(system);
    }

}
