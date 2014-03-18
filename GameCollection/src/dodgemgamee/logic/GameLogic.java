/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dodgemgamee.logic;

import java.io.Serializable;

/**
 *
 * @author Ákos
 */
public class GameLogic implements Serializable{

    public int size;
    private int map[][];
    private boolean redTheNext = true;

    public GameLogic(int size) {
        this.size = size;
        map = new int[size][size];
        int x = 0;
        for (int i = 1; i < size; ++i) {
            map[i - 1][0] = 1;
            map[size - 1][i] = 2;
        }
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                str = str + map[i][j] + " ";
            }
            str = str + "\n";
        }
        return str;
    }

    public void move(int x, int y, Way way) {
        if (redTheNext && map[x][y] == 1) {
            switch (way) {
                case FRONT:
                    if (y == size-1) {
                        map[x][y] = 0;
                    } else {
                        if (map[x ][y+ 1] == 0) {
                            map[x][y] = 0;
                            map[x ][y+ 1] = 1;
                          
                        }
                        
                    }
                     redTheNext = false;
                     break;
                case LEFT:
                    if (x > 0) {
                        if (map[x - 1][y] == 0) {
                            map[x][y] = 0;
                            map[x - 1][y] = 1;
                            
                        }
                    }
                     redTheNext = false;
                    break;
                case RIGHT:
                    if (x < size - 2) {
                        if (map[x+ 1 ][y] == 0) {
                            map[x][y] = 0;
                            map[x + 1][y] = 1;
                        }
                    }
                     redTheNext = false;
                    break;
            }
            
        }
        if (!redTheNext && map[x][y] == 2) {
            switch (way) {
                case FRONT:
                    if (x == 0) {
                        map[x][y] = 0;
                    } else {
                        if (map[x - 1][y] == 0) {
                            map[x][y] = 0;
                            map[x - 1][y] = 2;
                        }
                    }
                     redTheNext = true;
                    break;
                case LEFT:
                    if (y > 0) {
                        if (map[x][y - 1] == 0) {
                            map[x][y] = 0;
                            map[x][y - 1] = 2;
                        }
                    }
                    redTheNext = true;
                    break;
                case RIGHT:
                    if (y < size - 2) {
                        if (map[x][y + 1] == 0) {
                            map[x][y] = 0;
                            map[x][y + 1] = 2;
                        }
                    }
                    redTheNext = true;
                    break;
            }
        }
    }
    
    public int endGame()
    {
        if (!hasFreeRed())
        {
            return 1;
        }
        if (!hasFreeBlue())
        {
            return 2;
        }
        return 0;
        
    }
    
    public boolean hasFreeRed()
    {
        for(int i=0; i<size-1; i++)
        {
            for(int j=0; j<size; j++)
            {
                if(i==0 && map[i][j]==1)
                {
                    if(map[i+1][j]!=2 || map[i][j+1]!=2)
                    {
                        return true;
                    }
                }
                if(i==size-2 && map[i][j]==1)
                {
                    if(map[i-1][j]!=2 || map[i][j+1]!=2)
                    {
                        return true;
                    }
                }
                if(i!=size-2 && i!=0 && map[i][j]==1)
                {
                    if(map[i+1][j]!=2 || map[i-1][j]!=2 || map[i][j+1]!=2)
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }
 
    public boolean hasFreeBlue()
    {
        for(int i=0; i<size; i++)
        {
            for(int j=1; j<size; j++)
            {
                if(j==0 && map[i][j]==2)
                {
                    if(map[i][j+1]!=1 || map[i-1][j]!=1)
                    {
                        return true;
                    }
                }
                if(j==size-1 && map[i][j]==2)
                {
                    if(map[i][j-1]!=1 || map[i-1][j]!=1)
                    {
                        return true;
                    }
                }
                if(j!=size-1 && j!=0 && map[i][j]==2)
                {
                    if(map[i][j+1]!=1 || map[i][j-1]!=1 || map[i-1][j]!=1)
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }    
    public int getMap(int x, int y)
    {
        return map[x][y];
    }
    
    public boolean getRedTheNext()
    {
        return this.redTheNext;
    }

    public int getSize() {
        return size;
    }
}
