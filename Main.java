import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

// Создаем класс для оповещений (например, новостей)
class News {
    private String newsContent;

    public News(String content) {
        this.newsContent = content;
    }

    public String getNewsContent() {
        return newsContent;
    }
}

// Создаем класс для группы (наблюдаемого объекта)
class Group extends Observable {
    private List<News> newsList = new ArrayList<>();

    public void addNews(News news) {
        newsList.add(news);
        setChanged(); // Устанавливаем флаг изменения
        notifyObservers(news); // Оповещаем всех наблюдателей
    }

    public List<News> getNewsList() {
        return newsList;
    }
}

// Создаем класс для пользователей (наблюдателей)
class User implements Observer {
    private String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof News) {
            News news = (News) arg;
            System.out.println(name + " получил новость: " + news.getNewsContent());
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // Создаем группы
        Group group1 = new Group();
        Group group2 = new Group();
        Group group3 = new Group();
        Group group4 = new Group();

        // Создаем пользователей
        User user1 = new User("Пользователь 1");
        User user2 = new User("Пользователь 2");

        // Добавляем пользователей в группы
        group1.addObserver(user1);
        group1.addObserver(user2);

        group2.addObserver(user1);
        group2.addObserver(user2);

        group3.addObserver(user1);

        group4.addObserver(user2);

        // Добавляем новости в группы
        group1.addNews(new News("Новость в группе 1"));
        group2.addNews(new News("Новость в группе 2"));
        group3.addNews(new News("Новость в группе 3"));
        group4.addNews(new News("Новость в группе 4"));
    }
}
