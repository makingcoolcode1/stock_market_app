
import requests


while True:
    print()
    symbol = input("Enter a ticker symbol or type exit to quit ")
    if symbol.lower() == "exit":
        exit(0)

    api_key = #Enter your API Key


    url = f"https://api.twelvedata.com/time_series?symbol={symbol},EUR/USD,ETH/BTC:Huobi,TRP:TSX&interval=1min&apikey={api_key}"

    
    stock = requests.get(url).json()

    date_time = stock[symbol]['values'][0]['datetime']
    opening_price = stock[symbol]['values'][0]['open']
    high_price = stock[symbol]['values'][0]['high']
    low_price = stock[symbol]['values'][0]['low']
    closing_price = stock[symbol]['values'][0]['close']


    print()
    print(f"All stock prices are current as of {date_time} ")
    print()
    print(f"The opening price for {symbol} is {opening_price}")
    print()
    print(f"The high price for {symbol} is {high_price}" )
    print()
    print(f"The low price for {symbol} is {low_price}")
    print()
    print(f"The closing price for {symbol} is {closing_price}")
    print()
    print("*************************************************************")