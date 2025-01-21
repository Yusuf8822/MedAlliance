package org.example.util;

import com.vdurmont.emoji.EmojiParser;
import org.example.entity.DoctorEntity;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class InlineButtonUtil {

    public static InlineKeyboardButton button(String text, String callBack, String emoji) {
        String emojiText= EmojiParser.parseToUnicode(emoji+" "+text);
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(emojiText);
        button.setCallbackData(callBack);
        return button;
    }

    public static List<InlineKeyboardButton> row(InlineKeyboardButton... buttons) {
        return new LinkedList<>(Arrays.asList(buttons));
    }

    public static List<List<InlineKeyboardButton>> rowList(List<InlineKeyboardButton>... rows) {
        return new LinkedList<>(Arrays.asList(rows));
    }

    public static InlineKeyboardMarkup keyboard(List<List<InlineKeyboardButton>> rows) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(rows);
        return inlineKeyboardMarkup;
    }

    public static InlineKeyboardMarkup menuSingleKeyboard() {
        InlineKeyboardButton button=InlineButtonUtil.button("MENU", "menu_callback", ":dart:");
        List<InlineKeyboardButton> row = InlineButtonUtil.row(button);
        return InlineButtonUtil.keyboard(InlineButtonUtil.rowList(row));
    }

    public static ReplyKeyboardMarkup createShareContact(  ) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setSelective(true);
        KeyboardButton shareContactButton = new KeyboardButton();
        shareContactButton.setText("Enter contact");
        shareContactButton.setRequestContact(true);
        replyKeyboardMarkup.setKeyboard( List.of((KeyboardRow) List.of( shareContactButton )) );
        return replyKeyboardMarkup;
    }


    public static InlineKeyboardMarkup mainMenu() {
        InlineKeyboardButton button = InlineButtonUtil.button("Xizmatlarimiz", "service_callback", "⚙\uFE0F");
        List<InlineKeyboardButton> row = InlineButtonUtil.row(button);

        InlineKeyboardButton button1 = InlineButtonUtil.button("Narxlar bilan tanishing", "price_callback", "\uD83D\uDCB0");
        List<InlineKeyboardButton> row1 = InlineButtonUtil.row(button1);

        InlineKeyboardButton button2 = InlineButtonUtil.button("Manzilimiz", "location_callback", "\uD83D\uDCCD");
        button2.setUrl("https://yandex.uz/maps/org/48311554911/?ll=69.268993%2C41.302135&z=15");
        List<InlineKeyboardButton> row2 = InlineButtonUtil.row(button2);

        InlineKeyboardButton button3 = InlineButtonUtil.button("Doktorlarimiz bilan tanishing", "dr_callback", "\uD83D\uDC68\u200D⚕\uFE0F");
        List<InlineKeyboardButton> row3 = InlineButtonUtil.row(button3);

        InlineKeyboardButton button4 = InlineButtonUtil.button("Biz haqimizda", "bio_callback", "ℹ\uFE0F");
        List<InlineKeyboardButton> row4 = InlineButtonUtil.row(button4);

        List<List<InlineKeyboardButton>> rowList = InlineButtonUtil.rowList(row, row1, row2, row3, row4);
        return keyboard(rowList);
    }


    public static InlineKeyboardMarkup serviceMenu() {
        InlineKeyboardButton button = InlineButtonUtil.button("Breket", "breket_callback", "\uD83D\uDD17");
        List<InlineKeyboardButton> row = InlineButtonUtil.row(button);

        InlineKeyboardButton button1 = InlineButtonUtil.button("Tish qo'yish", "add_callback", "➕\uD83E\uDDB7");
        List<InlineKeyboardButton> row1 = InlineButtonUtil.row(button1);

        InlineKeyboardButton button2 = InlineButtonUtil.button("Chistka", "chistka_callback", "\uD83E\uDDFC \uD83E\uDDB7");
        List<InlineKeyboardButton> row2 = InlineButtonUtil.row(button2);

        InlineKeyboardButton button3 = InlineButtonUtil.button("Tish olish", "delete_callback", "➖\uD83E\uDDB7");
        List<InlineKeyboardButton> row3 = InlineButtonUtil.row(button3);

        InlineKeyboardButton button4 = InlineButtonUtil.button("Implantatsiya", "imp_callback", "\uD83E\uDDB7\uD83D\uDD29⚙\uFE0F");
        List<InlineKeyboardButton> row4 = InlineButtonUtil.row(button4);

        InlineKeyboardButton button5 = InlineButtonUtil.button("Maslahatlashish", "mas_callback", "\uD83D\uDCA1\uD83D\uDCDC");
        List<InlineKeyboardButton> row5 = InlineButtonUtil.row(button5);

        List<List<InlineKeyboardButton>> rowList = InlineButtonUtil.rowList(row, row1, row2, row3, row4, row5);
        return keyboard(rowList);
    }


    public static InlineKeyboardMarkup doctorMenu(ArrayList<DoctorEntity> doctorList){
        InlineKeyboardButton button = InlineButtonUtil.button(doctorList.getFirst().getFullName(), "0_callback", "\uD83D\uDC68\u200D⚕\uFE0F");
        List<InlineKeyboardButton> row = InlineButtonUtil.row(button);

        InlineKeyboardButton button1 = InlineButtonUtil.button(doctorList.get(1).getFullName(), "1_callback", "\uD83D\uDC68\u200D⚕\uFE0F");
        List<InlineKeyboardButton> row1 = InlineButtonUtil.row(button1);

        InlineKeyboardButton button2 = InlineButtonUtil.button(doctorList.get(3).getFullName(), "2_callback", "\uD83D\uDC68\u200D⚕\uFE0F");
        List<InlineKeyboardButton> row2 = InlineButtonUtil.row(button2);

        List<List<InlineKeyboardButton>> rowList = InlineButtonUtil.rowList(row, row1, row2);

        return keyboard( rowList );
    }

    public static InlineKeyboardMarkup timeMenu(ArrayList<String> emoji){
        InlineKeyboardButton button1 = InlineButtonUtil.button(emoji.getFirst(), "0_time_callback", "\uD83D\uDC68\u200D⚕\uFE0F");
        List<InlineKeyboardButton> row1 = InlineButtonUtil.row(button1);

        InlineKeyboardButton button2 = InlineButtonUtil.button(emoji.get(1), "1_time_callback", "\uD83D\uDC68\u200D⚕\uFE0F");
        List<InlineKeyboardButton> row2 = InlineButtonUtil.row(button2);

        InlineKeyboardButton button3 = InlineButtonUtil.button(emoji.get(2), "1_time_callback", "\uD83D\uDC68\u200D⚕\uFE0F");
        List<InlineKeyboardButton> row3 = InlineButtonUtil.row(button3);

        InlineKeyboardButton button4 = InlineButtonUtil.button(emoji.get(3), "1_time_callback", "\uD83D\uDC68\u200D⚕\uFE0F");
        List<InlineKeyboardButton> row4 = InlineButtonUtil.row(button4);

        InlineKeyboardButton button5 = InlineButtonUtil.button(emoji.get(4), "1_time_callback", "\uD83D\uDC68\u200D⚕\uFE0F");
        List<InlineKeyboardButton> row5 = InlineButtonUtil.row(button5);

        List<List<InlineKeyboardButton>> rowList = InlineButtonUtil.rowList(row1, row2, row3, row4, row5);

        return keyboard( rowList );
    }

}
