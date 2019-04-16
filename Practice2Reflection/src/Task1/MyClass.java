package Task1;

import javax.swing.*;
import java.lang.reflect.*;

public class MyClass {

    public static String ClassAnalysis(String name) {

        StringBuilder builder = new StringBuilder();
        String res = ""; Class clss = null;
        try {
            clss = Class.forName(name);

            builder.append( "Packаge: " + clss.getPackageName() + "\n");
            builder.append("Name: " + Modifier.toString(clss.getModifiers()) + " ");
            builder.append(clss.getTypeName() + " ");
            builder.append(clss.getName() + "\n");
            if (clss.getSuperclass() != null) {
                builder.append(" extends ");
                Class extend = clss.getSuperclass();
                //for (Class exten : extend) {
                builder.append(extend.getName() + " ");
               // }
            }
            if (clss.getInterfaces() != null) {
                builder.append(" implements ");
                Class[] interfaces = clss.getInterfaces();
                for (Class interf : interfaces) {
                    builder.append(interf.getName() + " ");
                }
            }

            builder.append("\n\nConstructors: ");
            Constructor[] constructors = clss.getDeclaredConstructors();
            for (Constructor constructor : constructors) {
                builder.append(constructor.getName() + " (");
                Parameter[] parameters = constructor.getParameters();
                for (Parameter parameter : parameters) {
                    builder.append(parameter.getType().getName() + " ");
                    builder.append(parameter.getName() + ", ");
                }
                builder.append("\n");
            }

            builder.append("\n");
            builder.append("Methods: \n");
            Method[] methods = clss.getDeclaredMethods();
            for (Method method : methods) {
                builder.append(Modifier.toString(method.getModifiers()) + " ");
                builder.append(method.getReturnType().getName() + " ");
                builder.append(method.getName() + " (");
                Parameter[] parameters = method.getParameters();
                for (Parameter parameter : parameters) {
                    builder.append(parameter.getType().getName() + " ");
                    builder.append(parameter.getName() + ", ");
                }
                builder.append(") \n");
            }

            builder.append("\n");
            builder.append("Fields: \n");
            Field[] fields = clss.getDeclaredFields();
            for (Field field : fields) {
                builder.append(Modifier.toString(field.getModifiers()) + " ");
                builder.append(field.getType().getName() + " ");
                builder.append(field.getName() + "\n");
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return res = builder.toString();
    }

    public static void main(String[] args) {
        JFrame myWindow = new MyFrame();
        myWindow.setVisible(true);
    }

}
