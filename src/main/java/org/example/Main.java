package org.example;

import java.util.Random;

public class Main {
    public static  int bossHP =800;
    public static  int bossDAMAGE =50;
    public static  String bossDefenseType;
    public static  String[] heroesAttackTypes = {"Kinetic","Magical","Physical","Medic"};
    public static  int[] heroesHP = {300, 200,250,400};
    public static  int[] heroesDamage = {30,40,20,0};
    public static  int round = 0;
    public static void main(String[] args) {
        state();
        while (!isGameFinish()){playRound();}
    }
    public static void state() {
        if (bossHP <0){bossHP=0 ;}
        System.out.println("round is " + round + "--------------");
        System.out.println("BossHP: " + bossHP);
        System.out.println("BossDamage: " + bossDAMAGE);
        System.out.println("BossDefense: " + (
                bossDefenseType==null ? "No defence" : bossDefenseType));

        for (int i = 0; i < heroesAttackTypes.length; i++) {
            System.out.println(heroesAttackTypes[i] + " Health: "
                    + heroesHP[i] + "|" +" Damage: " + heroesDamage[i]);}}

    public static boolean isGameFinish(){boolean allHeroDie = true;
        if (bossHP <= 0){ System.out.println("HeroesWon");
            return true;}
        for (int i = 0; i < heroesAttackTypes.length; i++) {
            if (heroesHP[i]>0) allHeroDie = false; break;
        }
        if (allHeroDie == true){
            System.out.println("Boss won");
            return true; } else return false;
    }
    public static void bossHit (){
        for (int i = 0; i < heroesAttackTypes.length; i++) {
            if (bossHP>0){heroesHP[i]=heroesHP[i] - bossDAMAGE;
                if (heroesHP[i] <0){heroesHP[i]=0 ;}}
        }
    }
    public static void heroesHit () {
        for (int i = 0; i < heroesAttackTypes.length; i++) {
            int hit = heroesDamage[i];
            if (heroesAttackTypes[i] == bossDefenseType){
                Random random = new Random();
                int coeff = random.nextInt(6 );
                hit = heroesDamage[i] * coeff;
                System.out.println("Critical Damage: " + coeff);
            }
            if (heroesHP[i]<=0 || bossHP<=0){continue;}
            bossHP = bossHP - hit;

        }
    }
    public static void playRound (){
        round++;
        Heal();
        chooseDefence();
        heroesHit();
        bossHit();
        state();
    }
    public static void chooseDefence(){
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackTypes.length);
        bossDefenseType = heroesAttackTypes[randomIndex];
    }
    public static void Heal(){
        Random random = new Random();
        int whoLucky = random.nextInt(heroesAttackTypes.length );
        if (heroesHP[whoLucky] < 100 && heroesHP[whoLucky]>0){heroesHP[whoLucky] = heroesHP[whoLucky] + 100;
            System.out.println(("Heal: ") + heroesAttackTypes[whoLucky]);}else {
            System.out.println("No heal:(");
        }
    }
}
