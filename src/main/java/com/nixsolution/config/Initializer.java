package com.nixsolution.config;

import com.nixsolution.annotation.PropertyKey;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

public class Initializer {

    public static void initialize(Object object) throws Exception {
        Properties properties = new Properties();
        InputStream is = Initializer.class.getClassLoader().getResourceAsStream("app.properties");
        properties.load(is);
        Class<?> aClass = object.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(PropertyKey.class)) {
                PropertyKey annotation = field.getAnnotation(PropertyKey.class);
                String property = properties.getProperty(annotation.value());
                try {
                    castObject(object, field, property);
                } catch (Exception e) {
                    throw new RuntimeException("Can't set filed " + field.getName());
                }
            }
        }
    }

    private static Object castObject(Object obj, Field filed, String value) throws ClassCastException, IllegalAccessException {

        filed.setAccessible(true);
        Class<?> filedType = filed.getType();
        if (Boolean.class == filedType || Boolean.TYPE == filedType) filed.set(obj, Boolean.parseBoolean(value));
        else if (Byte.class == filedType || Byte.TYPE == filedType) filed.set(obj, Byte.parseByte(value));
        else if (Short.class == filedType || Short.TYPE == filedType) filed.set(obj, Short.parseShort(value));
        else if (Integer.class == filedType || Integer.TYPE == filedType) filed.set(obj, Integer.parseInt(value));
        else if (Long.class == filedType || Long.TYPE == filedType) filed.set(obj, Long.parseLong(value));
        else if (Float.class == filedType || Float.TYPE == filedType) filed.set(obj, Float.parseFloat(value));
        else if (Double.class == filedType || Double.TYPE == filedType) filed.set(obj, Double.parseDouble(value));
        else if (filedType.isEnum()) filed.set(obj, Enum.valueOf((Class<Enum>) filedType, value));
        else if (String.class == filedType) filed.set(obj, value);
        return obj;
    }
}
