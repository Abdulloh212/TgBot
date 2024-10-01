package org.example.Bot;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

    public class BotServise {
    static TelegramBot telegramBot = new TelegramBot("7430990348:AAEwTZ1bdnusjbLWQ7niDdgGbVw8VVs_K5E");

        public static TgUser getOrCreateUser(Long id) {
            for (TgUser user : Db.USERS) {
                if (user.getChatId().equals(id)) {
                    return user;
                }
            }
            TgUser tgUser = new TgUser();
            tgUser.setChatId(id);
            Db.USERS.add(tgUser);
            return tgUser;
        }


        public static void wellcomeAndChoose(TgUser tgUser) {
            InlineKeyboardMarkup inlineKeyboardMarkup = generateBtns();
            SendMessage sendMessage = new SendMessage(tgUser.getChatId(), "Salom, botga hush kelibsiz!");
            sendMessage.replyMarkup(inlineKeyboardMarkup);
            telegramBot.execute(sendMessage);
            tgUser.setTgstate(Tgstate.WELLCOMING);
        }



        private static List<User> generateUser() {
            try {
                HttpClient httpClient = HttpClient.newBuilder().build();
                HttpRequest request = HttpRequest.newBuilder(
                        URI.create("https://jsonplaceholder.typicode.com/users")).GET().build();
                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

                Gson gson = new Gson();
                return gson.fromJson(response.body(), new TypeToken<List<User>>() {}.getType());
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        public static void appectChooseAndShowPosts(TgUser tgUser, String data) {
            User user = choosenUser(data);
            List<Post> posts = post(user);
            InlineKeyboardMarkup inlineKeyboardMarkup = generatePostBtns(posts);
            SendMessage sendMessage = new SendMessage(tgUser.getChatId(), "Posts");
            sendMessage.replyMarkup(inlineKeyboardMarkup);
            telegramBot.execute(sendMessage);
            tgUser.setTgstate(Tgstate.POSTING);
        }

        private static InlineKeyboardMarkup generateBtns() {
            List<User> users = generateUser();
            InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
            for (User user : users) {
            inlineKeyboardMarkup.addRow(
                    new InlineKeyboardButton(user.getName()).callbackData(user.getId().toString()),
                    new InlineKeyboardButton("POSTS").callbackData(user.getId().toString())
                    );
            }

            return inlineKeyboardMarkup;
        }


        private static User choosenUser(String data) {
            List<User> users = generateUser();
            for (User user : users) {
                if (String.valueOf(user.getId()).equals(data)) {
                    return user;
                }
            }
            return null;
        }


        private static InlineKeyboardMarkup generatePostBtns(List<Post> posts) {
            InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
            for (Post post : posts) {
                inlineKeyboardMarkup.addRow(
                        new InlineKeyboardButton(post.getTitle()).callbackData(post.getId().toString()),
                        new InlineKeyboardButton("COMMENTS").callbackData(post.getId().toString())
                );
            }
            return inlineKeyboardMarkup;
        }

        private static List<Post> post(User user) {
            List<Post> posts = generatePosts();
            List<Post> filteredPosts = new ArrayList<>();

            for (Post post : posts) {
                if (post.getUserId().equals(user.getId())) {
                    filteredPosts.add(post);
                }
            }

            return filteredPosts;
        }

        private static List<Post> generatePosts() {
            try {
                HttpClient httpClient = HttpClient.newBuilder().build();
                HttpRequest request = HttpRequest.newBuilder(
                        URI.create("https://jsonplaceholder.typicode.com/posts")).GET().build();
                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

                Gson gson = new Gson();
                return gson.fromJson(response.body(), new TypeToken<List<Post>>() {}.getType());
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        public static void appectPostAndShowComment(TgUser tgUser, String data) {
            List<Comments>comments=generate();
            Comments comments1=null;
            for (Comments comment : comments) {
                if (comment.getId().equals(data)){
                    comments1=comment;
                }
            }
            SendMessage sendMessage=new SendMessage(tgUser.getChatId(),
                    "COMMENT ID"+
                    comments1.getId()+"--"+
                    "COMMENT NAME "+
                    comments1.getName()+"--" +
                    "COMMENT BODY"+
                    comments1.getBody() );
            telegramBot.execute(sendMessage);
        }

        private static List<Comments> generate() {
            try {
                HttpClient httpClient = HttpClient.newBuilder().build();
                HttpRequest request = HttpRequest.newBuilder(
                        URI.create("https://jsonplaceholder.typicode.com/comments")).GET().build();
                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

                Gson gson = new Gson();
                return gson.fromJson(response.body(), new TypeToken<List<Comments>>() {}.getType());
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
