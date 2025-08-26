# Цель проекта

## Реализация проекта из [Java Роадмап Сергея Жукова](https://zhukovsd.github.io/java-backend-learning-course/)

## Техническое задание: [Погода](https://zhukovsd.github.io/java-backend-learning-course/projects/weather-viewer/)

# Инструкция по запуску

1. Установите Docker и запустите его.
2. Установите Maven.
3. Скачайте данный репозиторий.
4. Соберите проект командой maven clean package
5. Перенесите полученный war артефакт в папку docker-app/app 
6. В папке docker-app репозитория выполнить команду: docker compose up -d

Опционально вставьте в .env.local свой API ключ с https://openweathermap.org/

```
WEATHER_API_KEY=<your key>
```