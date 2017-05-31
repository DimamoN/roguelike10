package com.dimamon.roguelike10.game.gameLog;

import com.dimamon.roguelike10.common.Log;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

/**
 * Created by dimamon on 5/31/17.
 *
 * All game events will be saved here
 *
 */
public class StaticGameLog {

    public static Log log = new Log("staticGameLog");

    /**
     * Queue with game messages
     */
    public static Deque<String> messagesLog = new ArrayDeque<>();

    public static void addMessage(String message){
        messagesLog.add(message);
    }

    public static String getLastMessage(){
        if(messagesLog.peekLast() == null){
            return "Nothing hapents";
        }
        return messagesLog.peekLast();
    }

    public static void reset(){
        messagesLog.clear();
    }

}
