package org.example.controller;

import org.example.entity.DoctorEntity;
import org.example.entity.PriceEntity;
import org.example.entity.RequestEntity;
import org.example.enums.ServiceEnum;
import org.example.enums.Step;
import org.example.enums.TimeEnum;
import org.example.repo.DataBase;
import org.example.temp.Temp;
import org.example.util.HashMapUtil;
import org.example.util.InlineButtonUtil;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.ArrayList;

public class MainController {

    private DataBase dataBase = new DataBase();

    public SendMessage messageHandler(Message message) {
        createPrice();
        SendMessage sendMessage = new SendMessage();

        User user = message.getFrom();
        Long id = message.getChatId();
        sendMessage.setChatId(id);

        String text = message.getText();

        if (text.equals("/start") || text.equals("/menu")) {
            sendMessage.setText("Menu tanlang");
            sendMessage.setReplyMarkup(InlineButtonUtil.mainMenu());
        } else if (HashMapUtil.requestHashMap.get(message.getChatId()) != null) {
            sendMessage = textHandler(message, sendMessage);
        }

        return sendMessage;
    }

    public SendMessage textHandler(Message message, SendMessage sendMessage) {
        RequestEntity entity = HashMapUtil.requestHashMap.get(message.getChatId());

        if (entity != null && entity.getStep().equals(Step.ENTER_FIRSTNAME)) {
            entity.setFirstName(message.getText());
            entity.setStep(Step.ENTER_LASTNAME);
            HashMapUtil.requestHashMap.put(message.getChatId(), entity);
            sendMessage.setText("Familiyangizni kiriting");
        }

        return sendMessage;
    }

    public SendMessage callBackHandler(Message message, User user, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());

        switch (text) {
            case "service_callback" -> {
                sendMessage.setText("Xizmatlarimizdan foydalanish uchun tugmani bosing");
                sendMessage.setReplyMarkup(InlineButtonUtil.serviceMenu());
            }
            case "price_callback" -> sendMessage = showPrices(message, sendMessage);
            case "location_callback" ->
                    sendMessage.setText("https://yandex.uz/maps/org/206768865623/?ll=69.170932%2C41.348576&z=15");
            case "dr_callback" -> {
            }
            case "bio_callback" -> {
            }
            case "breket_callback" -> sendMessage = doctorMenu(message, sendMessage, text);
            case "0_callback" -> sendMessage = bronDoctor(message, sendMessage, text);
        }


        return sendMessage;
    }

    public SendMessage doctorMenu(Message message, SendMessage sendMessage, String text) {
        RequestEntity entity = new RequestEntity();
        ArrayList<DoctorEntity> doctorList = dataBase.getDrList();
        entity.setId(message.getChatId());

        if (text.equals("breket_callback")) {
            entity.setService(ServiceEnum.BREKET);
        }

        HashMapUtil.requestHashMap.put(message.getChatId(), entity);

        sendMessage.setText("Doltorlar bilan tanshing");
        sendMessage.setReplyMarkup(InlineButtonUtil.doctorMenu(doctorList));

        return sendMessage;
    }

    public SendMessage bronDoctor(Message message, SendMessage sendMessage, String text) {
        RequestEntity entity = HashMapUtil.requestHashMap.get(message.getChatId());
        entity.setUserId(message.getChatId());
        entity.setStep(Step.ENTER_TIME);
        HashMapUtil.requestHashMap.put(message.getChatId(), entity);

        DoctorEntity doctor = dataBase.getDoctorByIndex(Integer.parseInt(text.substring(0, 1)));

        ArrayList<TimeEnum> time = doctor.getFreeTime();

        ArrayList<String> emoji = new ArrayList<>();

        for (TimeEnum timeEnum : time) {
            if (timeEnum == null) {
                emoji.add("\uD83D\uDD52❌");
            } else {
                emoji.add(timeEnum + "✅");
            }
        }

        sendMessage.setText("Bron qilmoqchi bo'lgan vaqtni tanglar");
        sendMessage.setReplyMarkup(InlineButtonUtil.timeMenu(emoji));
        return sendMessage;
    }

    public SendMessage showPrices(Message message, SendMessage sendMessage) {

        ArrayList<PriceEntity> priceList = dataBase.getPriceList();
        StringBuilder builder = new StringBuilder();
        builder.append("--Narxlarimiz bilan tanishing--");
        for (PriceEntity entity : priceList) {
            builder.append("\n-------------------------");
            builder.append("\n Xizmat nomi: " + entity.getServiceName());
            builder.append("\n Xizmat tarifi: " + entity.getType());
            builder.append("\n Xizmat narxi: " + entity.getPrice() + " so'm");
            builder.append("\n-------------------------");
        }
        sendMessage.setText(builder.toString());
        return sendMessage;
    }

    public void createPrice() {
        if (!Temp.isCreated) {
            dataBase.createPrice();
            dataBase.createDr();
            Temp.isCreated = true;
        }

    }


}
