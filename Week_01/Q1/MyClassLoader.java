package Q1;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author deguang
 * @date 2021/01/15
 */

public class MyClassLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) {
        String fullPath = "Week_01/Q1/";
        System.out.println(fullPath + name);
        byte[] cLassBytes = null;
        Path path = null;
        try {
            path = Paths.get(fullPath + name);
            cLassBytes = Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert cLassBytes != null;
        for (int i = 0; i < cLassBytes.length; i++) {
            cLassBytes[i] = (byte)((byte) 0xff ^ cLassBytes[i]);
        }
        Class clazz = defineClass(name.split("\\.")[0], cLassBytes, 0, cLassBytes.length);
        return clazz;
    }

    public static void main(String[] args) {
        MyClassLoader myClassLoader = new MyClassLoader();
        Class<?> aClass = myClassLoader.findClass("Hello.xlass");
        try {
            Object obj = aClass.newInstance();
            Method method = aClass.getMethod("hello");
            method.invoke(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
