package com.dimamon.roguelike10.game.gameLog;

import com.badlogic.gdx.scenes.scene2d.ui.List;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by dimamon on 5/31/17.
 *
 * All game events will be saved here
 *
 */
public class GameLog {

    /**
     * Queue with game messages
     */
    Queue<String> messagesLog;

    public GameLog() {
        this.messagesLog = new ArrayDeque<>();
    }

    public void addMessage(String message){
        messagesLog.add(message);
    }

    public String getLastMessage(){
        String lastMessage = messagesLog.peek();
        if(lastMessage == null){
            return "Nothing";
        }
        return lastMessage;
    }
}
