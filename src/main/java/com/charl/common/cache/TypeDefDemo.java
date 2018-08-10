package com.charl.common.cache;

import lombok.Data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * @program: common
 * @description:
 * @author: charl
 * @create: 2018-08-09 09:35
 **/
public class TypeDefDemo {

    @Data
    static class TypeDefClass {
        private String typeDefStr;

        private String samble;

        private String extra;

        private int index;

        public TypeDefClass(String typeDefStr, String samble, String extra, int index) {
            this.typeDefStr = typeDefStr;
            this.samble = samble;
            this.extra = extra;
            this.index = index;
        }

        public TypeDefClass() {
        }
    }

    public String exportType(String line1,String line2) {
        String[] split = line1.split(";");
        List<TypeDefClass> typeDefClasses = new ArrayList<>(split.length);
        int index = 0;
        for (String s : split) {
            String[] split1 = s.split(" ");
            index++;
            if(!split1[0].equals("typedef")) {
                System.out.println("none");
                break;
            }
            TypeDefClass typeDefClass = new TypeDefClass();
            typeDefClass.setTypeDefStr(split1[0]);
            typeDefClass.setSamble(split1[1]);
            typeDefClass.setExtra(split1[2]);
            typeDefClass.setIndex(index);
            typeDefClasses.add(typeDefClass);
        }
        return export(typeDefClasses,line2,"");
    }

    public String export(List<TypeDefClass> typeDefClasses, String line2, String lian) {
        Iterator<TypeDefClass> iterator = typeDefClasses.iterator();
        String samble = "";
        String extraInfo = "";
        while (iterator.hasNext()) {
            TypeDefClass typeDefClass = iterator.next();

            if (typeDefClass.getExtra().equals(line2)) {
                if (typeDefClass.getIndex() == 1) {
                    return typeDefClass.getSamble() + lian;
                }

                int i = typeDefClass.getSamble().indexOf("*");
                samble = typeDefClass.getSamble().substring(0, i);
                extraInfo = typeDefClass.getSamble().substring(i, typeDefClass.getSamble().length());
                iterator.remove();
            }
        }
        if (samble != "") {
            return export(typeDefClasses, samble, lian + extraInfo);
        }
        return "none";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line1 = scanner.nextLine();
        String line2 = scanner.nextLine();

        TypeDefDemo typeDefDemo = new TypeDefDemo();
        String s = typeDefDemo.exportType(line1, line2);
        System.out.println(s);
    }

}
