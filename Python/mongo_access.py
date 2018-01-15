
from werkzeug import generate_password_hash, check_password_hash

import requests
import os
import json
import pymongo
from datetime import datetime
from pymongo import MongoClient

from flask import Flask
from flask import jsonify
from flask import request
from flask_pymongo import PyMongo

db = MongoClient()


app = Flask(__name__)

app.config['MONGO_DBNAME'] = 'Muven'
app.config['MONGO_URI'] = 'mongodb://localhost:27017/Muven'

mongo = PyMongo(app)

app.secret_key = "muven4-key"



@app.route('/', methods=['GET'])
def hello():
    return "testing get results"


#TODO
#backend functionality to display bookings list
@app.route('/List',  methods=['POST','GET'] )
#get the data for search criteria
    data = request.get_json(silent=True)
    
    #user collection 
    user = mongo.db.user
    
    if request.method == "GET":
        #get username
        username = data.get('username')
        
        #======================
        # retrieve all bookings where user is a booker
        # retrieve all bookings where user is a bookee
        # seperate bookings by accepted/pending/cancelled
        # request value determines status; 1= accepted 0 = pending, -1 = cancelled
        #=======================
        
        return 400 #user does not exist
        
        
    return 500 #not get

#TODO
#backend functionality to display profile information
@app.route('/display',  methods=['POST','GET'] )
#get the data for search criteria
    data = request.get_json(silent=True)
    
    #user collection 
    user = mongo.db.user
    
    if request.method == "GET":
        #get username
        username = data.get('username')
        
        #======================
        # get all info from a user
        # return all data as JSON object
        #
        #=======================
        
        return 400 #user does not exist
        
        
    return 500 #Not Get

#reject a booking request
@app.route('/reject',  methods=['POST','GET'] )
def reject_booking():
    #get the data for search criteria
    data = request.get_json(silent=True)
    
    #user collection 
    booking = mongo.db.bookreq
    
    if request.method == "POST":
        
        #gets posted data
        booker = data.get('booker')
        bookee = data.get('bookee')
        date = data.get('date')
        
        #update the request so that the request is set to -1
        result = booking.update_one({"booker": booker, "date" : date}, {"$set": {"request": -1}}, upsert=False)
    
        #if the change was made
        if result.matched_count:
                return "200"
        
        #update failed
        return "400"
    
    return "400"

#accept a booking request
@app.route('/accept',  methods=['POST','GET'] )
def accept_booking():
    #get the data for search criteria
    data = request.get_json(silent=True)
    
    #user collection 
    booking = mongo.db.bookreq
    
    if request.method == "POST":
        
        #gets posted data
        booker = data.get('booker')
        bookee = data.get('bookee')
        date = data.get('date')

        #update the request to accepted
        result = booking.update_one({"booker": booker, "date" : date}, {"$set": {"request": 1}}, upsert=False)
    
        #if not accepted
        if result.matched_count:
                return "200"
        
        #update failed
        return "400"
    
    return "400"

#request booking functionality
@app.route('/request', methods=['POST','GET'])
def request_booking():
    #get the data for search criteria
    data = request.get_json(silent=True)
    
    #user collection 
    booking = mongo.db.bookreq
    
    
    if request.method == "POST":
        
        #gets posted data
        booker = data.get('booker')
        bookee = data.get('bookee')
        date = data.get('date')
        
        #record to be inserted
        booking_record = {"booker": booker,
                       "bookee": bookee,
                       "date" : date,
                       "request" : 0  }
        #validate date
        
        #getting current date
        today = datetime.now()
        
        #getting booking date
        book_date = datetime.strptime(date, '%m/%d/%y')
        
        #booking more than 6 months in advance
        if (book_date - today).days >180: 
            return "401"
        
        #ensure that the booking date is in the future
        if (book_date - today).days < 1: 
            return "402"
        
        #ensures date is not already booked
        if( (booking.find_one({"booker": booker, "date": date}) == None) and (booking.find_one({"bookee": bookee, "date": date}) == None) ):
            booking.insert_one(booking_record)
            return "200"
        else:
            return "400"
        return "500"
    
    
    
    return "500" #for now returns true

