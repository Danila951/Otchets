package com.company;

import java.util.ArrayList;
import java.util.List;

public abstract class Entity {
    protected String title;
    protected int xPos;
    protected int zPos;
    protected int age;//количество пережитых обновлений
    protected double maxHealth; //максимальное колво хп
    protected double health; //текущее количество хп

    public Entity(String title, int xPos, int zPos, double maxHealth, double health) {
        this.title = title;
        this.xPos = xPos;
        this.zPos = zPos;
        this.age = 0;
        this.maxHealth = maxHealth;
        this.health = health;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getzPos() {
        return zPos;
    }

    public void setzPos(int zPos) {
        this.zPos = zPos;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(double maxHealth) {
        this.maxHealth = maxHealth;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public void update() {
        age++;
    } //age++



    public boolean attackEntityFrom(Entity entity, double damage){
        this.health-=damage;
        return this.health<=0;
    }

}
