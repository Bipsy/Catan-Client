/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.network;

import java.io.IOException;

/**
 *
 * @author Peter Anderson <anderson.peter@byu.edu>
 */
public class ServerProxyException extends IOException {
    public final int HTTP_CODE;
    public final String MESSAGE;
    
    public ServerProxyException(int httpCode, String message) {
        HTTP_CODE = httpCode;
        MESSAGE = message;
    }
}
