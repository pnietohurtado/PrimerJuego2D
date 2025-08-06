/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pokemon;

/**
 *
 * @author pablo
 */
public class Pokemon {
    
    private int level; 
    private int pokedex; 
    private String nombre; 
    private int hp; 
    private int attack; 
    private int defense; 
    private boolean object; 
    
    
    public Pokemon(int level, int pokedex, String nombre, int hp, int attack, int defense, boolean object){
        this.level = level; 
        this.pokedex = pokedex; 
        this.nombre = nombre;  
        this.hp = hp; 
        this.attack = attack; 
        this.defense = defense; 
        this.object = object; 
    }
    
    public int getLevel(){return this.level;}
    public int getPokedex(){return this.pokedex;}
    public String getNombre(){return this.nombre; }
    public int getHP(){return this.hp; }
    public int getAttack(){return this.attack;}
    public int getDefense() {return this.defense; }
    public boolean getObject() {return this.object; }
    
    public void setLevel(int level){this.level = level;}
    public void setPokedex(int pokedex){this.pokedex = pokedex; }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setObject(boolean object) {
        this.object = object;
    }
    
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder(); 
        sb.append("Level: ").append(this.level).append( " Nombre: ").append(this.nombre).append( " HP: ").append(this.hp).append( " Attack ").append(this.attack).append( " Defense: ").append(this.defense).append( " Object: ").append(this.object).append( "\n"); 
        return sb.toString(); 
    }
    
    
}
