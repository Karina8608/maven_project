package gb.example;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import java.util.Random;

public class MontyHallParadox {

    public static void main(String[] args) {
        // Число игр в симуляции
        int numberOfGames = 1000;

        // Счетчики для успехов при разных стратегиях
        int winsWhenSwitch = 0;
        int winsWhenStay = 0;

        // Генератор случайных чисел
        Random random = new Random();

        for (int i = 0; i < numberOfGames; i++) {
            // Случайная генерация двери с машиной (0, 1 или 2)
            int carDoor = random.nextInt(3);

            // Случайная первая попытка игрока (0, 1 или 2)
            int playerChoice = random.nextInt(3);

            // Ведущий открывает одну из оставшихся дверей с козлом
            int montyOpens = random.nextInt(3);
            while (montyOpens == carDoor || montyOpens == playerChoice) {
                montyOpens = random.nextInt(3);
            }

            // Игрок меняет выбор (если не выбрал машину сразу)
            int switchChoice = 3 - playerChoice - montyOpens;

            // Проверка, если игрок выиграл, когда изменил решение
            if (switchChoice == carDoor) {
                winsWhenSwitch++;
            }

            // Проверка, если игрок выиграл, не меняя решение
            if (playerChoice == carDoor) {
                winsWhenStay++;
            }
        }

        System.out.println("Количество игр: " + numberOfGames);
        System.out.println("Победы при смене выбора: " + winsWhenSwitch);
        System.out.println("Победы без смены выбора: " + winsWhenStay);

        // Используем Apache Commons Math для вывода статистики
        DescriptiveStatistics stats = new DescriptiveStatistics();
        stats.addValue(winsWhenSwitch);
        stats.addValue(winsWhenStay);

        // Вывод вероятностей
        System.out.println("Вероятность победы при смене выбора: " + (double) winsWhenSwitch / numberOfGames);
        System.out.println("Вероятность победы без смены выбора: " + (double) winsWhenStay / numberOfGames);
    }
}
