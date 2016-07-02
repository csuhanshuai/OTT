package com.huawei.ottvideo;

import android.content.Context;
import android.content.SharedPreferences;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by jiangzuyun on 2015/9/7.
 */
public class SPUtills
{

    /**
     * 保存在手机里面的文件名
     */
    public static final String FILE_NAME = "share_data";
    private SharedPreferences msp;
    private SharedPreferences.Editor editor;

    public SPUtills()
    {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 创建sp对象
     *
     * @param context
     * @param fileName
     *         存储名字
     * @param mode
     *         模式
     */
    public SPUtills(Context context, String fileName, int mode)
    {
        msp = context.getSharedPreferences(fileName, mode);
        editor = msp.edit();
    }

    public SPUtills(Context context, String fileName)
    {
        msp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        editor = msp.edit();
    }


    /**
     * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
     * 默认存储名 share_data
     * MODE_PRIVATE
     *
     * @param context
     * @param key
     * @param object
     */
    public static void put(Context context, String key, Object object)
    {

        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        if(object instanceof String)
        {
            editor.putString(key, (String)object);
        }else if(object instanceof Integer)
        {
            editor.putInt(key, (Integer)object);
        }else if(object instanceof Boolean)
        {
            editor.putBoolean(key, (Boolean)object);
        }else if(object instanceof Float)
        {
            editor.putFloat(key, (Float)object);
        }else if(object instanceof Long)
        {
            editor.putLong(key, (Long)object);
        }else
        {
            editor.putString(key, object.toString());
        }

        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 将 键值 写入sp文件
     *
     * @param key
     *         键
     * @param object
     *         各种类型的值
     */
    public void put(String key, Object object)
    {
        if(object.getClass() == String.class)
        {
            editor.putString(key, (String)object);
        }else if(object.getClass() == Integer.class)
        {
            editor.putInt(key, (Integer)object);
        }else if(object.getClass() == Boolean.class)
        {
            editor.putBoolean(key, (Boolean)object);
        }else if(object.getClass() == Float.class)
        {
            editor.putFloat(key, (Float)object);
        }else if(object.getClass() == Long.class)
        {
            editor.putLong(key, (Long)object);
        }else
        {
            editor.putString(key, object.toString());
        }
        editor.commit();
    }

    /**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     * MODE_PRIVATE
     *
     * @param context
     * @param key
     * @param defaultObject
     * @return
     */
    public static Object get(Context context, String key, Object defaultObject)
    {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);

        if(defaultObject instanceof String)
        {
            return sp.getString(key, (String)defaultObject);
        }else if(defaultObject instanceof Integer)
        {
            return sp.getInt(key, (Integer)defaultObject);
        }else if(defaultObject instanceof Boolean)
        {
            return sp.getBoolean(key, (Boolean)defaultObject);
        }else if(defaultObject instanceof Float)
        {
            return sp.getFloat(key, (Float)defaultObject);
        }else if(defaultObject instanceof Long)
        {
            return sp.getLong(key, (Long)defaultObject);
        }

        return null;
    }

    public Object get(String key, Object defaultObject)
    {
        if(defaultObject.getClass() == String.class)
        {
            return msp.getString(key, (String)defaultObject);
        }else if(defaultObject.getClass() == Integer.class)
        {
            return msp.getInt(key, (Integer)defaultObject);
        }else if(defaultObject.getClass() == Boolean.class)
        {
            return msp.getBoolean(key, (Boolean)defaultObject);
        }else if(defaultObject.getClass() == Float.class)
        {
            return msp.getFloat(key, (Float)defaultObject);
        }else if(defaultObject.getClass() == Long.class)
        {
            return msp.getLong(key, (Long)defaultObject);
        }

        return null;
    }

    /**
     * 移除某个key值已经对应的值
     * MODE_PRIVATE
     *
     * @param context
     * @param key
     */
    public static void remove(Context context, String key)
    {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        SharedPreferencesCompat.apply(editor);
    }


    /**
     * 移除某个key值已经对应的值
     * MODE_PRIVATE
     *
     * @param key
     */
    public void remove(String key)
    {
        editor.remove(key);
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 清除所有数据
     *
     * @param context
     */
    public static void clear(Context context)
    {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 清除所有数据
     */
    public void clear()
    {
        editor.clear();
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 查询某个key是否已经存在
     *
     * @param context
     * @param key
     * @return
     */
    public static boolean contains(Context context, String key)
    {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.contains(key);
    }

    /**
     * 查询某个key是否已经存在
     *
     * @param key
     * @return
     */
    public boolean contains(String key)
    {
        return msp.contains(key);
    }

    /**
     * 返回所有的键值对
     *
     * @param context
     * @return
     */
    public static Map<String,?> getAll(Context context)
    {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.getAll();
    }

    /**
     * 返回所有的键值对
     *
     * @return
     */
    public Map<String,?> getAll()
    {
        return msp.getAll();
    }

    /**
     * 创建一个解决SharedPreferencesCompat.apply方法的一个兼容类
     *
     * @author Jonas
     */
    private static class SharedPreferencesCompat
    {
        private static final Method sApplyMethod = findApplyMethod();

        /**
         * 反射查找apply的方法
         *
         * @return
         */
        @SuppressWarnings({"unchecked", "rawtypes"})
        private static Method findApplyMethod()
        {
            try
            {
                Class clz = SharedPreferences.Editor.class;
                return clz.getMethod("apply");
            }catch(NoSuchMethodException e)
            {
            }

            return null;
        }

        /**
         * 如果找到则使用apply执行，否则使用commit
         *
         * @param editor
         */
        public static void apply(SharedPreferences.Editor editor)
        {
            try
            {
                if(sApplyMethod != null)
                {
                    sApplyMethod.invoke(editor);
                    return;
                }
            }catch(IllegalArgumentException e)
            {
            }catch(IllegalAccessException e)
            {
            }catch(InvocationTargetException e)
            {
            }
            editor.commit();
        }
    }

}
