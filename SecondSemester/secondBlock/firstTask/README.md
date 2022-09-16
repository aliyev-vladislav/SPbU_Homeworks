# Кольцевой список

Кольцевой (циклический, замкнутый) связный список  —  контейнер, использующийся для хранения элементов, является разновидностью связного списка, 
при которой первый элемент указывает на последний, а последний  —  на первый.

## Функционал
> **AddAfter(CircleListNode<T>, CircleListNode<T>)** – Добавляет заданный новый узел после заданного существующего узла в CircleList<T>.<br></br>
> **AddAfter(CircleListNode<T>, T)** – Добавляет новый узел, содержащий заданное значение, после заданного существующего узла в CircleList<T>. <br></br>
> **AddBefore(CircleListNode<T>, CircleListNode<T>)** – Добавляет заданный новый узел перед заданным существующим узлом в CircleList<T>. <br></br>
> **AddBefore(CircleListNode<T>, T)** – Добавляет новый узел, содержащий заданное значение, перед заданным существующим узлом в CircleList<T>. <br></br>
> **AddFirst(CircleListNode<T>)** – Добавляет заданный новый узел в начало CircleList<T>. <br></br>
> **AddFirst(T)** – Добавляет новый узел, содержащий заданное значение, в начало CircleList<T>. <br></br>
> **AddLast(CircleListNode<T>)** – Добавляет заданный новый узел в конец CircleList<T>. <br></br>
> **AddLast(T)** – Добавляет новый узел, содержащий заданное значение, в конец CircleList<T>.<br></br>
> **Clear()** – Удаляет все узлы из CircleList<T>.<br></br>
> **Contains(T)** – Определяет, принадлежит ли значение объекту CircleList<T>.<br></br>
> **CopyTo(T[], Int32)** – Копирует целый массив CircleList<T> в совместимый одномерный массив Array, начиная с заданного индекса целевого массива.<br></br>
> **Equals(Object)** – Определяет, равен ли указанный объект текущему объекту. (Унаследовано от Object) <br></br>
> **Find(T)** – Находит первый узел, содержащий указанное значение.<br></br>
> **FindLast(T)** – Находит последний узел, содержащий указанное значение.<br></br>
> **GetType()** – Возвращает объект Type для текущего экземпляра. (Унаследовано от Object)<br></br>
> **Remove(CircleListNode<T>)**	 – Удаляет заданный узел из объекта CircleList<T>.<br></br>
> **Remove(T)** – Удаляет первое вхождение заданного значения из CircleList<T>.<br></br>
> **RemoveFirst()**	– Удаляет узел в начале CircleList<T>.<br></br>
> **RemoveLast()**	– Удаляет узел в конце CircleList<T>.<br></br>
> **ToString()** – Возвращает строку, представляющую текущий объект. (Унаследовано от Object)<br></br>
  
  **Методы расширения:**
