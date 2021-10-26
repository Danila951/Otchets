package com.company;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class World {
    private List<Entity> entities  = new ArrayList<>();

    public World(List<Entity> entities) {
        this.entities = entities;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }

    public void updateWorld() {
        for(int i=entities.size()-1;i>=0;i--){
            entities.get(i).update();
        }

        for(int i=entities.size()-1;i>=0;i--){
            if (entities.get(i).health <= 0) {
                entities.remove(i);
            }
        }
    }





    public List<Entity> getEntitiesInRegion(int x, int z, double range) {
        List<Entity> entities2  = new ArrayList<>();
        for(int i=entities.size()-1;i>=0;i--){
            if (sqrt(pow(entities.get(i).xPos - x,2) + pow(entities.get(i).zPos - z,2)) < range) {
                entities2.add(entities.get(i));
            }
        }
        return entities2;
    }


    public List<Entity> getEntitiesNearEntity(Entity entity, double range) {
        List<Entity> entities2  = new ArrayList<>();
        for(int i=entities.size()-1;i>=0;i--){
            if (sqrt(pow(entities.get(i).xPos - entity.getxPos(),2) + pow(entities.get(i).zPos - entity.getzPos(),2)) < range) {
                entities2.add(entities.get(i));
            }
        }
        return entities2;
    }


    public List<EntityGuard> getGuardiansInRegion(int x, int z, double range) {
        List<EntityGuard> entities2  = new ArrayList<>();
        for(int i=entities.size()-1;i>=0;i--){
            if (entities.get(i).getClass() == EntityGuard.class && sqrt(pow(entities.get(i).xPos - x,2) + pow(entities.get(i).zPos - z,2)) <= range) {
                entities2.add((EntityGuard) entities.get(i));
            }
        }
        return entities2;
    }
}
