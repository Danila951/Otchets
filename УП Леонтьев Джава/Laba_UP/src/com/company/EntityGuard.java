package com.company;

import jdk.dynalink.linker.LinkerServices;

import java.util.List;

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
        double dist = 9999999;
        int j = -1;

        for(int i=world.getEntities().size()-1;i>=0;i--){
            if (world.getEntities().get(i).getClass() == EntityPlayer.class) {
                 if (sqrt(pow(world.getEntities().get(i).xPos - this.xPos,2) + pow(world.getEntities().get(i).zPos - this.zPos,2)) < dist) {
                     dist = sqrt(pow(world.getEntities().get(i).xPos - this.xPos,2) + pow(world.getEntities().get(i).zPos - this.zPos,2));
                     j = i;
                 }
            }
        }


        if (j == -1) {

        } else if (world.getEntities().get(j).xPos == this.xPos) {
            //System.out.println(this.title + " дошел до " + world.getEntities().get(j).getTitle() + " по X");
        } else if (world.getEntities().get(j).xPos < this.xPos) {
            this.xPos--;
        } else {
            this.xPos++;
        }

        if (j == -1) {

        } else if (world.getEntities().get(j).zPos == this.zPos) {
            //System.out.println(this.title + " дошел до " + world.getEntities().get(j).getTitle() + " по Z");
        } else if (world.getEntities().get(j).zPos < this.zPos) {
            this.zPos--;
        } else {
            this.zPos++;
        }

    }


}
