## Тестовое задание (от Компания "Титан")

- Реактивный RESTful Web Service “Калькулятор” (Spring WebFlux)
- Реализовать реактивный RESTful Web Service “Калькулятор” с использованием Spring WebFlux.

#### При вызове сервиса клиент должен передать ему четыре параметра:

1. Текст JS/Python-функции №1 принимающий 1 параметр Int;
2. Текст JS/Python-функции №2 принимающий 1 параметр Int;
3. Количество расчетов, которые необходимо выполнить;
4. Признак выравнивания ответа (выдавать ответ в упорядоченном CSV-формате, либо выдавать результаты по мере их
   получения).

#### Сервис работает следующим образом:

1. По таймеру, через определенный (возможно указать в конфигурации общий для обоих функций) интервал (одинно вычисление
   одной функции), запускается итерация рассчета;
2. Для каждого существующего в настоящий момент клиентского запроса производится вычисление результатов функции №1 и
   функции №2. Текст функций написан на языке JavaScript либо Python (выбор языка на Ваше усмотрение) и должен
   вызываться из Вашего Java сервиса соответствующим образом. В качестве единственного параметра при вызове функций
   передается порядковый номер ее вызова, который уникален и отсчитывается для каждого вызова с начала. Синтаксис
   передачи входного значения в JS/Python-код и получение результата определите на свое усмотрение;
7. Полученные результаты выдаются клиенту либо по мере вычисления, либо подвергаются выравниванию и выдаются в
   упорядоченном виде по мере возможности.

#### Пример упорядоченной выдачи результатов:

 ```
 <№ итерации>, <результат функции 1>, <время расчета функции 1>, <кол-во полученных наперед результатов функции 1 (еще не выданных, в связи с медленным расчетом функции 2)>, <результат функции 2>, <время расчета функции 2>, <кол-во полученных наперед результатов функции 2 (еще не выданных, в связи с медленным расчетом функции 1)>
```

#### Пример неупорядоченной выдачи результатов:

```
<№ итерации>, <номер функции>, <результат функции>, <время расчета функции>
```

### Требования к реализации

- При любом режиме выдачи результатов, клиент должен их получать сразу, по мере возможности (без 100% буферизации
  полного цикла вычислений на стороне сервиса).
- Необходимо предусмотреть базовые исключительные ситуации при выполнении JavaScript/Python
  кода, а также различное время выполнения функций (№1 и №2).
- Выбор языка для описания функций (JavaScript или Python) на Ваше усмотрение.
- Необходима реализация только на одном выбранном Вами языке.

### Дополнительное требование

- Для проверки реализованного сервиса хотелось бы чтобы вы предоставили тестовый клиент.

### Использованные мной технологии

#### Сервер:

- [Spring WebFlux](https://docs.spring.io/spring-framework/docs/current/reference/html/web-reactive.html)

### Клиент:

- [Spring MVC](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html)
- [Thymeleaf](https://www.thymeleaf.org/)
- [Vue 3.0](https://vuejs.org/)
- [microsoft/fetch-event-source](https://github.com/Azure/fetch-event-source)

#### То что представило наибольшую сложность:

- Наиболее сложным оказалось - это выполнить запрос на специфический узел, так как встроенной библиотеки бля выполнения
  подобного запроса нет. И половина времени ушла на поиски решения, и день на то что бы его внедрить так как на
  доступных ресурсах была некорректно собранна данная библиотека. 
