package com.github.maxomys.geometrydash.components;

import com.github.maxomys.geometrydash.jade.Component;
import com.github.maxomys.geometrydash.util.Constants;
import com.github.maxomys.geometrydash.util.Vector2;

public class RigidBody extends Component {

    public Vector2 velocity;

    public RigidBody(Vector2 velocity) {
        this.velocity = velocity;
    }

    @Override
    public void update(double dt) {
        gameObject.transform.position.y += velocity.y * dt;
        gameObject.transform.position.x += velocity.x * dt;

        velocity.y += Constants.GRAVITY * dt;

        if (Math.abs(velocity.y) > Constants.TERMINAL_VELOCITY) {
            velocity.y = Math.signum(velocity.y) * Constants.TERMINAL_VELOCITY;
        }
    }

    @Override
    public Component copy() {
        return null;
    }

}
