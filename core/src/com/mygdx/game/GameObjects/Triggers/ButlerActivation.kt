package com.mygdx.game.GameObjects.MoveableObjects

import com.badlogic.gdx.math.Vector2
import com.mygdx.game.Collition.MoveCollision
import com.mygdx.game.DefaultTextureHandler
import com.mygdx.game.Enums.Layer
import com.mygdx.game.GameObjectData
import com.mygdx.game.GameObjects.GameObject.GameObject
import com.mygdx.game.GameObjects.MoveableEntities.Characters.Player
import com.mygdx.game.Managers.AreaManager
import com.mygdx.game.butler

class ButlerActivationTrigger(gameObjectData: GameObjectData)
    : GameObject(gameObjectData, Vector2(32f,32f)){
    override val texture = DefaultTextureHandler.getTexture("Butler.png")
    override val layer = Layer.PERSON
    override val collision = ButlerActivationCollision(this)
    init {
        polygon.scale(1.5f)
    }
}

class ButlerActivationCollision(val butlerActivationTrigger: ButlerActivationTrigger): MoveCollision(){
    override var canMoveAfterCollision = true

    override fun collisionHappened(collidedObject: GameObject) {
        if(collidedObject is Player){
            val currentObjects = AreaManager.getActiveArea()!!.gameObjects
            currentObjects.remove(butlerActivationTrigger)
            butler.setActive(butlerActivationTrigger.currentPosition())
        }
    }

}