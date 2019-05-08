package com.netty.demo.netty190508_1.serialize.impl;


import com.alibaba.fastjson.JSON;
import com.netty.demo.netty190508_1.serialize.Serializer;
import com.netty.demo.netty190508_1.serialize.SerializerAlgorithm;

public class JSONSerializer implements Serializer {
   
    @Override
    public byte getSerializerAlgorithm() {
        
        return SerializerAlgorithm.JSON;
    } 

    @Override
    public byte[] serialize(Object object) {
        
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        
        return JSON.parseObject(bytes, clazz);
    }
}