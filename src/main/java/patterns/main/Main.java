package patterns.main;

import patterns.task.MovieLibrary;
import patterns.task.menues.LoginSystem;

public class Main {
    static {
        LoginSystem.readFromFileToUserCredentials();
        LoginSystem.readFromFileToCustomers();
        MovieLibrary.readMoviesFromFile();
    }
    // не зробив html
    public static void main(String[] args) {
        LoginSystem.run();
    }
}

// Task:

// Уявімо, що нам треба додати нові фічі.
// По-перше, клієнт хоче виведення ще й у HTML.
// По-друге, клієнт хоче додати інші типи фільмів - наприклад, драми, комедії, трилери.
// По-третє, ми хочемо додати до фільмів нові характеристики (країна походження, короткий опис, режисер, актори)

// У поточну структуру ці правки лягають важко, тому нам доведеться її змінити.

// Створити можливість обліку декількох клієнтів, роботи з текстовими(!?) файлами для зберігання даних
// Програма повинна надавати можливості
// - перегляду каталога всіх фільмів,
// - додавання фільму,
// - пошуку за певними характеристиками
// - та ін.
// Створити зручне меню для доступу до функцій