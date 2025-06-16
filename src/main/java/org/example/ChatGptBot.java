package org.example;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.json.JSONObject;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;

public class ChatGptBot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {
        String text = update.getMessage().getText();
        System.out.println("HERE 0");
        String answerFromGpt = messageGpt(text);
        System.out.println("HERE 1");
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(answerFromGpt);
        sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
        System.out.println("HERE 2");
        try {
            execute(sendMessage);
            System.out.println("HERE 3");
        } catch (TelegramApiException e) {
            e.printStackTrace();
            System.out.println("HERE 4");
        }
    }

    @Override
    public String getBotUsername() {
        return "Shai1606Bot";
    }

    public String getBotToken() {
        return "7287253231:AAHLh7Gt8qWc0KpcemnF85ImKgS0v_b8jzo";
    }

    private String messageGpt(String message) {
        try {
            OkHttpClient client = new OkHttpClient();
            String url = "https://app.seker.live/fm1/send-message?id=039575329" +
                    "&text=" + message;
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Response response = client.newCall(request).execute();
            JSONObject jsonObject = new JSONObject(response.body().string());
            return jsonObject.getString("extra");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";

    }
}
