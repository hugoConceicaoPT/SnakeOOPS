package ModelLayer.SnakeLayer;

import java.util.LinkedList;

public class ManualMovementStrategy implements MovementStrategy {
    
    /** Move a cabeça da cobra
     * @param nextDirection a próxima direção que a cobra vai tomar
     */
    private void moveSquare(Quadrado quadrado,Direction currentDirection, Direction nextDirection) {
        switch (currentDirection) {
            case UP:
                switch (nextDirection) {
                    case RIGHT:
                        quadrado.rotateAngle(-90);
                        break; 
                    case LEFT:
                        quadrado.rotateAngle(90);
                        break;
                    default:
                        break;
            }
            break;
            case DOWN:
                switch (nextDirection) {
                    case RIGHT:
                        quadrado.rotateAngle(90);
                        break;
                    case LEFT:
                        quadrado.rotateAngle(-90);
                        break;
                    default:
                        break;
            }
            break;
            case LEFT:
                switch (nextDirection) {
                    case UP:
                        quadrado.rotateAngle(-90);
                        break;
                    case DOWN:
                        quadrado.rotateAngle(90);
                        default:
                            break;
                }
            break;
            case RIGHT:
                switch (nextDirection) {
                    case UP:
                        quadrado.rotateAngle(90);
                        break;
                    case DOWN:
                        quadrado.rotateAngle(-90);
                        default:
                            break;
                }
                break;
            default:
                break;
            
        }
    }
    
    @Override
    public void move(Direction nextDirection,  LinkedList<Quadrado> body, Direction currentDirection, int arestaHeadLength) {

        if(isOppositeDirection(nextDirection, currentDirection))
            return;

        Ponto centroHeadSnake = body.getFirst().getCentroide();

        Quadrado ultimoQuadrado = body.getLast();
        ultimoQuadrado.translateCentroide(((int) centroHeadSnake.getX()),(int) centroHeadSnake.getY());

        if(currentDirection != nextDirection) 
            moveSquare(ultimoQuadrado,currentDirection, nextDirection);

        switch (nextDirection) {
            case UP:
                ultimoQuadrado.translate(0, arestaHeadLength); 
                break;
            case DOWN:
                ultimoQuadrado.translate(0, -arestaHeadLength);
                break;
            case LEFT:
                ultimoQuadrado.translate(-arestaHeadLength, 0);
                break;
            case RIGHT:
                ultimoQuadrado.translate(arestaHeadLength, 0);
                break;
            default:
                break;
        }

        body.removeLast();
        body.addFirst(ultimoQuadrado);
    }

    private boolean isOppositeDirection(Direction nextDirection, Direction currentDirection) {
        return (nextDirection == Direction.UP && currentDirection == Direction.DOWN) ||
               (nextDirection == Direction.DOWN && currentDirection == Direction.UP) ||
               (nextDirection == Direction.LEFT && currentDirection == Direction.RIGHT) ||
               (nextDirection == Direction.RIGHT && currentDirection == Direction.LEFT);
    }
}