> **All<TSource>(IEnumerable<TSource>, Func<TSource,Boolean>)**	–  Проверяет, все ли элементы последовательности удовлетворяют условию.<br></br>
> **Any<TSource>(IEnumerable<TSource>)** – Проверяет, содержит ли последовательность какие-либо элементы.<br></br>
> **Any<TSource>(IEnumerable<TSource>, Func<TSource,Boolean>)** Проверяет, удовлетворяет ли какой-либо элемент последовательности заданному условию.<br></br>
> **Append<TSource>(IEnumerable<TSource>, TSource)** – Добавляет значение в конец последовательности.<br></br>
> **Concat<TSource>(IEnumerable<TSource>, IEnumerable<TSource>)** – Объединяет две последовательности.<br></br>
> **Contains<TSource>(IEnumerable<TSource>, TSource)** – Определяет, содержится ли указанный элемент в последовательности, используя компаратор проверки на равенство по умолчанию.<br></br>
> **Count<TSource>(IEnumerable<TSource>)** – Возвращает количество элементов в последовательности.<br></br>
> **Count<TSource>(IEnumerable<TSource>, Func<TSource,Boolean>)** – Возвращает число, представляющее количество элементов последовательности, удовлетворяющих заданному условию.<br></br>
> **ElementAt<TSource>(IEnumerable<TSource>, Int32)** – Возвращает элемент по указанному индексу в последовательности.<br></br>
> **ElementAtOrDefault<TSource>(IEnumerable<TSource>, Index)** – Возвращает элемент последовательности по указанному индексу или значение по умолчанию, если индекс вне допустимого диапазона.<br></br>
> **ElementAtOrDefault<TSource>(IEnumerable<TSource>, Int32)** – Возвращает элемент последовательности по указанному индексу или значение по умолчанию, если индекс вне допустимого диапазона.<br></br>
> **First<TSource>(IEnumerable<TSource>)** – Возвращает первый элемент последовательности.<br></br>
> **FirstOrDefault<TSource>(IEnumerable<TSource>)** – Возвращает первый элемент последовательности или значение по умолчанию, если последовательность не содержит элементов.<br></br>
> **Last<TSource>(IEnumerable<TSource>)** – Возвращает последний элемент последовательности.<br></br>
> **LastOrDefault<TSource>(IEnumerable<TSource>)** – Возвращает последний элемент последовательности или значение по умолчанию, если последовательность не содержит элементов.<br></br>
> **Max<TSource>(IEnumerable<TSource>)** – Возвращает максимальное значение, содержащееся в универсальной последовательности.<br></br>
> **Min<TSource>(IEnumerable<TSource>)** – Возвращает минимальное значение, содержащееся в универсальной последовательности.<br></br>
> **Prepend<TSource>(IEnumerable<TSource>, TSource)** – Добавляет значение в начало последовательности.<br></br>
> **Reverse<TSource>(IEnumerable<TSource>)** – Изменяет порядок элементов последовательности на противоположный.<br></br>
> **SequenceEqual<TSource>(IEnumerable<TSource>, IEnumerable<TSource>)** – Определяет, совпадают ли две последовательности, используя для сравнения элементов компаратор проверки на равенство по умолчанию, предназначенный для их типа.<br></br>
> **Single<TSource>(IEnumerable<TSource>)** –Возвращает единственный элемент последовательности и генерирует исключение, если число элементов последовательности отлично от 1.<br></br>
> **Take<TSource>(IEnumerable<TSource>, Int32)** –Возвращает указанное число подряд идущих элементов с начала последовательности.<br></br>
> **TakeLast<TSource>(IEnumerable<TSource>, Int32)** – Возвращает новую перечислимую коллекцию, содержащую последние count элементов из source.<br></br>


  
  ## Пример использования
  Небольшая игра в барабаны и прохождение цветов.
  
  Правила игры: N человек образуют круг, начиная с того, кто первым передает цветы, 
  когда счет достигает M, этот человек выходит из игры, пока не останется последний человек.

  Код
  ```java
 
 1 public class Game {
 2 
 3     //Один кольцевой список
 4     CircleLinkedList list = new CikleLinkList();
 5     //всего людей
 6     int num;
 7     //Считать, чтобы выйти
 8     int key;
 9     
10     //Метод инициализации игры
11     public Game(int num,int key)
12     {
13        this.num = num;
14        this.key = key;
15     }
16     
17     public void play() throws Exception
18     {
19        for(int i = 0;i < num;i++)
20        {
21            list.insert(i, i);  
22        }
23        
24        System.out.println ("\ n ------- Перед запуском игры --------- \ n");
25        for (int i = 0; i < list.size; i++)
26        { 
27            System.out.print(list.get(i)+" ");
28        }
29        System.out.println ("\ n ------- Начало игры --------- \ n");
30        int iCount = num; //Старт равен общему количеству человек num
31        int j = 0; //Аккумулятор вычисляет, может ли он делиться на ключ.
32        
33        Node node = list.head;
34        while(iCount!=1)
35        {
36           if (node.getElement() != null && Integer.parseInt(node.getElement().toString()) != -1)
37           {
38             j++;  
39             if (j % key == 0)
40             {
41                 node.setElement(-1);
42                 iCount--;
43                 System.out.println();
44                 for(int i = 0; i < list.size; i++)
45                 {
46                    System.out.print(list.get(i)+  " ");
47                 }
48             }
49           } 
50           node = node.next;
51        }
52        System.out.println ("\ n ------- Игра окончена --------- \ n");
53        for(int i = 0; i < list.size; i++)
54        {
55            System.out.print(list.get(i) + " ");
56        }
57     }
58     
59 }
  ```
  

  ## Запуск тестов
  Для сборки проекта используется **Gradle Wrapper**. Он содержит bat-скрипты для **Windows** и shell-скрипты для **Mac OS** и **Linux**. 
  Эти скрипты позволяют запускать сборку с Gradle без необходимости установки самого Gradle в вашу систему. <br></br>
  
  Запустите следующую команду для загрузки и инициализации wrapper-скриптов:
  ```sh
  gradle wrapper
  ```
  <br></br>
  Сборка проекта:
  
  Windows
  ```sh
  gradlew.bat build
  ```
  Linux и Max OS
  ```sh
  ./gradlew build
  ```
  <br></br>
  Затем запустите тесты, используя сследующую команду:
  
  Windows
  ```sh
  gradlew.bat test
  ```
  Linux и Mac OS
  ```sh
  ./gradlew test
  ```
  
  <br></br>
  При необходимости вы можете очистить сборку с помощью приведенной ниже команды:
  
  Windows
  ```sh
  gradlew.bat clean
  ```
  Linux и Mac OS
  ```sh
  
  ./gradlew clean
  ```
  
  
  
