from nltk.chat.util import Chat, reflections

# Responses for different user inputs
responses = {
    "hi": "Hello! Welcome to our pizza ordering service.",
    "hello": "Hi there! How can I assist you with your pizza order?",
    "order_pizza": "Sure! Let's start your pizza order.",
    "pizza_choice": "Which pizza would you like to order? We have Chicago Pizza, Pepperoni Pizza, Hawaiian Pizza, California Style Pizza, Margherita Pizza, Meat Lover's Pizza, Veggie Delight Pizza, and BBQ Chicken Pizza.",
    "pizza_size": "What size would you like for your pizza? (S, M, L, XL)",
    "extra_toppings": "Would you like to add extra toppings? (yes/no)",
    "topping_category": "Please select a topping category: Meats, Vegetables, Cheeses, Sauces.",
    "generate_bill": "Here is your total bill: $%.2f. Thank you for ordering with us!",
    "goodbye": "Thank you for visiting! Have a great day!"
}

# Topping prices
topping_prices = {
    "meats": 2,
    "vegetables": 1.5,
    "cheeses": 1,
    "sauces": 0.75
}

# Define rules for the chatbot
chat_rules = [
    (r'hi|hello', [responses['hi'], responses['order_pizza']]),
    (r'order|order pizza', [responses['order_pizza'], responses['pizza_choice']]),
    (r'chicago|pepperoni|hawaiian|california|california style|margherita|meat lover|meat lover\'s|veggie|veggie delight|bbq chicken', [responses['pizza_size']]),
    (r's|m|l|xl', [responses['extra_toppings']]),
    (r'yes', [responses['topping_category']]),
    (r'meats|vegetables|cheeses|sauces', [responses['topping_category']]),
    (r'no', [responses['generate_bill']]),
    (r'bye|goodbye', [responses['goodbye']]),
    (r'.*', ["I'm sorry, I didn't understand that. Could you please repeat?", responses['pizza_choice']])
]

# Create the chatbot
pizza_chatbot = Chat(chat_rules, reflections)

# Start the conversation
print("Welcome to Pizza Ordering ChatBot!")
print("You can start by saying 'hi' or 'hello'.")
print("To exit, simply say 'bye' or 'goodbye'.\n")

while True:
    user_input = input("User> ").lower()
    response = pizza_chatbot.respond(user_input)
    print("Bot> " + response)
    if user_input.lower() in ['bye', 'goodbye']:
        break
