package com.crossborder.utils.util;// Imports the Google Cloud client library
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.Translate.TranslateOption;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

public class QuickstartSample {
  public static void main(String... args) throws Exception {
    // Instantiates a client
    String key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCup5aaB0LI48UT" +
            "toNuGiK/lVJtjCGq2hmHH4B09YrLV403/XNfjc4NuzmuAhaMS6nLBry4q88jqBHz" +
            "gZQkbzNWNPyRhcF9VhINF1N3qzCHs4Az3XJsGVTCRGn4GKkukRGGPB9frW2axfCc" +
            "SOkBCeeYfSxWmOHjK8hgmh4iLw7hdGk37Kk+U1K77pENVlGO5JK2HDReO3xJglxg" +
            "aFqOjSFgRPbI4HH97IY+9muwcW8JHlykoGB+vIAL12GvaHs4+CPgrzTHEfKQXlJP" +
            "bI95QZYPi5jM0SSCr/luLWjBncwOECVtDaw8JND61NQcw2g4Kn3UKgf1VnNLPfqf" +
            "4A4LhOLbAgMBAAECggEAAwNhKxx09Sz4zsjspjmiioDzqxrVJIgevfYNK/dl2Ebq" +
            "aMzhRgTBnqWxmQ7wKft5h8crT678eTZXw5u4spLjOoQvgf3iGLIjcuu0GkcZzz2J" +
            "jgYusy68skf74WE2NKzMC0TTmxL4O8iuc1kkAUs/yPcKjMlBHGo6SsWzP2O7Hf3y" +
            "L5RXR63dP7gEDHqW+QwyKXsyq5LrFhRd4S2KhkvbYczVywAUIjxumgYQG57u67vh" +
            "MRuw3a0/ej/ffNm7lOiUALyLptTqFoY2/F4FeKFbkHsXxn9YTBgGPKPPPi1aUFMY" +
            "cgxhoFAFz5qvUHQASFBx9KIc63Bm7uZsLx2273soAQKBgQDdj6iXQGhQ8ahVsHsZ" +
            "5j9Kih7bzpG4/cwFaD+pwaMCI+tHO35/9BTqdfGo9K2hrSxIDR5mSnYDOLSnGaV6" +
            "SaAI80fDzAlZ9mJCPtWtBdRcGvTlOz2ZOgDjtAz650hpYMSu0OnG4BSurcvq4IAT" +
            "tSe/UcMgrG5UrY80Kbnlp703CwKBgQDJzW39KDmW3ZxSfvnVBh3p58HXBycMIF1K" +
            "2JeM6aiI0qQKwmSOftigXH2i9LyVz9cAvtrjjg+VsXis1hbUkDaw+CXYEKQpVRX8" +
            "KNBuOlVVUBAAfdnazqYatW/aFREMQmjftZ0GtkF26edYORjmQG5OQ+VYJBRDABe8" +
            "wTDc8wElcQKBgEQPcValLmZPFcrc37PhCug27avQFOui6x1uHMBk/1IU/p1vJTjh" +
            "Hdu4R3gDUaMss0Ge5gu+K+ppbPU356FdJJAbS5xV/Tlpel2nN7Ri6g0xKrGQNSfw" +
            "rX1FPgeFLLVSSJj7DBVPmIrgUJfiRvXG3GLlFu/eiDXy6t63QqfNk05pAoGAMe66" +
            "wGErnsDG5UK13pfc3vuRYvtKFh4L93EQTrWiFfjIzkC7UObiAwvfqmqUHx2GPZD7" +
            "6EJGaOu4yEtQ6tekMLW153h2piN/hR00NZIJe0qMKxL4zMNFJmaufm+oADiOaowt" +
            "hnOiTyIhNBsspgxbPtJd9Sk4OjvTo8jJpmIaboECgYEAuuOXwEEsPX25SfLetyya" +
            "gGFSPQmHmRqw+o8VAy1TK1e8+XI0RKKmUlIu9bKMKUSIbDi1PFkQPA00nXkzoP2v" +
            "BuIrQ8nfXkkKdrws43FcxTHEwmXTUfWGE0BhUrMnHyhaN3dfKS3rabBjseVvvuYH" +
            "Fkt7vHkEDSfZFDFFnYtYdcM=";
    Translate translate = TranslateOptions.newBuilder().build().getService();

    // The text to translate
    String text = "Hello, world!";

    // Translates some text into Russian
    Translation translation =
        translate.translate(
            text,
            TranslateOption.sourceLanguage("en"),
            TranslateOption.targetLanguage("ru"));


    System.out.printf("Text: %s%n", text);
    System.out.printf("Translation: %s%n", translation.getTranslatedText());
  }
}