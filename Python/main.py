from fastapi import FastAPI, HTTPException
from fastapi.middleware.cors import CORSMiddleware
from database import initializaDB

app = FastAPI()

app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

@app.get('/')
async def welcome():
    await initializaDB()
    return {'message': 'Bienvenido a mi FastApi'}


@app.post('/api/PlayerPOS')
async def position():  # Here I have to send the data to the database about the position of the player
    await initializaDB() 
    return {'message': 'Position send'}

@app.get('/api/PlayerGET')
async def positionGET(): 
    await initializaDB() 