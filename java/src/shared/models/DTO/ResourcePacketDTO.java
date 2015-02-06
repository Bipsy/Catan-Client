/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.models.DTO;

/**
 *
 * @author Peter Anderson <anderson.peter@byu.edu>
 */
public class ResourcePacketDTO {

    public final int brick;
    public final int ore;
    public final int sheep;
    public final int wheat;
    public final int wood;

    public ResourcePacketDTO(int newBrick, int newOre, int newSheep,
            int newWheat, int newWood) {
        brick = newBrick;
        ore = newOre;
        sheep = newSheep;
        wheat = newWheat;
        wood = newWood;
    }

}
