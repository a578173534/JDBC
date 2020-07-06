package com.ggs.jdbc;


import java.util.Date;

public class Test {
    public static void main(String[] args) {
        Date d = new java.sql.Date(new Date("1999 - 11 - 20").getTime());
        System.out.println(d);

    }
}
