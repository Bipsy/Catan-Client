/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.model;

import shared.models.DTO.ClientModelDTO;

/**
 *
 * @author Peter Anderson <anderson.peter@byu.edu>
 */
public interface iPopulator {
    public boolean populateModel(ClientModelDTO container);
}
