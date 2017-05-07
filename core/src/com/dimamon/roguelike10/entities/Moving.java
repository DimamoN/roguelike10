package com.dimamon.roguelike10.entities;

import com.dimamon.roguelike10.common.Act;
import com.dimamon.roguelike10.common.Action;
import com.dimamon.roguelike10.common.Direction;


public interface Moving {

    void move(Direction direction);
    Act choseAct(Direction direction);

}
