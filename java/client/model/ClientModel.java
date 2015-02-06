package client.model;

import shared.models.Bank;
import shared.models.Board;
import shared.models.ChatObject;
import shared.models.TradeOffer;
import shared.models.UserManager;

/**
 * Class serves as a wrapper for the model of the game or game instance. It can
 * be used as the single model instance for the server (iPopulator) and client
 * facades to write and read.
 *
 * @author Mikey Murphy <mikeyamadeo@gmail.com>
 */
public class ClientModel {

    private Bank bank;
    private Board board;
    private ChatObject chatObject;
    private UserManager userManager;
    private TradeOffer tradeOffer;
    private int version;
    //Player index of the game winner
    private int winner;

    /**
     * Getters and Setters for model wrapper
     */
    /**
     * BANK
     */
    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    /**
     * BOARD (Map)
     */
    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    /**
     * CHAT OBJECT
     */
    public ChatObject getChatObject() {
        return chatObject;
    }

    public void setChatObject(ChatObject chatObject) {
        this.chatObject = chatObject;
    }

    /**
     * USER MANAGER (player manager)
     */
    public UserManager getUserManager() {
        return userManager;
    }

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    /**
     * TRADE OFFER
     */
    public TradeOffer getTradeOffer() {
        return tradeOffer;
    }

    public void setTradeOffer(TradeOffer tradeOffer) {
        this.tradeOffer = tradeOffer;
    }

    /**
     * VERSION
     */
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    /**
     * WINNER
     */
    public int getWinner() {
        return winner;
    }

    public void setWinner(int winner) {
        this.winner = winner;
    }

}
