package com.effortapp.corelib.cache.cacheUtil.strategy;

import android.content.Context;

import com.effortapp.corelib.utils.KeyStoreHelper;

/**
 * KeyStore加密策略
 */
public class KeyStoreEncryptStrategy implements IEncryptStrategy {

    private Context mContext;
    private String alias;

    public KeyStoreEncryptStrategy(Context context) {
        this(context, context.getPackageName());
    }

    public KeyStoreEncryptStrategy(Context context, String alias) {
        this.mContext = context;
        this.alias = context.getPackageName() + "_" + alias;
        createKeyStoreSecretKey(this.alias);
    }

    @Override
    public String encrypt(String str) {
        try {
            return KeyStoreHelper.encrypt(mContext, alias, str);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public String decode(String str) {
        try {
            return KeyStoreHelper.decrypt(mContext, alias, str);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private void createKeyStoreSecretKey(String alias) {
        try {
            KeyStoreHelper.createKeys(mContext, alias);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
