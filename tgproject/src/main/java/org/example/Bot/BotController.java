package org.example.Bot;

import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;

import java.time.LocalDate;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class BotController {
    static ExecutorService executorService = Executors.newFixedThreadPool(10);
    public void start() {

        BotServise.telegramBot.setUpdatesListener(updates -> {
            for (Update update1 : updates) {
                executorService.execute(() -> {
                    try {
                        handleUpdate(update1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }
    private void handleUpdate(Update update) {
        if (update.message() != null) {
            Message message = update.message();
            TgUser tgUser = BotServise.getOrCreateUser(message.chat().id());
            if (message.text() != null ){
                if (message.text().equals("/start")){
                BotServise.wellcomeAndChoose(tgUser);
                }
            }
        } else if (update.callbackQuery() != null) {
            CallbackQuery callbackQuery=update.callbackQuery();
            String data= callbackQuery.data();
            TgUser tgUser = BotServise.getOrCreateUser(callbackQuery.from().id());

            if (tgUser.getTgstate().equals(Tgstate.WELLCOMING)) {
                BotServise.appectChooseAndShowPosts(tgUser, data);
            } else if (tgUser.getTgstate().equals(Tgstate.POSTING)) {
                BotServise.appectPostAndShowComment(tgUser, data);

            }
        }
    }


}
