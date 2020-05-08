package com.loya.Factory;

public class Factory {
    /**
     *
     * @param clazzimpl 接口实现类
     * @param iclazz    接口类
     * @param <T>
     * @return  实例对象
     */
    public static <T> T getService(Class clazzimpl , Class<T> iclazz){
        T instance = null;
        try {
            instance = (T) Class.forName(clazzimpl.getName()).getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }
}
