package com.testProject.twitchBot.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.testProject.twitchBot.twitchApiCommand.CreationDateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;

@Service
public class TwitchRequestHandler {

    @Autowired
    private CreationDateRequest dateRequest;

    public String getAge(String sendBy){
        String creationDate = "";
        dateRequest
                .execute(sendBy)
                .log()
                .map(jsonNode -> setDateFromJsonResponse(creationDate,jsonNode))

                .subscribe();
        System.out.println("CREATION DATE "+ creationDate);
        return creationDate;
    }

    private String setDateFromJsonResponse(String date, JsonNode response){
        String createdAt = "";
        JsonNode data = response.path("data");
        System.out.println(data);
        System.out.println(data.isArray());

        Iterator<JsonNode> iterator = data.iterator();
        JsonNode next = iterator.next();
        System.out.println("id " + next.path("id").textValue());
        System.out.println("name " + next.path("login").textValue());
        date = next.path("created_at").textValue();
        System.out.println("DATE " + date);
        return date;
    }


}






        /*
        *
        * String createdAt = "";
                    JsonNode data = jsonNode.path("data");
                    System.out.println(data);
                    System.out.println(data.isArray());

                    Iterator<JsonNode> iterator = data.iterator();
                    JsonNode next = iterator.next();
                    System.out.println("id " + next.path("id").textValue());
                    System.out.println("name " + next.path("login").textValue());
                    System.out.println("created_at " + next.path("created_at").textValue());

                    return createdAt;
        *
        * */





        /*System.out.println("next element");
        JsonNode id = next.path("id");
        System.out.println(next);
        System.out.println("id" + id);
        System.out.println("id value" + id.textValue());
        String stringId = id.textValue();
        System.out.println("string " + stringId);*/




        /*
        *
        * JsonNode data = jsonNode.path("data");
                    System.out.println(data);
                    System.out.println(data.isArray());

                    System.out.println(data.toString());

                    Iterator<JsonNode> iterator = data.iterator();
                    System.out.println(iterator.next());
                    while(iterator.hasNext()){
                        JsonNode next = iterator.next();



                    }




    /*String creationDate;
        dateRequest
                .execute(sendBy)
                .log()
                .map(jsonNode -> {
                String createdAt = "";
                for (JsonNode data : jsonNode.path("data")) {
                String current = data.textValue();
                if (current.equals( "created_at")){
                createdAt = current;
                }
                System.out.println(data);
                }
                System.out.println(createdAt);
                return createdAt;
                })
                .log()
                .subscribe();
                return LocalDateTime.now();*/