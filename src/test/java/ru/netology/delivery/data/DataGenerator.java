package ru.netology.delivery.data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {

    private static Faker faker = new Faker(new Locale("ru"));
    private DataGenerator() {
    }

    public static String generateDate(int newDays) {
        LocalDate newDate = LocalDate.now().plusDays(newDays);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = formatter.format(newDate);
        return date;
    }
    public static String getCity() {
        List<String> list = Arrays.asList("Санкт-Петербург", "Москва", "Самара",
                "Петропавловск-Камчатский", "Абакан", "Владикавказ", "Екатеринбург",
                "Казань", "Йошкар-Ола", "Калининград", "Кострома", "Калуга", "Краснодар",
                "Махачкала", "Курган", "Саранск", "Саратов", "Майкоп", "Мурманск", "Волгоград", "Грозный", "Белгород");
        Random random = new Random();
        return list.get(random.nextInt(list.size()));
    }

    public static String getInvalidCity() {
        List<String> list = Arrays.asList("Мухосранск", "Урюпинск", "Сочи", "Сан-Ремо", "Валенсия");
        Random random = new Random();
        return list.get(random.nextInt(list.size()));
    }

        public static String getName() {
            String name = faker.name().fullName().replace("ё", "е");
            return name;
        }
    public static String getPhone() {
        String phone = faker.phoneNumber().phoneNumber();
        return phone;
    }
}
