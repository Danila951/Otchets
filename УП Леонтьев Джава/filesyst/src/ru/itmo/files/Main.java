package ru.itmo.files;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.itmo.files.Entyty.Address;
import ru.itmo.files.Entyty.Geo;
import ru.itmo.files.Entyty.User;

import java.io.IOException;
import java.nio.file.*;


public class Main {

    public static void main(String[] args) {

        String s = null;
        try {
            s = Files.readString(Paths.get("./user.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        User user = gson.fromJson(s, User.class);
        System.out.println(user);

        Geo geo = new Geo(-2345.0,23545.0);
        Address adr = new Address("Lomonosovo street", "fdxgdby", "Sankt-Peterbrg", "4373456285", geo);
        user.setAddress(adr);

        System.out.println(user);


        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        builder.serializeNulls();
        builder.enableComplexMapKeySerialization();
        gson = builder.create();



            String s2 = gson.toJson(user);
        try {
            Files.writeString(Paths.get("./user.json"), s2);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
