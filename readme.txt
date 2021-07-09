This project was developed in java spring boot.. The Database used is mySQL which can seen in the application.properties file.
To run this locally, kindly change the database, username and password to suit your own database setup...
The following endpoints are exposed on port 8080:
1. /addItem
  Payload needed is 
  {
    "userId": 1,
    "name": "First Parcel",
    "fromLocation":"Owerri",
    "currentLocation": "Umuahia",
    "destination": "Enugu",
    "status": "PICKED_UP"
}
2. /updateItem
  
  Payload needed is 
  {
    "userId": 1,
    "name": "First Parcel",
    "fromLocation":"Owerri",
    "currentLocation": "Umuahia",
    "destination": "Enugu",
    "status": "IN_TRANSIT"
}
3. /deleteItem/{itemId}
  Payload needed is id number of item

4. /item/{itemId}
this endpoint gets a particular item. Payload needed is id number of item.

Thank you for the opportunity
