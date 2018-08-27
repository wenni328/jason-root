package com.jason.service.impl;

import com.jason.entity.Log;
import com.jason.mapper.LogMapper;
import com.jason.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogMapper logMapper;

    @Override
    public Integer insert(Log log) {
        // TODO Auto-generated method stub
        return logMapper.insert(log);
    }

}
