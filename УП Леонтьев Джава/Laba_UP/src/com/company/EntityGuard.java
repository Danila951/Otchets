package com.company;


import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class EntityGuard extends Entity {

    public EntityGuard(String title, int xPos, int zPos, double maxHealth, double health) {
        super(title, xPos, zPos, maxHealth, health);
    }

    @Override
    public void update() {
        super.update();
        World world = GameServer.INSTANCE.getWorld();
        double dist = -1;
        int j = 0;

        for(int i=world.getEntities().size()-1;i>=0;i--){
            if (world.getEntities().get(i).getClass() == EntityPlayer.class) {
                 if (dist == -1 || sqrt(pow(world.getEntities().get(i).xPos - this.xPos,2) + pow(world.getEntities().get(i).zPos - this.zPos,2)) < dist) {
                     dist = sqrt(pow(world.getEntities().get(i).xPos - this.xPos,2) + pow(world.getEntities().get(i).zPos - this.zPos,2));
                     j = i;
                 }
            }
        }


        if (dist!=-1 && world.getEntities().get(j).xPos < this.xPos) {
            this.xPos--;
        } else {
            this.xPos++;
        }

        if (dist!=-1 && world.getEntities().get(j).zPos < this.zPos) {
            this.zPos--;
        } else {
            this.zPos++;
        }

    }


}
