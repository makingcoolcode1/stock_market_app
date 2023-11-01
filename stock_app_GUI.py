
from tkinter import *
import requests
from configparser import ConfigParser


stock_data = 'https://api.twelvedata.com/time_series?symbol={},EUR/USD,ETH/BTC:Huobi,TRP:TSX&interval={}&apikey={}'


config_file = 'config.ini'
config = ConfigParser()
config.read(config_file)
api_key = config["api_key"]['key']


root = Tk()
root.title("Stock App")
root.geometry('550x400')
root.resizable(0,0)



def get_stocks(symbol, interval):
    result = requests.get(stock_data.format(symbol, interval, api_key))
    json = result.json()
    if result:
        open = json[symbol]['values'][0]['open']
        high = json[symbol]['values'][0]['high']
        low = json[symbol]['values'][0]['low']
        close = json[symbol]['values'][0]['close']
        name = [symbol]

    
  
    final = (open, high, low, close, name )
    return final



def search_stocks():
    symbol = ticker_search.get()
    interval = search_interval.get()
    market = get_stocks(symbol, interval)
    if market:
        open_price['text'] = '${:.5}'.format(market[0])
        high_price['text'] = '${:.5}'.format(market[1])
        low_price['text'] = '${:.5}'.format(market[2])
        closing_price['text'] = '${:.5}'.format(market[3])
        stock_name['text'] = market[4]
        
        
        


#Enter Shortcut

def shortcut(stock):
    search_stock_btn.invoke()
root.bind('<Return>', shortcut)



#Frame
search_frame = Frame(root, highlightbackground='black', highlightthickness=2, )
search_frame.place(width=280, height=400)

#Search Frame
search_ticker_lbl = Label(root, text = "Enter Ticker", font = ('arial', 16))
search_ticker_lbl.place(x=80, y=25)

ticker_search = Entry(root, width=16, font=('arial', 16))
ticker_search.place(x=60, y=60)

search_interval_lbl = Label(root, text="Enter Interval", font=('arial', 16))
search_interval_lbl.place(x=75, y=150)

search_interval = Entry(root, width = 16, font=('arial', 16))
search_interval.place(x=60, y=200)

search_stock_btn = Button(root, text = "Search Stock", font=('arial', 16), command=( search_stocks))
search_stock_btn.place(x=70, y=280)

 #Result Frame (Root)


#Labels


stock_name = Label(root, text = 'Ticker', font = ('arial', 16, 'bold'))
stock_name.place(x=380, y=10)


opening_price_lbl = Label(root, text="Open:", font = ('arial', 16))
opening_price_lbl.place(x=310, y=70)


high_price_lbl = Label(root, text = 'High:', font=('arial', 16))
high_price_lbl.place(x=310, y=150)

low_price_lbl = Label(root, text = "Low:", font = ('arial', 16))
low_price_lbl.place(x=310, y=230)

closing_pric_lbl = Label(root, text = "Closing:", font = ("arial, 16"))
closing_pric_lbl.place(x=310, y=310)


# Price Data Inserts

open_price = Label(root, text = "....", font = ('arial', 16))
open_price.place(x=430, y=70)

high_price = Label(root, text = "....", font = ('arial', 16))
high_price.place(x=430, y=150)

low_price = Label(root, text = "....", font = ('arial', 16))
low_price.place(x=430, y=230)

closing_price = Label(root, text = "....", font = ('arial', 16))
closing_price.place(x=430, y=310)


root.mainloop()