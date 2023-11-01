
import requests


symbol = input("Enter a ticker symbol ")

api_key = #Enter API Key


url = f"https://api.twelvedata.com/time_series?symbol={symbol},EUR/USD,ETH/BTC:Huobi,TRP:TSX&interval=1min&apikey={api_key}"

 
stock = requests.get(url).json()

date_time = stock[symbol]['values'][0]['datetime']
opening_price = stock[symbol]['values'][0]['open']
high_price = stock[symbol]['values'][0]['high']
low_price = stock[symbol]['values'][0]['low']


print()
print(f"All stock prices are current as of {date_time} ")
print()
print(f"The opening price for {symbol} is {opening_price}")
print()
print(f"The high price for {symbol} is {high_price}" )
print()
print(f"The low price for {symbol} is {low_price}")
print()