from motor.motor_asyncio import AsyncIOMotorClient
from bson import ObjectId
from models import Position

client = AsyncIOMotorClient('mongodb://localhost:27017')
database = client.Juego
collection = database.Partida 

async def initializaDB(): 
    count = await collection.count_documents({})
    if count == 0: 
        await collection.insert_one({
            '_id' : 1, 
            'posX' : 3124, 
            'posY': 9700, 
            'booleanGet': True,
            'obj1': 0,
            'obj2': 0,
            'obj3': 0,
            'obj4': 0,
            'obj5': 0,
            'obj6': 0,
            'obj7': 0
        })

        print('Enviado a la base de datos!')


async def getData(): 
    cursor = await collection.find_one({})

    if cursor: 
        return Position(**cursor)
    return None


async def pushData(): 
    