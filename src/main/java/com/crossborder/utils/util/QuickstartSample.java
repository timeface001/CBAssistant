package com.crossborder.utils.util;// Imports the Google Cloud client library
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.Translate.TranslateOption;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

public class QuickstartSample {
  public static void main(String... args) throws Exception {
    // Instantiates a client
    String key = "AIzaSyD9ZFuiV0CJYppKv9G6DQ08QQc2JDpOnHk";
    Translate translate = TranslateOptions.newBuilder().setApiKey(key).build().getService();

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
    translate.detect(text);
  }
}