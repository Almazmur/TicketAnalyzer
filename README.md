#  Ticket Analysis

## Описание

Этот проект анализирует данные о рейсах между Владивостоком и Тель-Авивом, чтобы ответить на следующие вопросы:
- Минимальное время полета для каждого авиаперевозчика.
- Разница между средней ценой и медианой для полета между Владивостоком и Тель-Авивом.

## Как запустить

1. Убедитесь, что у вас установлен JDK 17 или выше.
2. Скачайте исходный код проекта:
   ```bash
   git clone https://github.com/Almazmur/FlightTicketAnalysis.git
   cd TicketAnalysis
3. Скомпилируйте проект:
   ```bash
   javac -cp "path/to/jackson-core.jar:path/to/jackson-databind.jar:path/to/jackson-annotations.jar" src/org/example/*.java -d bin
5. Поместите файл tickets.json в корневую директорию проекта.
6. Запустите программу
   ```bash
   java -cp "bin:path/to/jackson-core.jar:path/to/jackson-databind.jar:path/to/jackson-annotations.jar" org.example.Main

Формат вывода:
  Результаты будут представлены в текстовом виде в консоли. Пример вывода:
  Минимальное время полета для каждого авиаперевозчика:
  SU: 360 минут
  S7: 390 минут
  TK: 350 минут
  BA: 485 минут
  Средняя цена: 13960,00
  Медиана цены: 13500,00
  Разница между средней ценой и медианой: 460,00
