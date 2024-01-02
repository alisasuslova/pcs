# Курсовая работа к модулю «Java Core»: Менеджер задач

Задача: предстоит разработать сервер, отвечающий за менеджмент списка дел.

## Входные данные:
### Сервер
Программа должна принимать запросы на добавление или удаление задач из списка через сервер. Сервер создаётя и запускается в [классе Main](https://github.com/netology-code/pcs-javacore/blob/main/src/main/java/ru/netology/javacore/Main.java), его менять нельзя. Реализацию же самому [классу сервера](https://github.com/netology-code/pcs-javacore/blob/main/src/main/java/ru/netology/javacore/TodoServer.java) предстоит написать самим. После старта, он должен в бесконечном цикле принимать подключения и считывать с них одну строку, в которой будет располагаться json вида:
```json
{ "type": "ADD", "task": "Название задачи" }
```
где `type` - тип операции (`ADD` или `REMOVE`), а `task` - сама задача.

Для парсинга входных данных подключения воспользуйтесь библиотекой GSon (`com.google.code.gson:gson:2.8.9`). Мы предполагаем, что на сервер всегда приходят корректные данные.

В ответ на запрос сервер должен присылать текущее состояние списка задач после совершения операции и в том виде, в котором его возвращает операция `getAllTasks` (т.е. без всяких json и тп).

### Клиент
Для демонстрационных целей создан [класс Client](https://github.com/netology-code/pcs-javacore/blob/main/src/main/java/ru/netology/javacore/Client.java), который пытается добавить задачу со случайным именем из ограниченного набора имён. Вы можете запустить `Main`, а затем запустить `Client`, чтобы увидеть, как отработает сервер на ваш запрос.

## Выходные данные:
Центральным для логики программы компонентом будет [класс Todos](https://github.com/netology-code/pcs-javacore/blob/main/src/main/java/ru/netology/javacore/Todos.java). Объект этого класса должен содержать в себе набор задач, добавленных в систему. 

Каждая задача представляет собой обычное значение типа `String`. Например: `"Сходить в магазин"`, `"Пойти на пробежку"`. 

Изначально объект этого класса не должен содержать никаких задач, но должна быть возможность:
- добавить их через метод `add`;
- удалить через метод `remove`. 

Также у этого объекта должна быть возможность получить все актуальные задачи разом через метод `getAllTasks` - метод возвращает все задачи через пробел **в отсортированном лексикографическом (словарном) порядке**. Например, если мы добавили задачу "Пробежка", "Акробатика" и "Учёба", то этот метод должен вернуть строку вида `Акробатика Пробежка Учёба`.

После реализации этого класса нужно [написать на него тесты](https://github.com/netology-code/pcs-javacore/blob/main/src/test/java/ru/netology/javacore/TodosTests.java) на основе JUnit 5, минимум 3 штуки, тестирующие его в разных сценариях.

## Реализация:
1. Реализовала класс `Todos` со следующими методами `addTask()`, `getTasks()`, `removeTask()`, `getAllTasks()`:
```java
public class Todos {
    List<String> tasks = new ArrayList<>();

    public void addTask(String task) {
        this.tasks.add(task);
    }

    public List<String> getTasks() {
        return this.tasks;
    }

    public void removeTask(String task) {
        this.tasks.remove(task);
    }

    @Override
    public String toString() {
        return tasks + " ";
    }

    public String getAllTasks() {
        List<String> str = getTasks();
        List<String> strSort = str.stream()
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());

        String temp = strSort.toString();
        String[] strArray = temp.split(",");
        String result = String.join(" ", strArray);
        return result;
    }
}
```
2. Написала тесты на основе JUnit 5:
```java
public class TodosTests {

    Todos sut;

    @BeforeEach
    public void init() {
        System.out.println("Test started");
        sut = new Todos();
    }

    @BeforeAll
    public static void started() {
        System.out.println("Tests started");
    }

    @AfterEach
    public void finished() {
        System.out.println("Test completed");
    }

    @AfterAll
    public static void finishedAll() {
        System.out.println("Tests completed");
    }

    @Test
    public void testAddTask() {
        List<String> tasks = new ArrayList<>();
        String task1 = "task1";
        String task2 = "task2";
        tasks.add(task1);
        tasks.add(task2);

        int expected = 2;
        int result = tasks.size();

        assertEquals(expected, result);
    }

    @Test
    public void testRemoveTask() {
        List<String> tasks = new ArrayList<>();
        String task1 = "task1";
        String task2 = "task2";
        String task3 = "task3";
        String task4 = "task4";
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        tasks.add(task4);
        tasks.remove(task1);
        tasks.remove(task3);

        String expected = "[task2, task4]";
        String result = tasks.toString();

        assertEquals(expected, result);

    }

    @Test
    public void testGetAllTasks() {
        StringBuilder sb = new StringBuilder();
        List<String> tasks = new ArrayList<>();
        String task1 = "task1";
        String task2 = "task2";
        String task3 = "task3";
        String task4 = "task4";
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        tasks.add(task4);

        Collections.sort(tasks);

        for (String str: tasks) {
            sb.append(str)
                    .append(" ");
        }
        String result = sb.toString();
        String expected = "task1 task2 task3 task4 ";

        assertEquals(expected, result);
    }
}
```
3. В конце после запуска класса `Main` запустила класс `Client`, который добавляет задачу со случайным именем из ограниченного набора имён.
