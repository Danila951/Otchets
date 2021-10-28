package com.company;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class EntityMonster extends Entity {
    private double damage;

    public EntityMonster(String title, int xPos, int zPos, double maxHealth, double health, double damage) {
        super(title, xPos, zPos, maxHealth, health);
        this.damage = damage;
    }



    @Override
    public void update() {
        super.update();

        World world = GameServer.INSTANCE.getWorld();
        double dist = 99999;
        int j = 0;

        for(int i=world.getEntities().size()-1;i>=0;i--){
            if (world.getEntities().get(i).getClass() == EntityPlayer.class) {
                if (sqrt(pow(world.getEntities().get(i).xPos - this.xPos,2) + pow(world.getEntities().get(i).zPos - this.zPos,2)) < dist) {
                    dist = sqrt(pow(world.getEntities().get(i).xPos - this.xPos,2) + pow(world.getEntities().get(i).zPos - this.zPos,2));
                    j = i;
                }
            }
        }
        if(dist > 2 && dist != 99999){
            if (world.getEntities().get(j).xPos < this.xPos) {
                this.xPos--;
            } else {
                this.xPos++;
            }

            if (world.getEntities().get(j).zPos < this.zPos) {
                this.zPos--;
            } else {
                this.zPos++;
            }
        } else if(dist != 99999){
            System.out.println(this.title + " бьет " + world.getEntities().get(j).getTitle()  + " на " + damage);
            world.getEntities().get(j).attackEntityFrom(this, damage);
            System.out.println("Теперь здоровье " + world.getEntities().get(j).getTitle() + " равняется " + world.getEntities().get(j).getHealth());
        }
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }
}
