/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.model;

import shared.models.DTO.ClientModelDTO;

/**
 *
 * @author Mikey Murphy <mikeyamadeo@gmail.com>
 */
public interface iPopulator {

    public boolean populateModel(ClientModelDTO container, String username);

}
