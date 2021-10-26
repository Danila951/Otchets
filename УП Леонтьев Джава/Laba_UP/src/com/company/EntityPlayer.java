package com.company;

import java.util.ArrayList;
import java.util.List;

public class EntityPlayer extends Entity {
    private int exp;

    public EntityPlayer(String title, int xPos, int zPos, double maxHealth, double health, int exp) {
        super(title, xPos, zPos, maxHealth, health);
        this.exp = exp;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    @Override
    public void update() {
        super.update();

        if(health < maxHealth && health>0 && age%2 == 0) {
            health++;
            System.out.println("Здоровье увеличено, теперь оно у " + this.title + " равняется " + health);
        }
    }

    @Override
    public boolean attackEntityFrom(Entity entity, double damage) {
        World world = GameServer.INSTANCE.getWorld();

        List<EntityGuard> listGuard = new ArrayList<>(world.getGuardiansInRegion(this.xPos,this.zPos,2));

        if (listGuard.size() > 0) {
            listGuard.get(0).attackEntityFrom(this, damage);
            System.out.println("Урон получил " + listGuard.get(0).getTitle() + " тепере его ХП: " + listGuard.get(0).getHealth());
        } else {
            health -= damage;
            if (this.health > 0) {
                double dam = 3+ (double)exp/2;
                System.out.println(this.title + " бьет " + entity.getTitle() + " на " + dam);
                entity.attackEntityFrom(this, calculateDamage(entity));
                System.out.println("Теперь здоровье " + entity.getTitle() + " равняется " + entity.getHealth());
            } else {
                System.out.println(this.title + " умер от " + entity.getTitle());
            }
        }

        return this.health<=0;

    }



    public double calculateDamage(Entity entity) {
        double damage = 3+ (double)exp/2;
        if(entity.health <= damage){
            exp++;
            System.out.println(this.title + " убил " + entity.getTitle());
        }
        return  damage;
    }
}
