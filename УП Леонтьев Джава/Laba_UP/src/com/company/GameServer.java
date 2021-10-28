package com.company;

public class GameServer { //Основной класс
    public static GameServer INSTANCE;
    private World world; //мир c сущностями
    private int updateCounter; //колчество обновлений сервера

    public GameServer(World world) { //контруктор
        INSTANCE = this;
        this.world = world;
        this.updateCounter = 0;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public int getUpdateCounter() {
        return updateCounter;
    }

    public void setUpdateCounter(int updateCounter) {
        this.updateCounter = updateCounter;
    }



    public void updateServer() {
        updateCounter++;
        world.updateWorld();
    }
}
