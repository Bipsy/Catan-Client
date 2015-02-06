package shared.models.DTO;

import shared.definitions.MoveType;
import shared.exceptions.InvalidPlayerIndex;

/**
 * This class stores the information needed to create a JSON string of the trade
 * offer information, and is used to facilitate the transfer of data between the
 * server and client.
 *
 * @author Austin
 *
 */
public class TradeOfferDTO {

    public final MoveType type;
    private int sender;
    private int receiver;
    private ResourceListDTO offer;

    public TradeOfferDTO(MoveType newType) {
        type = newType;
    }

    public int getSender() {
        return sender;
    }

    /**
     * Sets the sender's index
     *
     * @pre this index can only be the numbers 0 - 3 because there are only four
     * players
     * @param sender the player index of the sender
     * @throws InvalidPlayerIndex this exception is thrown if the index is not
     * an integer in the range 0 - 3
     */
    public void setSender(int sender) throws InvalidPlayerIndex {
        if (sender > 3 || sender < 0) {
            throw new InvalidPlayerIndex();
        }
        this.sender = sender;
    }

    public int getReceiver() {
        return receiver;
    }

    /**
     * Sets the index of the receiver
     *
     * @pre this index can only be the numbers 0 - 3 because there are only four
     * players
     * @param receiver the player index to whom resources will be traded
     * @throws InvalidPlayerIndex this exception is thrown if the index is not
     * an integer in the range 0 - 3
     */
    public void setReceiver(int receiver) throws InvalidPlayerIndex {
        if (receiver > 3 || receiver < 0) {
            throw new InvalidPlayerIndex();
        }
        this.receiver = receiver;
    }

    public ResourceListDTO getOffer() {
        return offer;
    }

    public void setOffer(ResourceListDTO offer) {
        this.offer = offer;
    }

}
