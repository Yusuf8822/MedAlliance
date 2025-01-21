package org.example;

import org.example.controller.MainController;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class MyBot extends TelegramLongPollingBot {

    private MainController mainController = new MainController();

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            if (message.hasText()) {
                send(mainController.messageHandler(message));
            }
        }
        else if (update.hasCallbackQuery() ) {
            Message message = (Message) update.getCallbackQuery().getMessage();
            User user = update.getCallbackQuery().getFrom();
            String text = update.getCallbackQuery().getData();
            send(mainController.callBackHandler(message, user, text));
        }
    }

    @Override
    public String getBotUsername() {
        return "https://t.me/yusuf_88_bot";
    }

    @Override
    public String getBotToken() {
        return "7590586963:AAF-ZniFbAVoyb2IcSNKtH4T-8v0d8lCeqA";
    }

    public void send(SendMessage sendMessage) {
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }


}
