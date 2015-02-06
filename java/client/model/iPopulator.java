/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.model;

import shared.models.DTO.UserDTO;
import shared.models.DTO.ClientModelDTO;
import shared.models.DTO.GameContainerDTO;

/**
 *
 * @author Mikey Murphy <mikeyamadeo@gmail.com>
 */
public interface iPopulator {

    public boolean populateModel(ClientModelDTO container);

    public boolean populateModel(UserDTO container);

    public boolean populateModel(GameContainerDTO container);
}
