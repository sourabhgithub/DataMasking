package com.cat.mask;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
public class MaskData implements Cloneable {
    Set fieldSet=new HashSet();
    String[] spiData = { "cardNumber", "cvv", "expDate" };
 
    public Object clone() {
        return this;
    }
 
    @Override
    public String toString() {
        StringBuffer buffer=new StringBuffer();
        try
        {
        Object object=super.clone();
        printObjectStream(buffer,object);
        }
         catch (CloneNotSupportedException ex)
        {
             ex.printStackTrace();
        }
        return buffer.toString();
    }
 
    private void printObjectStream(StringBuffer buffer, Object object) {
        try {
            buffer.append(object.getClass().getCanonicalName()).append("[\n");
            Object value=null;
            for (Field field : object.getClass().getDeclaredFields()) {
                if(fieldSet.add(field.getName()))
                {
                    field.setAccessible(true);
                    //System.out.println(field.getName());
                    buffer.append(field.getName() + "=");
                    value = field.get(object);
                    if(value!=null)
                    {
                        if(field.getType().isArray() |field.getType().getCanonicalName().startsWith("com.cat.model"))
                        {
                            printObjectStream(buffer,value);
                        }
                        else if (Arrays.asList(spiData).contains(field.getName())) {
                            //field.set(object, replaceDigits((String) field.get(object)));
                            buffer.append(replaceDigits((String) value) );
                        }
                        else
                        {
                            buffer.append( value );
                        }
                    }
                }
 
                buffer.append("\n");
            }
            buffer.append("]");
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        }
 
    }
 
    private String replaceDigits(String text) {
        StringBuffer buffer = new StringBuffer(text.length());
        Pattern pattern = Pattern.compile("\\d");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            matcher.appendReplacement(buffer, "X");
        }
        return buffer.toString();
    }
 
}