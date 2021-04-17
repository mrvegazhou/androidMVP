package com.effortapp.corelib.cache.cacheUtil.strategy;

/**
 * 加解密策略
 */
public interface IEncryptStrategy {

    String encrypt(String str);

    String decode(String str);
}
