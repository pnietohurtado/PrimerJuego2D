from pydantic import BaseModel, Field 
from typing import Optional
from datetime import datetime 
from enum import Enum 

class Position(BaseModel): 
    id: Optional[int] = None

    posX: int = 3124
    posY: int = 9700
    booleanGet: Optional[bool] = True


    # Types of object that a player can have 
    obj1: int = 0
    obj2: int = 0
    obj3: int = 0
    obj4: int = 0
    obj5: int = 0
    obj6: int = 0
    obj7: int = 0
