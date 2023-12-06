package org.example;

enum RomanNum {
    I(1),II(2),III(3),IV(4),V(5),VI(6),VII(7),VIII(8),IX(9),X(10),L(50),C(100);

    private final int num;

    RomanNum(int num){
        this.num = num;
    }

    public int getNum(){
        return num;
    }
}