#to be programmed search backend
@app.route('/search', methods=['POST','GET'])
def search():
    #get the data for search criteria
    data = request.get_json(silent=True)
    
    #user collection 
    user = mongo.db.user 
    
    #checks if axious posted
    if request.method == "POST":        
        #get data from axios?
        search = data.get('search')
        search_params = search.split(" ")
        if "artist" in search_params:
            type = "artist"
            both = 0
            search_params.remove("artist")
        elif "venue" in search_params:
            type = "venue"
            both = 0
            search_params.remove("venue")
        else:
            both = 1

        #query results
        temp = []
        result = []
        for x in search_params:
            if both == 0:
                query = user.find( {'$and': [ {'type': type}, { '$or': [ {'username': x},  {'city': x}, {'state': x}]}]})
                for j in query:
                    temp.append(j)
            else:
                query = user.find( {'$or': [ {'username': x},  {'city': x}, {'state': x} ] } )
                for j in query:
                    temp.append(j)

        for e in temp:
            e['_id'] = str(e['_id'])
            result.append(e)
        return jsonify(result)
        
    return "500"
        
    

@app.route('/profile', methods=['POST','GET'])
def profile():
    #gets the data from the post
    data = request.get_json(silent=True)
    
    #user collection 
    user = mongo.db.user 
    
    #checks if axious posted
    if request.method == "POST":

        if data.get('type') == 'artist':
            #gets the individual bits of data from the post
            type = data.get('type')
            name = data.get('name')
            address = data.get('address')
            city = data.get('city')
            state = data.get('state')
            genre = data.get('genre')
            about = data.get('about')
            bookings = []
        
            #what parts of a record to update
            update_record = {"type": type,
                             "address": address,
                             "city" : city,
                             "state": state,
                             "genre": genre,
                             "about": about,
                             "bookings": bookings}
        
            #updates the document
            result = user.update_one({'username':name}, {"$set": update_record}, upsert=False)
            #tests to see if update occurred
            if result.matched_count:
                return "200"
        
            #update failed
            return "400"

        if data.get('type') == 'venue':
            #gets the individual bits of data from the post
            type = data.get('type')
            name = data.get('name')
            address = data.get('address')
            city = data.get('city')
            state = data.get('state')
            genre = data.get('genre')
            about = data.get('about')
            capacity = data.get('capacity')
            max_bands = data.get('max_bands')
            bookings = []
        
            #what parts of a record to update
            update_record = {"type": type,
                             "address": address,
                             "city" : city,
                             "state": state,
                             "genre": genre,
                             "about": about,
                             "capacity": capacity,
                             "max_bands": max_bands,
                             "bookings": bookings}
        
            #updates the document
            result = user.update_one({'username':name}, {"$set": update_record}, upsert=False)
            #tests to see if update occurred
            if result.matched_count:
                return "200"
        
            #update failed
            return "400"
    #update failed
    return "500"


@app.route('/signup', methods=['POST','GET'])
def sign_up():
    data = request.get_json(silent=True)
    if request.method == "POST":
                
        user = mongo.db.user #user collection until artist/venue functionality added
        
        #verify that data has been entered
        if data.get('first_name').strip() == "" or data.get('email').strip() == "" or data.get('password').strip() == "" or data.get('confirm').strip() == "":
            return "402"
        
        #gather data
        name = data.get('first_name').strip()
        email = data.get('email').strip()
        #verify that passwords match and encrypt the password
        if (data.get('password').strip() == data.get('confirm').strip()): 
            pwdhash = data.get('password').strip() #generate_password_hash(data.get('password'))
        else:
            return "404"
        
        #user record to be stored
        user_record = {"username": name,
                       "email": email,
                       "password" : pwdhash   }
        
        

        
        #if the email is already in use
        if user.find_one({"email": email}):
            return "408" 
        #if the username is already in use
        elif user.find_one({"username": name}) :
            return "407" #username already in use error
        else:
            user.insert_one(user_record)
            return "200" #successfully registered
        
    return "500" #nothing posted

@app.route('/login', methods=['POST'])
def login():
    if request.method == "POST":
        data = request.get_json(silent=True)
        
        user = mongo.db.user #user collection until artist/venue functionality added
        
        #validates entered fields
        if data.get('username').strip() == "" or data.get('password').strip() == "" :
            return "402"
        
        #get data from axios?
        username = data.get('username').strip()
        pwdhash  = data.get('password').strip()#generate_password_hash(data.get('password'))
    
        

        
        #if username and password find no match
        if(user.find_one({"username": username, "password": pwdhash}) != None):
            return "200"
        #wrong password
        elif(user.find_one({"username": username}) != None): 
            return "401" #no matches
        
        return "400" #nothing posted -must enter username and password-
    
    return "500" #nothing posted
    

    
if __name__ == "__main__":
    app.run(debug=True)
