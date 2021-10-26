package com.company;

public class EntityMonster extends Entity {
    private double damage;

    public EntityMonster(String title, int xPos, int zPos, double maxHealth, double health, double damage) {
        super(title, xPos, zPos, maxHealth, health);
        this.damage = damage;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    @Override
    public void update() {
        super.update();
    }
}
