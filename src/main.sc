import requests
from jaicp.sdk import JAICPApp, TextResponse

# Получаем данные о погоде
def get_weather():
    # Пример запроса на получение прогноза погоды (вы можете заменить на реальное API)
    response = requests.get("https://api.openweathermap.org/data/2.5/weather?q=London&appid=your_api_key")
    data = response.json()
    weather = data['weather'][0]['description']
    return f"Погода в Лондоне: {weather}"

# Получаем курс валют
def get_currency():
    # Пример запроса на получение курса валют (вы можете заменить на реальное API)
    response = requests.get("https://api.exchangerate-api.com/v4/latest/USD")
    data = response.json()
    rate = data['rates']['EUR']  # Курс USD к EUR
    return f"Курс доллара к евро: {rate}"

# Бот на платформе JAICP
class AssistantBot(JAICPApp):
    def __init__(self):
        super().__init__()

    # Стейт /hello — приветствие пользователя
    def on_hello(self, message):
        return TextResponse("Привет! Я могу рассказать тебе о погоде и курсах валют. Что тебя интересует?")

    # Стейт /weather — запрос прогноза погоды
    def on_weather(self, message):
        weather_info = get_weather()
        return TextResponse(weather_info)

    # Стейт /currency — запрос курса валют
    def on_currency(self, message):
        currency_info = get_currency()
        return TextResponse(currency_info)

    # Стейт /NoMatch — обработка исключений, если интент не распознан
    def on_NoMatch(self, message):
        return TextResponse("Извините, я не понял ваш запрос. Могу помочь чем-то другим?")

# Запуск бота
if __name__ == '__main__':
    bot = AssistantBot()
    bot.run()  # Запуск бота на платформе JAICP
