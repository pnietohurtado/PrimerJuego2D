from motor.motor_asyncio import AsyncIOMotorClient
from bson import ObjectId

client = AsyncIOMotorClient('mongodb://localhost:27017')
database = client.Juego
collection = database.Partida 

async def initializaDB(): 
    count = await collection.count_documents({})